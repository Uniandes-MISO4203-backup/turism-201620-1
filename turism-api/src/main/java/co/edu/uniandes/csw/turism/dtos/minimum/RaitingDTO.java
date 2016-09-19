/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.minimum;

import co.edu.uniandes.csw.turism.entities.RaitingEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author da.salinas3247
 */
@XmlRootElement
public class RaitingDTO implements Serializable{
    
    private Long id;
    private String name;
    private Integer value;
    private Date date;
    
    public RaitingDTO(){
    }

    public RaitingDTO(RaitingEntity purchaseRaitingEntity) {
        if (purchaseRaitingEntity != null)
        {
            this.id = purchaseRaitingEntity.getId();
            this.name = purchaseRaitingEntity.getName();
            this.value = purchaseRaitingEntity.getValue();
            this.date = purchaseRaitingEntity.getDate();
        }
    }
    
    public RaitingEntity toEntity(){
        
        RaitingEntity purchaseRaitingEntity = new RaitingEntity();
        
        purchaseRaitingEntity.setId(this.getId());
        purchaseRaitingEntity.setName(this.getName());
        purchaseRaitingEntity.setValue(this.getValue());
        purchaseRaitingEntity.setDate(this.getDate());
        
        return purchaseRaitingEntity;
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
      
}
