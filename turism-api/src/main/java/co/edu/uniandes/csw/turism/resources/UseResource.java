/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import java.util.List;
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
