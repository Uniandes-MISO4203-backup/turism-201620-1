/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.turism.api.IContentLogic;
import co.edu.uniandes.csw.turism.dtos.detail.ContentDetailDTO;
import co.edu.uniandes.csw.turism.entities.ContentEntity;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * URI: trips/{tripsId: \\d+}/contents/
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContentResource {
    @Inject private IContentLogic contentLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    @PathParam("tripsId") private Long tripsId;
    
    /**
     * Convierte una lista de ContentEntity a una lista de ContentDetailDTO
     *
     * @param entityList Lista de ContentEntity a convertir
     * @return Lista de ContentDetailDTO convertida
     * @generated
     */
    private List<ContentDetailDTO> listEntity2DTO(List<ContentEntity> entityList){
        List<ContentDetailDTO> list = new ArrayList<>();
        for (ContentEntity entity : entityList) {
            list.add(new ContentDetailDTO(entity));
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
    public List<ContentDetailDTO> getContents() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", contentLogic.countContents());
            return listEntity2DTO(contentLogic.getContents(page, maxRecords, tripsId));
        }
        return listEntity2DTO(contentLogic.getContents(tripsId));
    }
    
       /**
     * Obtiene los datos de una instancia de Content a partir de su ID asociado a un Trip
     *
     * @param contentId Identificador de la instancia a consultar
     * @return Instancia de ContentDetailDTO con los datos del Content consultado
     * @generated
     */
    @GET
    @Path("{contentId: \\d+}")
    public ContentDetailDTO getContent(@PathParam("contentId") Long contentId) {
        ContentEntity entity = contentLogic.getContent(contentId);
        if (entity.getTrip() != null && !tripsId.equals(entity.getTrip().getId())) {
            throw new WebApplicationException(404);
        }
        return new ContentDetailDTO(entity);
    }
    
    /**
     * Asocia un Content existente a un Trip
     *
     * @param dto Objeto de TripDetailDTO con los datos nuevos
     * @return Objeto de TripDetailDTOcon los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public ContentDetailDTO createContent(ContentDetailDTO dto) {
        return new ContentDetailDTO(contentLogic.createContent(tripsId, dto.toEntity()));
    }
    
    
    /**
     * Actualiza la información de una instancia de Content.
     *
     * @param contentId Identificador de la instancia de Content a modificar
     * @param dto Instancia de ContentDetailDTO con los nuevos datos.
     * @return Instancia de ContentDetailDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{contentId: \\d+}")
    public ContentDetailDTO updateTrip(@PathParam("contentId") Long contentId, ContentDetailDTO dto) {
        ContentEntity entity = dto.toEntity();
        entity.setId(contentId);
        ContentEntity oldEntity = contentLogic.getContent(contentId);
        //entity.setCategory(oldEntity.getCategory());
        return new ContentDetailDTO(contentLogic.updateContent(tripsId, entity));
    }
    
    
    /**
     * Elimina una instancia de Content de la base de datos.
     *
     * @param contentId Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{contentId: \\d+}")
    public void deleteTrip(@PathParam("contentId") Long contentId) {
        contentLogic.deleteContent(contentId);
    }
    public void existsContent(Long contentsId){
        ContentDetailDTO content = getContent(contentsId);
        if (content== null) {
            throw new WebApplicationException(404);
        }
    }

}
