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
