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
import co.edu.uniandes.csw.turism.api.INewsLogic;
import co.edu.uniandes.csw.turism.api.ITripLogic;
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
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NewsResource {
    
    @Inject private INewsLogic newsLogic;  
    @Inject private ITripLogic tripLogic;
    @Context private HttpServletResponse response;

    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    @PathParam("tripsId") private Long tripId;
    
    
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
    
    private List<NewsEntity> listDTO2Entity(List<NewsDetailDTO> dtos){
        List<NewsEntity> list = new ArrayList<>();
        for(NewsDetailDTO dto:dtos){
            list.add(dto.toEntity());
        }
        return list;
    }
    
    
    /**
     * 
     * @return 
     */
     /*@GET
    public List<NewsDetailDTO> getAllNews() {
         System.out.println("Algo Hizo");
        List<NewsDTO> list = new ArrayList<>();
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", newsLogic.countNews());
            return listEntity2DTO(newsLogic.getAllNews(page, maxRecords));
        }
        return listEntity2DTO(newsLogic.getAllNews());
    }*/
    /**
     * * Obtiene una colección de instancias de NewsDetailDTO asociadas a una
     * instancia de Trip
     * @param tripId Identificador de la instancia de Trip
     * @return Colección de instancias de NewsDetailDTO asociadas a la instancia de Trip
     */
    
    @GET
     public List<NewsDTO> listNews(@PathParam("tripsId") Long tripId) {
        List<NewsDTO> list = new ArrayList<>();
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", newsLogic.countNews());
           for(NewsEntity newsEntity: newsLogic.getAllNews(page, maxRecords)) {
               list.add(new NewsDTO(newsEntity));
           }
        } else {
              for(NewsEntity newsEntity: newsLogic.getAllNews()) {
               list.add(new NewsDTO(newsEntity));
           }
        }
        return list;
    }

    /**
     * 
     * @param id
     * @return 
     *//*
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
    }*/
     
     /**
     * Obtiene una instancia de News asociada a una instancia de Trip
     *
     * @param tripId Identificador de la instancia de Trip
     * @param NewsId Identificador de la instancia de Tax
     * @generated
     */
    @GET
    @Path("{newsId: \\d+}")
    public NewsDTO getNews(@PathParam("newsId") Long newsId) {
        return new NewsDTO(newsLogic.getNews(newsId));
    }
    
    /**
     * Se encarga de crear un News y  lo asoci al trip
     * @param dto
     * @return 
     *//*
    @POST
    @StatusCreated
    public NewsDetailDTO createNeews(NewsDetailDTO dto) {
        return new NewsDetailDTO(newsLogic.createNews(dto.toEntity()));
    }*/
    
    /**
     *
     * Crea un News y lo asocia a un Trip
     *
     * @param tripId Identificador de la instancia de Trip
     * @param newsId Identificador de la instancia de News
     * @return Instancia de NewsDetailDTO que fue asociada a Trip
     * @generated
     */
     @POST
    @StatusCreated
    public NewsDetailDTO addNews(@PathParam("tripsId") Long tripId, NewsDTO dto) {
         NewsEntity entity = dto.toEntity();
         NewsEntity newsEntity = newsLogic.createNews(entity);
         tripLogic.addTax(tripId, newsEntity.getId());
        return new NewsDetailDTO(newsEntity);
    }
    
    /**
     * Actualiza la información de una instancia de News
     * @param id
     * @param dto
     * @return 
     */
    @PUT
    @Path("{id: \\d+}")
    public NewsDetailDTO updateNews(@PathParam("id") Long id, NewsDTO dto) {
        NewsEntity entity = dto.toEntity();
        entity.setId(id);
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
