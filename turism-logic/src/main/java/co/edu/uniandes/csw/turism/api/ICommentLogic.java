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

    public List<CommentEntity> getComments(Long tripId);

    public CommentEntity getComment(Long id);

    public CommentEntity createComment(Long tripId, CommentEntity entity);

}
