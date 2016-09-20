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
        
    public List<ContentEntity> getContents(Long tripid);
    public List<ContentEntity> getContents(Integer page, Integer maxRecords, Long tripid);
    public ContentEntity getContent(Long contentid);
    public ContentEntity createContent(Long tripid, ContentEntity entity);
    public ContentEntity updateContent(Long tripid, ContentEntity entity);
    public void deleteContent(Long id);
}
