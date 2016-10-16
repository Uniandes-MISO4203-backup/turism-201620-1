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
package co.edu.uniandes.csw.turism.ejbs;

import co.edu.uniandes.csw.turism.api.IClientLogic;
import co.edu.uniandes.csw.turism.api.IItemLogic;
import co.edu.uniandes.csw.turism.api.ITripLogic;
import co.edu.uniandes.csw.turism.entities.ClientEntity;
import co.edu.uniandes.csw.turism.entities.ItemEntity;
import co.edu.uniandes.csw.turism.entities.RaitingEntity;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.persistence.RaitingPersistence;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import co.edu.uniandes.csw.turism.api.IRaitingLogic;

/**
 *
 * @author da.salinas3247
 */
public class RaitingLogic implements IRaitingLogic {
    
    @Inject private RaitingPersistence persistence;
    
    @Inject
    private IClientLogic clientLogic;
    
    @Inject
    private ITripLogic tripLogic;
    
    @Inject
    private IItemLogic itemLogic;
    
    //------------------------------------------------
    // Logica de solo RaitingLogic
    //------------------------------------------------
    
   /**
    * Conteo de Ratings
    * @return 
    */
    @Override
    public int countRaitings() {
       return persistence.count();
    }

    
    @Override
    public List<RaitingEntity> getRaitings() {
        return persistence.findAll();
    }
    
    @Override
    public List<RaitingEntity> getRaitings(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    
    
    @Override
    public RaitingEntity getRaiting(Long purchaseRaitingId) {
        try {
            return persistence.find(purchaseRaitingId);
        }catch(NoResultException e){
            throw new IllegalArgumentException("El raiting del viaje no existe");
        }
    }
    
    @Override
    public RaitingEntity createRaiting(RaitingEntity entity) {
        persistence.create(entity);
        return entity;
    }
    
     @Override
    public RaitingEntity updateRaiting(RaitingEntity entity) {
        return persistence.update(entity);
    }
    
    @Override
    public void deleteRaiting(Long id) {
        persistence.delete(id);
    }
      

    @Override
    public RaitingEntity createRaitingFromItem(Long clientId, Long itemId, RaitingEntity entity) {
        ClientEntity client = clientLogic.getClient(clientId);
        entity.setClient(client);

        ItemEntity item = itemLogic.getItem(itemId);
        TripEntity trip = item.getTrip();
        if (trip==null)
        {
            throw new IllegalArgumentException("El item debe tener un producto que calificar");
        }
        entity.setTrip(trip);
        
        persistence.create(entity);
        return entity;
    }

    @Override
    public RaitingEntity updateRaitingFromItem(Long clientId, Long itemId, RaitingEntity entity) {
        ClientEntity client = clientLogic.getClient(clientId);
        entity.setClient(client);

        ItemEntity item = itemLogic.getItem(itemId);
        TripEntity trip = item.getTrip();
        if (trip==null)
        {
            throw new IllegalArgumentException("El item debe tener un producto que calificar");
        }
        entity.setTrip(trip);
        return persistence.update(entity);
    }

    
   
    
}
