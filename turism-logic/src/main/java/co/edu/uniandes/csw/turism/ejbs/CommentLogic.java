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

import co.edu.uniandes.csw.turism.api.ICommentLogic;
import co.edu.uniandes.csw.turism.api.ITripLogic;
import co.edu.uniandes.csw.turism.entities.CommentEntity;
import co.edu.uniandes.csw.turism.entities.TripEntity;
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

    @Inject
    private ITripLogic tripLogic;

    /**
     * 
     * @return int contador de comments 
     */
    @Override
    public int countComments() {
        return persistence.count();
    }
    
    /**
     * Devuelve un listado de comments
     * @param tripId
     * @return 
     */
    @Override
    public List<CommentEntity> getComments(Long tripId) {
        TripEntity tripEntity = tripLogic.getTrip(tripId);
        return tripEntity.getComments();
    }

    /**
     * Devuelve un comment por id
     * @param id
     * @return 
     */
    @Override
    public CommentEntity getComment(Long id) {
        return persistence.find(id);
    }

    /**
     * Crear un commment
     * @param tripId
     * @param entity
     * @return 
     */
    @Override
    public CommentEntity createComment(Long tripId, CommentEntity entity) {

        TripEntity trip = tripLogic.getTrip(tripId);
        entity.setTrip(trip);

        entity = persistence.create(entity);
        return entity;
    }

}
