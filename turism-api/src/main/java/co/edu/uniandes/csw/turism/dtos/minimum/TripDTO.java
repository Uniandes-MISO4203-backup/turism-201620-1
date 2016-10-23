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
package co.edu.uniandes.csw.turism.dtos.minimum;

import co.edu.uniandes.csw.turism.entities.TripEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @generated
 */
@XmlRootElement
public class TripDTO implements Serializable{

    private Long id;
    private String name;
    private String image;
    private Long price;
    private Integer quota;
    private Integer duration;
    private Date date;
    private String origin;
    private String destination;    
    private String transportType;
    private String specialRequirements;
    private String dailyDescription;
    private String includesDescription;
   

    /**
     * Constructor de la clase DTO mínima
     */
    public TripDTO() {
    }

    /**
     * Crea un objeto TripDTO a partir de un objeto TripEntity.
     *
     * @param entity Entidad TripEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public TripDTO(TripEntity entity) {
	   if (entity!=null){
        this.id=entity.getId();
        this.name=entity.getName();
        this.image=entity.getImage();
        this.price=entity.getPrice();
        this.quota=entity.getQuota();
        this.duration=entity.getDuration();
        this.date=entity.getDate();
        this.origin=entity.getOrigin();
        this.destination=entity.getDestination();        
        this.transportType=entity.getTransportType();
        this.specialRequirements=entity.getSpecialRequirements();
        this.dailyDescription=entity.getDailyDescription();
        this.includesDescription=entity.getIncludesDescription();

       }
    }

    /**
     * Convierte un objeto TripDTO a TripEntity.
     *
     * @return Nueva objeto TripEntity.
     * @generated
     */
    public TripEntity toEntity() {
        TripEntity entity = new TripEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setImage(this.getImage());
        entity.setPrice(this.getPrice());
        entity.setQuota(this.getQuota());
        entity.setDuration(duration);
 	entity.setDate(this.getDate());
        entity.setOrigin(this.getOrigin());
        entity.setDestination(this.getDestination());       
        entity.setTransportType(this.getTransportType());
        entity.setSpecialRequirements(this.getSpecialRequirements());
        entity.setDailyDescription(this.getDailyDescription());
        entity.setIncludesDescription(this.getIncludesDescription());

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
     * Obtiene el atributo image.
     *
     * @return atributo image.
     * @generated
     */
    public String getImage() {
        return image;
    }

    /**
     * Establece el valor del atributo image.
     *
     * @param image nuevo valor del atributo
     * @generated
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Obtiene el atributo price.
     *
     * @return atributo price.
     * @generated
     */
    public Long getPrice() {
        return price;
    }

    /**
     * Establece el valor del atributo price.
     *
     * @param price nuevo valor del atributo
     * @generated
     */
    public void setPrice(Long price) {
        this.price = price;
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
     * Obtiene el atributo origin.
     * 
     * @return atributo origin.
     * @generated
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Establece el valor del atributo origin.
     * 
     * @param origin nuevo valor del atributo
     * @generated
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    
    /**
     * Obtiene el atributo destination.
     * 
     * @return atributo destination.
     * @generated
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Establece el valor del atributo destination.
     * 
     * @param destination nuevo valor del atributo
     * @generated
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    

    /**
     * Obtiene el atributo quota.
     *
     * @return atributo quota.
     * @generated
     */
    public Integer getQuota() {
        return quota;
    }

    /**
     * Establece el valor del atributo duration.
     *
     * @param duration nuevo valor del atributo
     * @generated
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    
     /**
     * Obtiene el atributo duration.
     *
     * @return atributo duration.
     * @generated
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Establece el valor del atributo name.
     *
     * @param quota nuevo valor del atributo
     * @generated
     */
    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    /*
     * Obtiene el atributo transportType
     * @return atributo transportType
     */
    public String getTransportType() {
        return transportType;
    }
    
    /**
     * Establece el valor del atributo transportType
     * @param transportType  nuevo valor del atributo
     */
    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }
    
    /**
     * Obtiene el atributo specialRequirements
     * @return atributo specialRequirements
     */
    public String getSpecialRequirements() {
        return specialRequirements;
    }

    /**
     * Establce el valor del atributo specialRequirements
     * @param specialRequirements nuevo valor del atributo
     */
    public void setSpecialRequirements(String specialRequirements) {
        this.specialRequirements = specialRequirements;
    }

    /**
     * Obtiene el atributo dailyDescription
     * @return atributo dailyDescription
     */
    public String getDailyDescription() {
        return dailyDescription;
    }

    /**
     * Establce el valor del atributo dailyDescription
     * @param dailyDescription nuevo valor del atributo
     */
    public void setDailyDescription(String dailyDescription) {
        this.dailyDescription = dailyDescription;
    }

    /**
     * Obtiene el atributo includesDescription
     * @return atributo includesDescription
     */
    public String getIncludesDescription() {
        return includesDescription;
    }

    /**
     * Establce el valor del atributo includesDescription
     * @param includesDescription nuevo valor del atributo
     */
    public void setIncludesDescription(String includesDescription) {
        this.includesDescription = includesDescription;
    }
    
    

}
