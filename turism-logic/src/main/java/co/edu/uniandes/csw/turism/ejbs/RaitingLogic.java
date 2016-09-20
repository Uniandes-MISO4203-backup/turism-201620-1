/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
