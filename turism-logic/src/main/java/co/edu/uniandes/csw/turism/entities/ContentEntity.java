/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author dh.mahecha
 */
@Entity
public class ContentEntity  extends BaseEntity implements Serializable{
    private String contentValue;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    @PodamExclude
    @ManyToOne
    private TripEntity trip;

    
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

    /**
     * Obtiene el atributo trip.
     *
     * @return atributo agency.
     * @generated
     */
    public TripEntity getTrip() {
        return trip;
    }

    /**
     * Establece el valor del atributo trip.
     *
     * @param trip nuevo valor del atributo
     * @generated
     */
    public void setTrip(TripEntity trip) {
        this.trip = trip;
    }
}
