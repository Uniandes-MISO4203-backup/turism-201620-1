/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.api;

import co.edu.uniandes.csw.turism.entities.RaitingEntity;
import java.util.List;

/**
 *
 * @author da.salinas3247
 */
public interface IRaitingLogic {
    public int countRaitings();
    public List<RaitingEntity> getRaitings();
    public List<RaitingEntity> getRaitings(Integer page, Integer maxRecords);
    public RaitingEntity getRaiting(Long id);
    public RaitingEntity createRaiting(RaitingEntity entity); 
    public RaitingEntity updateRaiting(RaitingEntity entity);
    public void deleteRaiting(Long id);
     
    // Rate a product in a item
    public RaitingEntity createRaitingFromItem(Long clientId, Long itemId, RaitingEntity entity); 
    public RaitingEntity updateRaitingFromItem(Long clientId, Long itemId, RaitingEntity entity);

}
