/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.api;

import co.edu.uniandes.csw.turism.entities.NewsEntity;
import java.util.List;

/**
 *
 * @author dp.espitia
 */
public interface INewsLogic {
    
    public int countNews();
    public List<NewsEntity> getAllNews();
    public List<NewsEntity> getAllNews(Integer page, Integer maxRecords);
    public NewsEntity getNews(Long id);
    public NewsEntity createNews(NewsEntity entity);
    public NewsEntity updateNews(NewsEntity entity);
    public void deleteNews(Long id);

    
}
