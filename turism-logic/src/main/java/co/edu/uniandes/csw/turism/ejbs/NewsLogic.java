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
    public NewsEntity getNews(Long id) {
        return persistence.find(id);
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
}
