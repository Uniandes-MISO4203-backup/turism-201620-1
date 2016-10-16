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
import co.edu.uniandes.csw.turism.dtos.detail.RaitingDetailDTO;
import co.edu.uniandes.csw.turism.entities.RaitingEntity;
import java.util.ArrayList;
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
import co.edu.uniandes.csw.turism.api.IRaitingLogic;

/**
 *
 * @author da.salinas3247
 */
@Path("/raitings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RaitingResource {
    
    @Inject private IRaitingLogic raitingLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    /**
     * Convierte una lista de RaitingEntity a una lista de RaitingDetailDTO.
     *
     * @param entityList Lista de RaitingEntity a convertir.
     * @return Lista de RaitingDetailDTO convertida.
     */
    private List<RaitingDetailDTO> listEntity2DTO(List<RaitingEntity> entityList){
        List<RaitingDetailDTO> list = new ArrayList<>();
        for (RaitingEntity entity : entityList) {
            list.add(new RaitingDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Raiting
     *
     * @return Colección de objetos de RaitingDetailDTO
     */
    @GET
    public List<RaitingDetailDTO> getRaitings() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", raitingLogic.countRaitings());
            return listEntity2DTO(raitingLogic.getRaitings(page, maxRecords));
        }
        return listEntity2DTO(raitingLogic.getRaitings());
    }

    /**
     * Obtiene los datos de una instancia de Raiting a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de RaitingDetailDTO con los datos del Raiting consultado
     */
    @GET
    @Path("{id: \\d+}")
    public RaitingDetailDTO getRaiting(@PathParam("id") Long id) {
        return new RaitingDetailDTO(raitingLogic.getRaiting(id));
    }

    /**
     * Se encarga de crear un Raiting en la base de datos
     *
     * @param dto Objeto de RaitingDetailDTO con los datos nuevos
     * @return Objeto de RaitingDetailDTOcon los datos nuevos y su ID
     */
    @POST
    @StatusCreated
    public RaitingDetailDTO createRaiting(RaitingDetailDTO dto) {
        return new RaitingDetailDTO(raitingLogic.createRaiting(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Raiting
     *
     * @param id Identificador de la instancia de Raiting a modificar
     * @param dto Instancia de RaitingDetailDTO con los nuevos datos
     * @return Instancia de RaitingDetailDTO con los datos actualizados
     */
    @PUT
    @Path("{id: \\d+}")
    public RaitingDetailDTO updateRaiting(@PathParam("id") Long id, RaitingDetailDTO dto) {
        RaitingEntity entity = dto.toEntity();
        entity.setId(id);
        RaitingEntity oldEntity = raitingLogic.getRaiting(id);
        return new RaitingDetailDTO(raitingLogic.updateRaiting(entity));
    }

    /**
     * Elimina una instancia de Raiting de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteRaiting(@PathParam("id") Long id) {
        raitingLogic.deleteRaiting(id);
    }
    
    
    
}
