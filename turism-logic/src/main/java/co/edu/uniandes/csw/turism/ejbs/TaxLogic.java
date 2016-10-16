/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
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
    
    
    /**
     * 
     * @return 
     */
    @Override
    public int countTaxes() {
        return persistence.count();
    }

    /**
     * 
     * @return 
     */
    @Override
    public List<TaxEntity> getTaxes() {
        return persistence.findAll();
    }

    /**
     * 
     * @param page
     * @param maxRecords
     * @return 
     */
    @Override
    public List<TaxEntity> getTaxes(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    @Override
    public TaxEntity getTax(Long id) {
        return persistence.find(id);
    }

    /**
     * 
     * @param entity
     * @return 
     */
    @Override
    public TaxEntity createTax(TaxEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * 
     * @param entity
     * @return 
     */
    @Override
    public TaxEntity updateTax(TaxEntity entity) {
        return persistence.update(entity);
    }

    /**
     * 
     * @param id 
     */
    @Override
    public void deleteTax(Long id) {
        persistence.delete(id);
    }
    
}
