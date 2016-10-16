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
