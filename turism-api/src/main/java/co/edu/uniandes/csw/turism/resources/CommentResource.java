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
package co.edu.uniandes.csw.turism.resources;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import co.edu.uniandes.csw.turism.api.ICommentLogic;
import co.edu.uniandes.csw.turism.dtos.detail.CommentDetailDTO;
import co.edu.uniandes.csw.turism.entities.CommentEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * URI: trips/{tripId: \\d+}/comments/
 *
 * @author da.prieto1
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

    @Inject
    private ICommentLogic commentLogic;

    @Context
    private HttpServletResponse response;

    @QueryParam("page")
    private Integer page;

    @QueryParam("limit")
    private Integer maxRecords;

    @PathParam("tripsId")
    private Long tripsId;

    @GET
    public List<CommentDetailDTO> getComments() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", commentLogic.countComments());
            return listEntity2DTO(commentLogic.getComments(tripsId));
        }
        return listEntity2DTO(commentLogic.getComments(tripsId));
    }

    @GET
    @Path("{commentId: \\d+}")
    public CommentDetailDTO getComment(@PathParam("commentId") Long commentId) {
        CommentEntity entity = commentLogic.getComment(commentId);
        if (entity.getTrip() != null && !tripsId.equals(entity.getTrip().getId())) {
            throw new WebApplicationException(404);
        }
        return new CommentDetailDTO(entity);
    }

    @POST
    @StatusCreated
    public CommentDetailDTO createComment(CommentDetailDTO dto) {
        return new CommentDetailDTO(commentLogic.createComment(tripsId, dto.toEntity()));
    }

    private List<CommentDetailDTO> listEntity2DTO(List<CommentEntity> entityList) {
        List<CommentDetailDTO> list = new ArrayList<>();
        for (CommentEntity entity : entityList) {
            list.add(new CommentDetailDTO(entity));
        }
        return list;
    }

}
