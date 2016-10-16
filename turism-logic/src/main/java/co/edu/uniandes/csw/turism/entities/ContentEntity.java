/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
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
