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
     * @param tripsId Identificador de la instancia a consultar
     * @return Instancia de TripDetailDTO con los datos del Trip consultado
     */
    @GET
    @Path("{tripsId: \\d+}")
    public TripDetailDTO getTripFromItem(@PathParam("tripsId") Long tripsId) {
        TripEntity entity = tripLogic.getTrip(tripsId);
        return new TripDetailDTO(entity);
    }
    
    @Path("{tripsId: \\d+}/raitings")
    public Class<TripRaitingResource> getTripRaitingResource(@PathParam("tripsId") Long tripsId) {
         
        return TripRaitingResource.class;
    }
}
