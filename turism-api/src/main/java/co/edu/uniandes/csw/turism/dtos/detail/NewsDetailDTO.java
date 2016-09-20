/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.detail;

import co.edu.uniandes.csw.turism.dtos.minimum.NewsDTO;
import co.edu.uniandes.csw.turism.dtos.minimum.TripDTO;
import co.edu.uniandes.csw.turism.entities.NewsEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author dp.espitia
 */

@XmlRootElement
public class NewsDetailDTO extends NewsDTO {
    
    
    @PodamExclude
    private TripDTO trip;

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public NewsDetailDTO(){
        super();
    }

     /**
     * Crea un objeto NewsDetailDTO a partir de un objeto NewsEntity incluyendo los atributos de NewsDTO.
     *
     * @param entity Entidad NewsEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public NewsDetailDTO(NewsEntity entity) {
        super(entity);
        if (entity.getTrip()!=null){
        this.trip = new TripDTO(entity.getTrip());
        }
        
    }
    
     /**
     * Convierte un objeto NewsDetailDTO a NewsEntity incluyendo los atributos de NewsDTO.
     *
     * @return Nueva objeto NewsEntity.
     * @generated
     */
    @Override
    public NewsEntity toEntity() {
        NewsEntity entity = super.toEntity();
        if (this.getTrip()!=null){
            entity.setTrip(this.getTrip().toEntity());
        }
        return entity;
    }
}
