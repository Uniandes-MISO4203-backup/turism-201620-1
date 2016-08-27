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
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author da.prieto1
 */
@Entity
public class CommentEntity extends BaseEntity implements Serializable{
    
    @PodamExclude
    @ManyToOne
    private ClientEntity author;
    
    @PodamExclude
    @ManyToOne
    private TripEntity trip;
    
    private String text;
    private Date date;

    public ClientEntity getAuthor() {
        return author;
    }

    public void setAuthor(ClientEntity author) {
        this.author = author;
    }

    public TripEntity getTrip() {
        return trip;
    }

    public void setTrip(TripEntity trip) {
        this.trip = trip;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   
    
    
}
