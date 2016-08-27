/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.persistence;

import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.turism.entities.CommentEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author da.prieto1
 */
@Stateless
public class CommentPersistence extends CrudPersistence<CommentEntity> {
    
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
    protected Class<CommentEntity> getEntityClass() {
        return CommentEntity.class;
    }
}
