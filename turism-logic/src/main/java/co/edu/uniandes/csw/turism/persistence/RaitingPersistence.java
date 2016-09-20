/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.persistence;

import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.turism.entities.ItemEntity;
import co.edu.uniandes.csw.turism.entities.RaitingEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author da.salinas3247
 */
@Stateless
public class RaitingPersistence extends CrudPersistence<RaitingEntity>{
   
    @PersistenceContext(unitName="TurismPU")
    protected EntityManager em;
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<RaitingEntity> getEntityClass() {
        return RaitingEntity.class;
    }
    
    
    public List<RaitingEntity> findAll(Integer page, Integer maxRecords, Long tripid) {
        TypedQuery<RaitingEntity> q = em.createQuery("select p from RaitingEntity p where (p.trip.id = :tripid)", RaitingEntity.class);
        q.setParameter("tripid", tripid);
        if (page != null && maxRecords != null) {
            q.setFirstResult((page - 1) * maxRecords);
            q.setMaxResults(maxRecords);
        }
        return q.getResultList();
    }
}
