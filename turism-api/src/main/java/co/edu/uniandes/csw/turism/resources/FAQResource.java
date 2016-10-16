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

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.turism.api.IFAQLogic;
import co.edu.uniandes.csw.turism.dtos.minimum.FAQDTO;
import co.edu.uniandes.csw.turism.entities.FAQEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

/**
 *
 * @author lm.ariza10
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FAQResource {
     @Inject
    private IFAQLogic faqLogic;
    @Context
    private HttpServletResponse response;

    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    @PathParam("agencysId") private Long agencysId;

 @GET
    public List<FAQDTO> getFAQs() {
        List<FAQDTO> list = new ArrayList<>();
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", faqLogic.countFAQs());
           for(FAQEntity faqEntity: faqLogic.getFAQs(page, maxRecords)) {
               list.add(new FAQDTO(faqEntity));
           }
        } else {
              for(FAQEntity faqEntity: faqLogic.getFAQs()) {
               list.add(new FAQDTO(faqEntity));
           }
        }
        return list;
    }

    @GET
    @Path("{id: \\d+}")
    public FAQDTO getFAQ(@PathParam("id") Long id) {
        return new FAQDTO(faqLogic.getFAQ(id));
    }

    @POST
    @StatusCreated
    public FAQDTO createFAQ(FAQDTO dto) {
        FAQEntity entity = dto.toEntity();
        return new FAQDTO(faqLogic.createFAQ(entity));
    }

    @PUT
    @Path("{id: \\d+}")
    public FAQDTO updateFAQ(@PathParam("id") Long id, FAQDTO dto) {
        FAQEntity entity = dto.toEntity();
        entity.setId(id);
        return new FAQDTO(faqLogic.updateFAQ(entity));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteFAQ(@PathParam("id") Long id) {
        faqLogic.deleteFAQ(id);
    }
    
}