/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.api;

import co.edu.uniandes.csw.turism.entities.TaxEntity;
import java.util.List;

/**
 *
 * @author lm.ariza10
 */
public interface ITaxLogic {
     public int countTaxes();
    public List<TaxEntity> getTaxes();
    public List<TaxEntity> getTaxes(Integer page, Integer maxRecords);
    public TaxEntity getTax(Long id);
    public TaxEntity createTax(TaxEntity entity); 
    public TaxEntity updateTax(TaxEntity entity);
    public void deleteTax(Long id);
}
