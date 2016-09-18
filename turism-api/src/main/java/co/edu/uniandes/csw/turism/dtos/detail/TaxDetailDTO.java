/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.detail;

import co.edu.uniandes.csw.turism.dtos.minimum.TripDTO;
import co.edu.uniandes.csw.turism.dtos.minimum.TaxDTO;
import co.edu.uniandes.csw.turism.dtos.minimum.TaxDTO;
import co.edu.uniandes.csw.turism.dtos.minimum.TripDTO;
import co.edu.uniandes.csw.turism.entities.TaxEntity;
import co.edu.uniandes.csw.turism.entities.TaxEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author lm.ariza10
 */
@XmlRootElement
public class TaxDetailDTO extends TaxDTO {
    
     @PodamExclude
  private TripDTO trip;

    /**
     * @generated
     */
    public TaxDetailDTO() {
        super();
    }

    /**
     * Crea un objeto TripDetailDTO a partir de un objeto TripEntity incluyendo los atributos de TripDTO.
     *
     * @param entity Entidad TripEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public TaxDetailDTO(TaxEntity entity) {
        super();        
    }

    /**
     * Convierte un objeto TripDetailDTO a TripEntity incluyendo los atributos de TripDTO.
     *
     * @return Nueva objeto TripEntity.
     * @generated
     */
    @Override
    public TaxEntity toEntity() {
        TaxEntity entity = super.toEntity();
        if (this.getTrip()!=null){
        entity.setTrip(this.getTrip().toEntity());
        }
        return entity;
    }
 

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }
    
}
