/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.service.AuthService;
import co.edu.uniandes.csw.turism.api.IClientLogic;
import co.edu.uniandes.csw.turism.api.IAgencyLogic;
import co.edu.uniandes.csw.turism.entities.ClientEntity;
import co.edu.uniandes.csw.turism.entities.AgencyEntity;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.group.Group;
import com.stormpath.sdk.resource.ResourceException;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author asistente
 */
public class UseResource extends AuthService {
    
    @Inject private IClientLogic clientLogic;
    @Inject private IAgencyLogic agencyLogic;
    private static final String CLIENT_HREF = "https://api.stormpath.com/v1/groups/3NWAWMK7dhZDY0Lo7p3lai";
    private static final String AGENCY_HREF = "https://api.stormpath.com/v1/groups/3HLGWgwc7MezMbiUHKWIKO";    
    private static final String CLIENT_CD = "client_id";
    private static final String AGENCY_CD = "agency_id";
    
    @Override
    public void register(UserDTO user) {
        try {
           Account acct = createUser(user);
        for (Group gr : acct.getGroups()) {
            switch(gr.getHref()){
                case CLIENT_HREF:
                ClientEntity client = new ClientEntity();
                client.setName(user.getUserName());
                client = clientLogic.createClient(client);
                acct.getCustomData().put(CLIENT_CD, client.getId());
                break;
                case AGENCY_HREF:
                AgencyEntity provider = new AgencyEntity();
                provider.setName(user.getUserName());                
                provider = agencyLogic.createAgency(provider);
                acct.getCustomData().put(AGENCY_CD, provider.getId());  
                break;
                default:
                 throw new WebApplicationException("Group link doen's match ");
            }
                
            }
        acct.getCustomData().save();
        } catch (ResourceException e) {
            throw new WebApplicationException(e, e.getStatus());
        }
    }
    
}
