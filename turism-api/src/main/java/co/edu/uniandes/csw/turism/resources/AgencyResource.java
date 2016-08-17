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
import co.edu.uniandes.csw.auth.stormpath.Utils;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.turism.api.IAgencyLogic;
import co.edu.uniandes.csw.turism.dtos.detail.AgencyDetailDTO;
import co.edu.uniandes.csw.turism.entities.AgencyEntity;
import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.group.Group;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;

/**
 * URI: agencys/
 * @generated
 */
@Path("/agencys")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AgencyResource {
    private static final String AGENCY_HREF = "https://api.stormpath.com/v1/groups/3HLGWgwc7MezMbiUHKWIKO";
    private static final String ADMIN_HREF = "https://api.stormpath.com/v1/groups/4ayxsJyhONfx4NUCF5HmTg";

    @Inject private IAgencyLogic agencyLogic;
    @Context private HttpServletResponse response;
    @Context private HttpServletRequest req;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;

   
    /**
     * Convierte una lista de AgencyEntity a una lista de AgencyDetailDTO.
     *
     * @param entityList Lista de AgencyEntity a convertir.
     * @return Lista de AgencyDetailDTO convertida.
     * @generated
     */
    private List<AgencyDetailDTO> listEntity2DTO(List<AgencyEntity> entityList){
        List<AgencyDetailDTO> list = new ArrayList<>();
        for (AgencyEntity entity : entityList) {
            list.add(new AgencyDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Agency
     *
     * @return Colección de objetos de AgencyDetailDTO
     * @generated
     */
    @GET
    public List<AgencyDetailDTO> getAgencys() {
        String accountHref = req.getRemoteUser();
        if (accountHref != null) {
            Account account = Utils.getClient().getResource(accountHref, Account.class);
            for (Group gr : account.getGroups()) {
                switch (gr.getHref()) {
                    case ADMIN_HREF:
                        if (page != null && maxRecords != null) {
                        this.response.setIntHeader("X-Total-Count", agencyLogic.countAgencys());
                        return listEntity2DTO(agencyLogic.getAgencys(page, maxRecords));
                    }
                    return listEntity2DTO(agencyLogic.getAgencys());
                    case AGENCY_HREF:
                        Integer id = (int) account.getCustomData().get("agency_id");
                        List<AgencyDetailDTO> list = new ArrayList();
                        list.add(new AgencyDetailDTO(agencyLogic.getAgency(id.longValue())));
                        return list;
                }
            }
        } 
        return null;
    }

    /**
     * Obtiene los datos de una instancia de Agency a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de AgencyDetailDTO con los datos del Agency consultado
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public AgencyDetailDTO getAgency(@PathParam("id") Long id) {
        return new AgencyDetailDTO(agencyLogic.getAgency(id));
    }

    /**
     * Se encarga de crear un Agency en la base de datos
     *
     * @param dto Objeto de AgencyDetailDTO con los datos nuevos
     * @return Objeto de AgencyDetailDTOcon los datos nuevos y su ID
     * @generated
     */
    @POST
    @StatusCreated
    public AgencyDetailDTO createAgency(AgencyDetailDTO dto) {
        return new AgencyDetailDTO(agencyLogic.createAgency(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Agency
     *
     * @param id Identificador de la instancia de Agency a modificar
     * @param dto Instancia de AgencyDetailDTO con los nuevos datos
     * @return Instancia de AgencyDetailDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public AgencyDetailDTO updateAgency(@PathParam("id") Long id, AgencyDetailDTO dto) {
        AgencyEntity entity = dto.toEntity();
        entity.setId(id);
        AgencyEntity oldEntity = agencyLogic.getAgency(id);
        return new AgencyDetailDTO(agencyLogic.updateAgency(entity));
    }

    /**
     * Elimina una instancia de Agency de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteAgency(@PathParam("id") Long id) {
        agencyLogic.deleteAgency(id);
    }
    public void existsAgency(Long agencysId){
        AgencyDetailDTO agency = getAgency(agencysId);
        if (agency== null) {
            throw new WebApplicationException(404);
        }
    }
    
    
    @Path("{agencysId: \\d+}/trips")
    public Class<TripResource> getTripResource(@PathParam("agencysId") Long agencysId){
        existsAgency(agencysId);
        return TripResource.class;
    }
    
}
