/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.turism.api.IContentLogic;
import co.edu.uniandes.csw.turism.api.ITripLogic;
import co.edu.uniandes.csw.turism.dtos.detail.ContentDetailDTO;
import co.edu.uniandes.csw.turism.dtos.minimum.ContentDTO;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * URI: agencys/{agencysId: \\d+}/trips/{tripsId: \\d+}/content
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContentResource {
    @Inject private ITripLogic tripLogic;
    @Context private HttpServletResponse response;
    
    @Inject private IContentLogic contentLogic;
    
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    @PathParam("tripsId") private Long tripsId;

    
    /**
     * Convierte una lista de ContentEntity a una lista ContentDetailDTO
     *
     * @param entityList Lista de ContentEntity a convertir
     * @return Lista de ContentDetailDTO convertida
     * @generated
     */
    private List<ContentEntity> contentlistEntity2DTO(List<ContentDetailDTO> dtos){
        List<ContentEntity> list = new ArrayList<>();
        for (ContentDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    
    /**
     * Obtiene una colección de instancias de ContentDetailDTO asociadas a una
     * instancia de Trip
     *
     * @param tripsId Identificador de la instancia de Trip
     * @return Colección de instancias de ContentDetailDTO asociadas a la instancia de Trip
     * @generated
     */
    @GET
    public List<ContentDTO> listContent(@PathParam("tripsId") Long tripsId) {
        List<ContentDTO> list = new ArrayList<>();
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", contentLogic.countContents());
           for(ContentEntity contentEntity: contentLogic.getContents(page, maxRecords)) {
               list.add(new ContentDTO(contentEntity));
           }
        } else {
              for(ContentEntity contentEntity: contentLogic.getContents()) {
               list.add(new ContentDTO(contentEntity));
           }
        }
        return list;
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
    public ContentDTO getContent(@PathParam("contentId") Long contentId) {
        return new ContentDTO(contentLogic.getContent(contentId));
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
    public ContentDetailDTO addContent(@PathParam("tripsId") Long tripsId, ContentDTO dto) {
        ContentEntity entity = dto.toEntity();
        ContentEntity contentEntity = contentLogic.createContent(entity);
        tripLogic.addContent(tripsId, contentEntity.getId());
        return new ContentDetailDTO(contentEntity);
    }
    
    
    /**
     * Actualiza la información de una instancia de Content.
     *
     * @param id Identificador de la instancia de Content a modificar
     * @param dto Instancia de ContentDetailDTO con los nuevos datos.
     * @return Instancia de ContentDetailDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{contentId: \\d+}")
    public ContentDTO updateContent(@PathParam("id") Long id, ContentDTO dto) {
        ContentEntity entity = dto.toEntity();
        entity.setId(id);
        return new ContentDTO(contentLogic.updateContent(entity));
    }
    
    
    /**
     * Elimina una instancia de Content de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteContent(@PathParam("id") Long id) {
        contentLogic.deleteContent(id);
    }
}
