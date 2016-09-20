/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.turism.api.INewsLogic;
import co.edu.uniandes.csw.turism.dtos.detail.NewsDetailDTO;
import co.edu.uniandes.csw.turism.dtos.minimum.NewsDTO;
import co.edu.uniandes.csw.turism.entities.NewsEntity;
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
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 *
 * @author dp.espitia
 */
@Path("/news")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NewsResource {
      @Inject
    private INewsLogic newsLogic;
    @Context
    private HttpServletResponse response;

    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    @PathParam("tripId") private Long tripId;
    
    
    /**
     * Convierte una lista de NewsEntity a una lista de NewsDetailDTO.
     *
     * @param entityList Lista de NewsEntity a convertir.
     * @return Lista de NewsDetailDTO convertida.
     * @generated
     */
    private List<NewsDetailDTO> listEntity2DTO(List<NewsEntity> entityList){
        List<NewsDetailDTO> list = new ArrayList<>();
        for (NewsEntity entity : entityList) {
            list.add(new NewsDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * 
     * @return 
     */
     @GET
    public List<NewsDetailDTO> getAllNews() {
         System.out.println("Algo Hizo");
        List<NewsDTO> list = new ArrayList<>();
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", newsLogic.countNews());
            return listEntity2DTO(newsLogic.getAllNews(page, maxRecords));
        }
        return listEntity2DTO(newsLogic.getAllNews());
    }

    /**
     * 
     * @param id
     * @return 
     */
     @GET
    @Path("{id: \\d+}")
    public List<NewsDetailDTO> getNews(@PathParam("id") Long id) {
        System.out.println("Algo Hizo");
        List<NewsDTO> list = new ArrayList<>();
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", newsLogic.countNews());
            return listEntity2DTO(newsLogic.getNewsByTrip(page, maxRecords, tripId));
        }
        return listEntity2DTO(newsLogic.getNewsByTrip(tripId));
    }
    
    /**
     * Se encarga de crear un News en la base de datos
     * @param dto
     * @return 
     */
    @POST
    @StatusCreated
    public NewsDetailDTO createNeews(NewsDetailDTO dto) {
        return new NewsDetailDTO(newsLogic.createNews(dto.toEntity()));
    }
    
    /**
     * Actualiza la información de una instancia de News
     * @param id
     * @param dto
     * @return 
     */
    @PUT
    @Path("{id: \\d+}")
    public NewsDetailDTO updateNews(@PathParam("id") Long id, NewsDetailDTO dto) {
        NewsEntity entity = dto.toEntity();
        entity.setId(id);
        NewsEntity oldEntity = newsLogic.getNewsById(id);
        return new NewsDetailDTO(newsLogic.updateNews(entity));
    }

    /**
     *  Elimina una instancia de News de la base de datos
     * @param id 
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteNews(@PathParam("id") Long id) {
        newsLogic.deleteNews(id);
    }
}
