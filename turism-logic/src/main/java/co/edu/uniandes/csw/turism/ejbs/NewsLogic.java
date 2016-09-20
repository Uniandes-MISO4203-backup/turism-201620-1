 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.ejbs;

import co.edu.uniandes.csw.turism.api.INewsLogic;
import co.edu.uniandes.csw.turism.api.ITripLogic;
import co.edu.uniandes.csw.turism.entities.NewsEntity;
import co.edu.uniandes.csw.turism.persistence.NewsPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author dp.espitia
 */
@Stateless
public class NewsLogic implements INewsLogic {

    @Inject private NewsPersistence persistence;
    
    @Inject
    private ITripLogic tripLogic;
    
    @Override
    public int countNews() {
        return persistence.count();
    }
    
    @Override
    public List<NewsEntity> getAllNews() {
        return persistence.findAll();
    }

    @Override
    public List<NewsEntity> getAllNews(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public NewsEntity createNews(NewsEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public NewsEntity updateNews(NewsEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deleteNews(Long id) {
        persistence.delete(id);
    }
    
    /**
     * Obtiene la lista de los registros de Category indicando su padre.
     *
     * * @param parentCategoryid Categoria padre.
     * @return Colección de objetos de CategoryEntity.
     * @generated
     */
    @Override
    public List<NewsEntity> getNewsByTrip(Integer page, Integer maxRecords,Long tripId) {
         //TripEntity trip = tripLogic.getTrip(tripId);
        //return trip.getNews();
        return persistence.getNewsByTrip(page,maxRecords,tripId);
    }
     /**
     * Obtiene la lista de los registros de Category indicando su padre.
     *
     * * @param parentCategoryid Categoria padre.
     * @return Colección de objetos de CategoryEntity.
     * @generated
     */
    @Override
    public List<NewsEntity> getNewsByTrip(Long tripId) {
         /*TripEntity trip = tripLogic.getTrip(tripId);
        return trip.getNews();*/
         return persistence.getNewsByTrip(null,null,tripId);
    }
    
    /**
     * Obtiene  el registro de News indicando su id.
     *
     * * @param newsId .
     * @return  objeto de NewsEntity.
     * @generated
     */
    @Override
    public NewsEntity getNewsById(Long newsId) {
        return persistence.getNewsById(newsId);
    }
    
}
