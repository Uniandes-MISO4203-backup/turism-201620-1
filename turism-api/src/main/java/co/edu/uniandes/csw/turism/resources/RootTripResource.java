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

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.turism.api.ITripLogic;
import co.edu.uniandes.csw.turism.dtos.detail.TripDetailDTO;
import co.edu.uniandes.csw.turism.ejbs.RaitingLogic;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;

/**
 * @generated
 */
@Path("/trips")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RootTripResource {

    @Inject
    private ITripLogic tripLogic;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("limit")
    private Integer maxRecords;

    /**
     * Convierte una lista de TripEntity a una lista de TripBasicDTO
     *
     * @param entityList Lista de TripEntity a convertir
     * @return Lista de TripBasicDTO convertida
     * @generated
     */
    private List<TripDetailDTO> listEntity2DTO(List<TripEntity> entityList) {
        List<TripDetailDTO> list = new ArrayList<>();
        for (TripEntity entity : entityList) {
            list.add(new TripDetailDTO(entity));
        }
        return list;
    }

    public void existsTrip(Long tripsId) {
        TripDetailDTO trip = getTrip(tripsId);
        if (trip == null) {
            throw new WebApplicationException(404);
        }
    }

    /**
     * Obtiene la lista de los registros de Artist
     *
     * @return Colección de objetos de TripDetailDTO
     * @generated
     */
    @GET
    public List<TripDetailDTO> getTrips() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", tripLogic.countTrips());
            return listEntity2DTO(tripLogic.getTrips(page, maxRecords, null));
        }
        return listEntity2DTO(tripLogic.getTrips(null, null, null));
    }

    /**
     * Obtiene la lista de los registros de Trip por categoria.
     *
     * @param categoryid id de la categoria.
     * @return Colección de objetos de TripDetailDTO.
     * @generated
     */
    @GET
    @Path("{categoryid: \\d+}")
    public List<TripDetailDTO> getTripByCategory(@PathParam("categoryid") Long categoryid) {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", tripLogic.countTrips());
            return listEntity2DTO(tripLogic.getTripByCategory(page, maxRecords, categoryid));
        }
        return listEntity2DTO(tripLogic.getTripByCategory(null, null, categoryid));
    }

    /**
     * Obtiene los datos de una instancia de Trip a partir de su ID SIN
     * asociación a una agencia o a un cliente
     *
     * @param tripId Identificador de la instancia a consultar
     * @return Instancia de TripDetailDTO con los datos del Trip consultado
     * @
     */
    @GET
    @Path("/detail/{tripId: \\d+}")
    public TripDetailDTO getTrip(@PathParam("tripId") Long tripId) {
        Logger.getLogger(RootTripResource.class.getName()).log(Level.INFO, ("GET TRIP DIRECTO= " + tripId));
        TripEntity entity = tripLogic.getTrip(tripId);
        return new TripDetailDTO(entity);

    }

    @Path("{tripsId: \\d+}/raitings")
    public Class<TripRaitingResource> getTripRaitingResource(@PathParam("tripsId") Long tripsId) {
        existsTrip(tripsId);
        return TripRaitingResource.class;
    }

}
