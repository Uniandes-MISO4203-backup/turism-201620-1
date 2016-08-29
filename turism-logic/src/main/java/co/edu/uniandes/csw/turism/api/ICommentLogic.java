/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.api;

import co.edu.uniandes.csw.turism.entities.CommentEntity;
import java.util.List;

/**
 *
 * @author da.prieto1
 */
public interface ICommentLogic {

    public int countComments();

    public List<CommentEntity> getCommentsByTrip(Long tripId);

    public List<CommentEntity> getCommentsByTrip(Integer page, Integer maxRecords, Long tripId);
    
    public List<CommentEntity> getCommentsByClient(Long clientId);

    public List<CommentEntity> getCommentsByClient(Integer page, Integer maxRecords, Long clientId);

    public CommentEntity getComment(Long id);

    public CommentEntity createComment(Long clientId, Long tripId, CommentEntity entity);

}
