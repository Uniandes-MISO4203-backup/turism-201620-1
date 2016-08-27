/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.ejbs;

import co.edu.uniandes.csw.turism.api.ICommentLogic;
import co.edu.uniandes.csw.turism.entities.CommentEntity;
import co.edu.uniandes.csw.turism.persistence.CommentPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author da.prieto1
 */
public class CommentLogic implements ICommentLogic {

    @Inject
    private CommentPersistence persistence;

    @Override
    public int countComments() {
        return persistence.count();
    }

    @Override
    public List<CommentEntity> getComments() {
        return persistence.findAll();
    }

    @Override
    public List<CommentEntity> getComments(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public CommentEntity getComment(Long id) {
        return persistence.find(id);
    }

    @Override
    public CommentEntity createComment(CommentEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public CommentEntity updateComment(CommentEntity entity) {
        CommentEntity newEntity = entity;
        CommentEntity oldEntity = persistence.find(entity.getId());
        return persistence.update(newEntity);
    }

    @Override
    public void deleteComment(Long id) {
        persistence.delete(id);
    }
}
