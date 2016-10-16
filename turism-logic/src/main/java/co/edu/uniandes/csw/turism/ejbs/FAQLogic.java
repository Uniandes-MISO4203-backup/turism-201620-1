/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.ejbs;

import co.edu.uniandes.csw.turism.entities.FAQEntity;
import co.edu.uniandes.csw.turism.persistence.FAQPersistence;
import java.util.List;
import javax.inject.Inject;
import co.edu.uniandes.csw.turism.api.IFAQLogic;
import javax.ejb.Stateless;

/**
 *
 * @author lm.ariza10
 */
@Stateless
public class FAQLogic implements IFAQLogic{

    @Inject private FAQPersistence persistence;
    
    /**
     * Conteo de FAQs
     * @return int conteo de FAQs
     */
    @Override
    public int countFAQs() {
        return persistence.count();
    }

    /**
     * Obtiene todos los FAQs
     * @return 
     */
    @Override
    public List<FAQEntity> getFAQs() {
        return persistence.findAll();
    }
    
    /**
     * Obtiene una lista de FAQs paginados
     * @param page
     * @param maxRecords
     * @return 
     */
    @Override
    public List<FAQEntity> getFAQs(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    
    /**
     * Obtiene un FAQ
     * @param id
     * @return 
     */
    @Override
    public FAQEntity getFAQ(Long id) {
        return persistence.find(id);
    }
    
    /**
     * Crea un FAQ
     * @param entity
     * @return 
     */
    @Override
    public FAQEntity createFAQ(FAQEntity entity) {
        persistence.create(entity);
        return entity;
    }
    
    /**
     * Actualiza un FAQ
     * @param entity
     * @return 
     */
    @Override
    public FAQEntity updateFAQ(FAQEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina un FAQ
     * @param id 
     */
    @Override
    public void deleteFAQ(Long id) {
        persistence.delete(id);
    }
    
}
