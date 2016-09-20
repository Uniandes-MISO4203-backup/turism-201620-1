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

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Temporal;

/**
 * @generated
 */
@Entity
public class TripEntity extends BaseEntity implements Serializable {

    private String image;

    private Long price;
    
    private Integer quota;
    
    private Integer duration;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    private String origin;
    
    private String destination;    

    private String transportType;
    
    private String specialRequirements;


    @PodamExclude
    @ManyToOne
    private AgencyEntity agency;

    @PodamExclude
    @OneToMany
    private List<CategoryEntity> category = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "trip")
    private List<CommentEntity> comments = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaxEntity> taxes = new ArrayList<>();
    @PodamExclude
    @OneToMany(mappedBy = "trip")
    private List<RaitingEntity> raitings = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "trip")
    private List<NewsEntity> news = new ArrayList<>();


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
     * Obtiene el atributo tipo de transporte
     * @return  atributo transportType
     */
    public String getTransportType() {
        return transportType;
    }
    
    /**
     * Establece el valor del atributo transportType
     * @param transportType nuevo valor del aributo
     */
    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }
    
    /**
     * Obtiene el aributo requerimientos especiales
     * (en cuanto a ropa, elementos deportivos especiales, etc.
     * @return atributo specialRequirements
     */
    public String getSpecialRequirements() {
        return specialRequirements;
    }

    /**
     * Establece el valor del atributo specialRequirements
     * @param specialRequirements nuevo valor del atributo
     */
    public void setSpecialRequirements(String specialRequirements) {
        this.specialRequirements = specialRequirements;
    }
        
    /**
     * Obtiene el atributo agency.
     *
     * @return atributo agency.
     * @generated
     */
    public AgencyEntity getAgency() {
        return agency;
    }

    /**
     * Establece el valor del atributo agency.
     *
     * @param agency nuevo valor del atributo
     * @generated
     */
    public void setAgency(AgencyEntity agency) {
        this.agency = agency;
    }

    /**
     * Obtiene la colección de category.
     *
     * @return colección category.
     * @generated
     */
    public List<CategoryEntity> getCategory() {
        return category;
    }

    /**
     * Establece el valor de la colección de category.
     *
     * @param category nuevo valor de la colección.
     * @generated
     */
    public void setCategory(List<CategoryEntity> category) {
        this.category = category;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }
    
    /**
     * Obtiene el atributo quota.
     * @return quota
     */
    public Integer getQuota(){
        return quota;
    }

    /**
     * Establece el valor del atributo duration.
     * @param duration 
     */
    public void setDuration(Integer duration){
        this.duration = duration;
    }
    
     /**
     * Obtiene el atributo quota.
     * @return quota
     */
    public Integer getDuration(){
        return duration;
    }

    /**
     * Establece el valor del atributo quota.
     * @param quota 
     */
    public void setQuota(Integer quota){
        this.quota = quota;
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
    

    public List<TaxEntity> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<TaxEntity> taxes) {
        this.taxes = taxes;
    }

    public List<RaitingEntity> getRaitings() {
        return raitings;
    }

    public void setRaitings(List<RaitingEntity> raitings) {
        this.raitings = raitings;
    }
    

     /**
     * Obtiene la colección de news.
     *
     * @return colección news.
     * @generated
     */
    public List<NewsEntity> getNews() {
        return news;
    }
    
    /**
     * Establece el valor de la colección de trips.
     *
     * @param trips nuevo valor de la colección.
     * @generated
     */
    public void setNews(List<NewsEntity> news) {
        this.news = news;
    }
}

