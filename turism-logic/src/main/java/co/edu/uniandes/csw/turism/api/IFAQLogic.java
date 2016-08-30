/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.api;

import co.edu.uniandes.csw.turism.entities.FAQEntity;
import java.util.List;

/**
 *
 * @author lm.ariza10
 */
public interface IFAQLogic {
     public int countFAQs();
    public List<FAQEntity> getFAQs();
    public List<FAQEntity> getFAQs(Integer page, Integer maxRecords);
    public FAQEntity getFAQ(Long id);
    public FAQEntity createFAQ(FAQEntity entity); 
    public FAQEntity updateFAQ(FAQEntity entity);
    public void deleteFAQ(Long id);
}
