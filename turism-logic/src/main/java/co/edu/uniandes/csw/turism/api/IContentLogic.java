/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.api;

import co.edu.uniandes.csw.turism.entities.ContentEntity;
import java.util.List;

/**
 *
 * @author dh.mahecha
 */
public interface IContentLogic {
    public int countContents();
        
    public List<ContentEntity> getContents();
    public List<ContentEntity> getContents(Integer page, Integer maxRecords);
    public ContentEntity getContent(Long contentid);
    public ContentEntity createContent(ContentEntity entity);
    public ContentEntity updateContent(ContentEntity entity);
    public void deleteContent(Long id);
}
