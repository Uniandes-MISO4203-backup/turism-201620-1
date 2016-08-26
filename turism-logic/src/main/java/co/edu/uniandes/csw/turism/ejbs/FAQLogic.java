/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.ejbs;

import co.edu.uniandes.csw.turism.api.IFAQ;
import co.edu.uniandes.csw.turism.entities.FAQEntity;
import co.edu.uniandes.csw.turism.persistence.FAQPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author lm.ariza10
 */
public class FAQLogic implements IFAQ{

    @Inject private FAQPersistence persistence;
    
    @Override
    public int countFAQs() {
        return persistence.count();
    }

    @Override
    public List<FAQEntity> getFAQs() {
        return persistence.findAll();
    }

    @Override
    public List<FAQEntity> getFAQs(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public FAQEntity getFAQ(Long id) {
        return persistence.find(id);
    }

    @Override
    public FAQEntity createFAQ(FAQEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public FAQEntity updateFAQ(FAQEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deleteFAQ(Long id) {
        persistence.delete(id);
    }
    
}
