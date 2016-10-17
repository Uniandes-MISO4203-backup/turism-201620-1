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

import co.edu.uniandes.csw.turism.dtos.minimum.ClientDTO;
import co.edu.uniandes.csw.turism.dtos.minimum.ItemDTO;
import co.edu.uniandes.csw.turism.dtos.minimum.RaitingDTO;
import co.edu.uniandes.csw.turism.dtos.minimum.TripDTO;
import co.edu.uniandes.csw.turism.entities.RaitingEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author da.salinas3247
 */
@XmlRootElement
public class RaitingDetailDTO extends RaitingDTO{
    
    @PodamExclude
    private TripDTO trip;
    
    @PodamExclude
    private ItemDTO item;

    @PodamExclude
    private ClientDTO client;

    public RaitingDetailDTO() {
        super();
    }
    
    public RaitingDetailDTO(RaitingEntity entity ){
        super(entity);
        if (entity.getTrip() != null) {
            this.trip = new TripDTO(entity.getTrip());
        }
        if (entity.getClient() != null) {
            this.client = new ClientDTO(entity.getClient());
        }
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public RaitingEntity toEntity(){
        RaitingEntity entity = super.toEntity();
        if (this.getTrip() != null) {
            entity.setTrip(this.getTrip().toEntity());
        }
        if (this.getClient() != null) {
            entity.setClient(this.getClient().toEntity());
        }
        return entity;
    }

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }
    
    
}
