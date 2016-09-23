/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.detail;

import co.edu.uniandes.csw.turism.dtos.minimum.ContentDTO;
import co.edu.uniandes.csw.turism.dtos.minimum.TripDTO;
import co.edu.uniandes.csw.turism.entities.ContentEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class ContentDetailDTO extends ContentDTO{
    
    @PodamExclude
    private TripDTO trip;

    /**
     * @generated
     */
    public ContentDetailDTO() {
        super();
    }
    
    /**
     * Crea un objeto ContentDetailDTO a partir de un objeto ContentEntity incluyendo los atributos de ContentDTO.
     *
     * @param entity Entidad ContentEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ContentDetailDTO(ContentEntity entity) {
        super();
    }

    /**
     * Convierte un objeto ContentDetailDTO a ContentEntity incluyendo los atributos de ContentDTO.
     *
     * @return Nueva objeto ContentEntity.
     * @generated
     */
    @Override
    public ContentEntity toEntity() {
        ContentEntity entity = super.toEntity();
        if (this.getTrip()!=null){
            entity.setTrip(this.getTrip().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el atributo trip.
     *
     * @return atributo trip.
     * @generated
     */
    public TripDTO getTrip() {
        return trip;
    }

    /**
     * Establece el valor del atributo trip.
     *
     * @param trip nuevo valor del atributo
     * @generated
     */
    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }    
    
}
