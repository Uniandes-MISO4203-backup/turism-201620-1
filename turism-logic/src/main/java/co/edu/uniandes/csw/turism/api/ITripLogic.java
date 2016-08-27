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
package co.edu.uniandes.csw.turism.api;

import co.edu.uniandes.csw.turism.entities.TripEntity;
import co.edu.uniandes.csw.turism.entities.CategoryEntity;
import co.edu.uniandes.csw.turism.entities.CommentEntity;
import java.util.List;

public interface ITripLogic {
    
    public int countTrips();
    public List<TripEntity> getTrips(Long agencyid);
    public List<TripEntity> getTrips(Integer page, Integer maxRecords, Long agencyid);
    public List<TripEntity> getTripByCategory(Integer page, Integer maxRecords, Long categoryid);
    public TripEntity getTrip(Long tripid);
    public TripEntity createTrip(Long agencyid, TripEntity entity);
    public TripEntity updateTrip(Long agencyid, TripEntity entity);
    public void deleteTrip(Long id);
    
    public List<CategoryEntity> listCategory(Long tripId);
    public CategoryEntity getCategory(Long tripId, Long categoryId);
    public CategoryEntity addCategory(Long tripId, Long categoryId);
    public List<CategoryEntity> replaceCategory(Long tripId, List<CategoryEntity> list);
    public void removeCategory(Long tripId, Long categoryId);
    
    public List<CommentEntity> listComments(Long tripId);
    public CommentEntity getComment(Long tripId, Long commentId);
    public CommentEntity addComment(Long tripId, Long commentId);
    public List<CommentEntity> replaceComments(Long tripId, List<CommentEntity> list);
    public void removeComment(Long tripId, Long commentId);
}
