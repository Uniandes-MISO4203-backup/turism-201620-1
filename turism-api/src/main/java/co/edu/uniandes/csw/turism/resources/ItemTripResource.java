/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.turism.api.IItemLogic;
import co.edu.uniandes.csw.turism.api.ITripLogic;
import co.edu.uniandes.csw.turism.dtos.detail.RaitingDetailDTO;
import co.edu.uniandes.csw.turism.dtos.detail.TripDetailDTO;
import co.edu.uniandes.csw.turism.entities.ItemEntity;
import co.edu.uniandes.csw.turism.entities.RaitingEntity;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author da.salinas3247
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemTripResource {

    
    
    @Inject
    private ITripLogic tripLogic;
    
    @Inject
    private IItemLogic itemLogic;
    
    private List<RaitingDetailDTO> raitingListEntity2DTO(List<RaitingEntity> entityList) {
        List<RaitingDetailDTO> list = new ArrayList<>();
        for (RaitingEntity entity : entityList) {
            list.add(new RaitingDetailDTO(entity));
        }
        return list;
    }
    
    
        /**
     * Obtiene la lista de los registros de Trip asociados a un Agency
     *
     * @param itemsId Identificador de la instancia a consultar 
     * @return Colección de objetos de TripDetailDTO
     */
    @GET
    public List<TripDetailDTO> getTripsFromItem(@PathParam("itemsId") Long itemsId) {
        
        ItemEntity item = itemLogic.getItem(itemsId);
        TripEntity entity = item.getTrip();
        
        List<TripDetailDTO> list = new ArrayList<>();
        list.add(new TripDetailDTO(entity));
        
        return list;
    }
    

    /**
     * Obtiene los datos de una instancia de Trip a partir de su ID asociado a
     * un Agency
     *
     * @param tripId Identificador de la instancia a consultar
     * @return Instancia de TripDetailDTO con los datos del Trip consultado
     */
    @GET
    @Path("{tripId: \\d+}")
    public TripDetailDTO getTripFromItem(@PathParam("tripId") Long tripId) {
        TripEntity entity = tripLogic.getTrip(tripId);
        return new TripDetailDTO(entity);
    }
    
    @GET
    @Path("{tripsId: \\d+}/raitings")
    public List<RaitingDetailDTO> listRaitingFromItem(@PathParam("tripsId") Long tripsId) {
        return raitingListEntity2DTO(tripLogic.listRaiting(tripsId));
    }
}
