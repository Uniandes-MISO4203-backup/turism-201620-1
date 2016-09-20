/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.detail;

import co.edu.uniandes.csw.turism.dtos.minimum.AgencyDTO;
import co.edu.uniandes.csw.turism.dtos.minimum.FAQDTO;
import co.edu.uniandes.csw.turism.entities.FAQEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author lm.ariza10
 */
@XmlRootElement
public class FAQDetailDTO extends FAQDTO {
    
  @PodamExclude
  private AgencyDTO agency;

    /**
     * @generated
     */
    public FAQDetailDTO() {
        super();
    }

    /**
     * Crea un objeto TripDetailDTO a partir de un objeto TripEntity incluyendo los atributos de TripDTO.
     *
     * @param entity Entidad TripEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public FAQDetailDTO(FAQEntity entity) {
        super(entity);
        if (entity.getAgency()!=null){
        this.agency = new AgencyDTO(entity.getAgency());
        }
        
    }

    /**
     * Convierte un objeto TripDetailDTO a TripEntity incluyendo los atributos de TripDTO.
     *
     * @return Nueva objeto TripEntity.
     * @generated
     */
    @Override
    public FAQEntity toEntity() {
        FAQEntity entity = super.toEntity();
        if (this.getAgency()!=null){
        entity.setAgency(this.getAgency().toEntity());
        }
        return entity;
    }
 

    public AgencyDTO getAgency() {
        return agency;
    }

    public void setAgency(AgencyDTO agency) {
        this.agency = agency;
    }
    
}
