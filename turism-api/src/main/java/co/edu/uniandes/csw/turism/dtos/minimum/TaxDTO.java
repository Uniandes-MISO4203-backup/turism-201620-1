/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.minimum;

import co.edu.uniandes.csw.turism.entities.TaxEntity;
import co.edu.uniandes.csw.turism.entities.TaxEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lm.ariza10
 */
@XmlRootElement
public class TaxDTO implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Long value;
    
     /**
     * @generated
     */
    public TaxDTO() {
    }

    /**
     * Crea un objeto TripDTO a partir de un objeto TripEntity.
     *
     * @param entity Entidad TripEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public TaxDTO(TaxEntity entity) {
	if (entity!=null){
        this.id=entity.getId();
        this.name=entity.getName();
        this.description=entity.getDescription();
        this.value=entity.getValue();
       }
    }

    /**
     * Convierte un objeto TripDTO a TripEntity.
     *
     * @return Nueva objeto TripEntity.
     * @generated
     */
    public TaxEntity toEntity() {
        TaxEntity entity = new TaxEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setDescription(this.getDescription());
        entity.setValue(this.getValue());
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
   
     public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
    
}
