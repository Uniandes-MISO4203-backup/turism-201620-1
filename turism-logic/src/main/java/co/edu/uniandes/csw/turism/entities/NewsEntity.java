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
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author dp.espitia
 */
@Entity
public class NewsEntity extends BaseEntity implements Serializable {
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private String content;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    @PodamExclude
    @ManyToOne
    private TripEntity  trip;

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

  
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
