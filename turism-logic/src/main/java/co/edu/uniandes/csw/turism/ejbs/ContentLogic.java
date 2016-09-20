/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.ejbs;

import co.edu.uniandes.csw.turism.api.IContentLogic;
import co.edu.uniandes.csw.turism.api.ITripLogic;
import co.edu.uniandes.csw.turism.entities.AgencyEntity;
import co.edu.uniandes.csw.turism.entities.ContentEntity;
import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.persistence.ContentPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class ContentLogic implements IContentLogic{
 
    @Inject
    private ContentPersistence persistence;

    @Inject
    private ITripLogic tripLogic;   
    
   /**
     * Obtiene el número de registros de Content.
     *
     * @return Número de registros de Content.
     * @generated
     */
    public int countContents() {
        return persistence.count();
    }
    
    /**
     * Obtiene la lista de los registros de Content que pertenecen a un Trip.
     *
     * @param tripid id del Trip el cual es padre de los Contents.
     * @return Colección de objetos de ContentEntity.
     * @generated
     */
    @Override
    public List<ContentEntity> getContents(Long tripid) {
        TripEntity trip = tripLogic.getTrip(tripid);
        return trip.getContents();
    }
    
    /**
     * Obtiene la lista de los registros de Content que pertenecen a un Trip
     * indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @param tripid id del Trip el cual es padre de los Contents.
     * @return Colección de objetos de ContentEntity.
     * @generated
     */
    @Override
    public List<ContentEntity> getContents(Integer page, Integer maxRecords, Long tripid) {
            return persistence.findAll(page, maxRecords);
    }
    
    /**
     * Obtiene los datos de una instancia de Content a partir de su ID.
     *
     * @pre La existencia del elemento padre Trip se debe garantizar.
     * @param contentid) Identificador del Content a consultar
     * @return Instancia de ContentEntity con los datos del Content consultado.
     * @generated
     */
    @Override
    public ContentEntity getContent(Long contentid) {
        try {
            return persistence.find(contentid);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("El Content no existe");
        }
    } 
    
    /**
     * Se encarga de crear un Content en la base de datos.
     *
     * @param entity Objeto de ContentEntity con los datos nuevos
     * @param tripid id del Trip el cual sera padre del nuevo Content.
     * @return Objeto de ContentEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public ContentEntity createContent(Long tripid, ContentEntity entity) {
        TripEntity trip = tripLogic.getTrip(tripid);
        entity.setTrip(trip);
        entity = persistence.create(entity);
        return entity;
    }
    
    /**
     * Actualiza la información de una instancia de Content.
     *
     * @param entity Instancia de ContentEntity con los nuevos datos.
     * @param tripid id del Trip el cual sera padre del Content actualizado.
     * @return Instancia de ContentEntity con los datos actualizados.
     * @generated
     */
    @Override
    public ContentEntity updateContent(Long tripid, ContentEntity entity) {
        TripEntity trip = tripLogic.getTrip(tripid);
        entity.setTrip(trip);
        return persistence.update(entity);
    }
    
    /**
     * Elimina una instancia de Content de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @Override
    public void deleteContent(Long id) {
        ContentEntity old = getContent(id);
        persistence.delete(old.getId());
    }
}
