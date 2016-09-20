/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.minimum;

import co.edu.uniandes.csw.turism.entities.ContentEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @generated
 */
@XmlRootElement
public class ContentDTO implements Serializable{
    private Long id;
    private String name;
    private String contentValue;
    private Date date;

    
    /**
     * Crea un objeto ContentDTO a partir de un objeto ContentEntity.
     *
     * @param entity Entidad ContentEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */    
    public ContentDTO(ContentEntity entity) {
        if (entity!=null){
            this.id = entity.getId();
            this.name = entity.getName();
            this.contentValue = entity.getContentValue();
            this.date = entity.getDate();
        }    
    }

    public ContentDTO() {
    }

    /**
     * Convierte un objeto ContentDTO a ContentEntity.
     *
     * @return Nueva objeto ContentEntity.
     * @generated
     */
    public ContentEntity toEntity() {
        ContentEntity entity = new ContentEntity();
        entity.setId(this.getId());
 	entity.setDate(this.getDate());
        entity.setContentValue(this.getContentValue());       
    return entity;
    }   
    
     /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     * @generated
     */   
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el atributo name.
     *
     * @return atributo name.
     * @generated
     */    
    public String getName() {
        return name;
    }

    /**
     * Establece el valor del atributo name.
     *
     * @param name nuevo valor del atributo
     * @generated
     */    
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el atributo contentValue.
     * 
     * @return atributo contentValue.
     * @generated
     */    
    public String getContentValue() {
        return contentValue;
    }

    /**
     * Establece el valor del atributo contentValue.
     * 
     * @param contentValue nuevo valor del atributo
     * @generated
     */    
    
    public void setContentValue(String contentValue) {
        this.contentValue = contentValue;
    }

    
    /**
     * Obtiene el atributo date.
     * 
     * @return atributo date.
     * @generated
     */
    public Date getDate() {
        return date;
    }

    /**
     * Establece el valor del atributo date.
     * 
     * @param date nuevo valor del atributo
     * @generated
     */    
    public void setDate(Date date) {
        this.date = date;
    }
   
}
