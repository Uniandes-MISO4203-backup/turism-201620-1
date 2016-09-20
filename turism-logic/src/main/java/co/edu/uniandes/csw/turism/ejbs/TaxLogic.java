/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.ejbs;

import co.edu.uniandes.csw.turism.entities.TaxEntity;
import co.edu.uniandes.csw.turism.persistence.TaxPersistence;
import java.util.List;
import javax.inject.Inject;
import co.edu.uniandes.csw.turism.api.ITaxLogic;
import javax.ejb.Stateless;

/**
 *
 * @author lm.ariza10
 */
@Stateless
public class TaxLogic implements ITaxLogic{

    @Inject private TaxPersistence persistence;
    
    
    @Override
    public int countTaxes() {
        return persistence.count();
    }

    @Override
    public List<TaxEntity> getTaxes() {
        return persistence.findAll();
    }

    @Override
    public List<TaxEntity> getTaxes(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public TaxEntity getTax(Long id) {
        return persistence.find(id);
    }

    @Override
    public TaxEntity createTax(TaxEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public TaxEntity updateTax(TaxEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deleteTax(Long id) {
        persistence.delete(id);
    }
    
}
