/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.persistence;

import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.turism.entities.NewsEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author dp.espitia
 */
public class NewsPersistence extends CrudPersistence<NewsEntity>{

    @PersistenceContext(unitName="TurismPU")
    protected EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<NewsEntity> getEntityClass() {
        return NewsEntity.class;
    }
    
    public NewsEntity getNewsById(Long newsId) {
        TypedQuery<NewsEntity> q =   em.createQuery("select p from NewsEntity p where p.id = :newsId", NewsEntity.class);
        return q.getSingleResult();
    }
    public List<NewsEntity> getNewsByTrip(Integer page, Integer maxRecords,Long tripId) {
        TypedQuery<NewsEntity> q = em.createQuery("select p from NewsEntity p where p.trip.id = :tripId", NewsEntity.class);
        q.setParameter("tripId", tripId);
        if (page != null && maxRecords != null) {
            q.setFirstResult((page - 1) * maxRecords);
            q.setMaxResults(maxRecords);
        }
        return q.getResultList();
    }
}
