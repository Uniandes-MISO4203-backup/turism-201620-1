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
import co.edu.uniandes.csw.turism.api.ITripLogic;
import co.edu.uniandes.csw.turism.dtos.detail.TripDetailDTO;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * URI: agencys/{tripsId: \\d+}/trips/
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TripResource {

    @Inject private ITripLogic tripLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    @PathParam("agencysId") private Long agencysId;

   
    /**
     * Convierte una lista de TripEntity a una lista de TripDetailDTO
     *
     * @param entityList Lista de TripEntity a convertir
     * @return Lista de TripDetailDTO convertida
     * @generated
     */
    private List<TripDetailDTO> listEntity2DTO(List<TripEntity> entityList){
        List<TripDetailDTO> list = new ArrayList<>();
        for (TripEntity entity : entityList) {
            list.add(new TripDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Trip asociados a un Agency
     *
     * @return Colección de objetos de TripDetailDTO
     * @generated
     */
    @GET
    public List<TripDetailDTO> getTrips() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", tripLogic.countTrips());
            return listEntity2DTO(tripLogic.getTrips(page, maxRecords, agencysId));
        }
        return listEntity2DTO(tripLogic.getTrips(agencysId));
    }

    /**
     * Obtiene los datos de una instancia de Trip a partir de su ID asociado a un Agency
     *
     * @param tripId Identificador de la instancia a consultar
     * @return Instancia de TripDetailDTO con los datos del Trip consultado
     * @generated
     */
    @GET
    @Path("{tripId: \\d+}")
    public TripDetailDTO getTrip(@PathParam("tripId") Long tripId) {
        TripEntity entity = tripLogic.getTrip(tripId);
        if (entity.getAgency() != null && !agencysId.equals(entity.getAgency().getId())) {
            throw new WebApplicationException(404);
        }
        return new TripDetailDTO(entity);
    }

    /**
     * Asocia un Trip existente a un Agency
     *
     * @param dto Objeto de TripDetailDTO con los datos nuevos
     * @return Objeto de TripDetailDTOcon los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public TripDetailDTO createTrip(TripDetailDTO dto) {
        return new TripDetailDTO(tripLogic.createTrip(agencysId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Trip.
     *
     * @param tripId Identificador de la instancia de Trip a modificar
     * @param dto Instancia de TripDetailDTO con los nuevos datos.
     * @return Instancia de TripDetailDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{tripId: \\d+}")
    public TripDetailDTO updateTrip(@PathParam("tripId") Long tripId, TripDetailDTO dto) {
        TripEntity entity = dto.toEntity();
        entity.setId(tripId);
        TripEntity oldEntity = tripLogic.getTrip(tripId);
        entity.setCategory(oldEntity.getCategory());
        return new TripDetailDTO(tripLogic.updateTrip(agencysId, entity));
    }

    /**
     * Elimina una instancia de Trip de la base de datos.
     *
     * @param tripId Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{tripId: \\d+}")
    public void deleteTrip(@PathParam("tripId") Long tripId) {
        tripLogic.deleteTrip(tripId);
    }
    public void existsTrip(Long tripsId){
        TripDetailDTO trip = getTrip(tripsId);
        if (trip== null) {
            throw new WebApplicationException(404);
        }
    }
    
    
    @Path("{tripsId: \\d+}/category")
    public Class<TripCategoryResource> getTripCategoryResource(@PathParam("tripsId") Long tripsId){
        existsTrip(tripsId);
        return TripCategoryResource.class;
    }
    
    
    @Path("{tripsId: \\d+}/taxes")
    public Class<TaxResource> getTaxesResource(@PathParam("tripsId") Long tripsId){
        existsTrip(tripsId);
        return TaxResource.class;
    }
    
    @Path("{tripsId: \\d+}/raitings")
    public Class<TripRaitingResource> getTripRaitingResource(@PathParam("tripsId") Long tripsId){
        existsTrip(tripsId);
        return TripRaitingResource.class;
    }    

    @Path("{tripsId: \\d+}/content")
    public Class<ContentResource> getContentsResource(@PathParam("tripsId") Long tripsId){
        existsTrip(tripsId);
        return ContentResource.class;
    }
    
}
