/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.minimum;

import co.edu.uniandes.csw.turism.entities.NewsEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dp.espitia
 */
@XmlRootElement
public class NewsDTO implements Serializable{
    
    private Long id;
    private String name;
    private String content;
    private Date date;
    
    /**
     * @generated
     */
    public NewsDTO(){
        
    }

    /**
     * 
     * @param entity 
     */
    public NewsDTO(NewsEntity entity) {
        if (entity!=null){
            this.id = entity.getId();
            this.name = entity.getName();
            this.content = entity.getContent();
            this.date = entity.getDate();
        }
    }
    
    /**
     * Convierte un objeto NewsDTO a NewsEntity.
     *
     * @return Nuevo objeto NewsEntity.
     * @generated
     */
    public NewsEntity toEntity() {
        NewsEntity entity = new NewsEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setContent(this.getContent());
 	entity.setDate(this.getDate());    
    return entity;
    }
    
    
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
     public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
