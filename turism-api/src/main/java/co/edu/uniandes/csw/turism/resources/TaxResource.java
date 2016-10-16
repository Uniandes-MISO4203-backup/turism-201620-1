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
import co.edu.uniandes.csw.turism.api.ITaxLogic;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.turism.api.ITripLogic;
import co.edu.uniandes.csw.turism.dtos.detail.TaxDetailDTO;
import co.edu.uniandes.csw.turism.dtos.minimum.TaxDTO;
import co.edu.uniandes.csw.turism.entities.TaxEntity;
import java.util.ArrayList;
import javax.ws.rs.QueryParam;
/**
 * URI: agencys/{agencysId: \\d+}/trips/{tripsId: \\d+}/tax
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaxResource {

    @Inject private ITripLogic tripLogic;
    @Context private HttpServletResponse response;
    
     @Inject
    private ITaxLogic taxLogic;

        @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    @PathParam("tripsId") private Long tipsId;

    /**
     * Convierte una lista de TaxDetailDTO a una lista de TaxEntity.
     *
     * @param dtos Lista de TaxDetailDTO a convertir.
     * @return Lista de TaxEntity convertida.
     * @generated
     */
    private List<TaxEntity> taxListDTO2Entity(List<TaxDetailDTO> dtos){
        List<TaxEntity> list = new ArrayList<>();
        for (TaxDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * Obtiene una colección de instancias de TaxDetailDTO asociadas a una
     * instancia de Trip
     *
     * @param tripsId Identificador de la instancia de Trip
     * @return Colección de instancias de TaxDetailDTO asociadas a la instancia de Trip
     * @generated
     */
    @GET
    public List<TaxDTO> listTax(@PathParam("tripsId") Long tripsId) {
        List<TaxDTO> list = new ArrayList<>();
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", taxLogic.countTaxes());
           for(TaxEntity taxEntity: taxLogic.getTaxes(page, maxRecords)) {
               list.add(new TaxDTO(taxEntity));
           }
        } else {
              for(TaxEntity taxEntity: taxLogic.getTaxes()) {
               list.add(new TaxDTO(taxEntity));
           }
        }
        return list;
    }

    /**
     * Obtiene una instancia de Tax asociada a una instancia de Trip
     *
     * @param tripsId Identificador de la instancia de Trip
     * @param taxId Identificador de la instancia de Tax
     * @generated
     */
    @GET
    @Path("{taxId: \\d+}")
    public TaxDTO getTax(@PathParam("taxId") Long taxId) {
        return new TaxDTO(taxLogic.getTax(taxId));
    }

    /**
     * Crea un Tax y lo asocia a un Trip
     *
     * @param tripsId Identificador de la instancia de Trip
     * @param taxId Identificador de la instancia de Tax
     * @return Instancia de TaxDetailDTO que fue asociada a Trip
     * @generated
     */
     @POST
    @StatusCreated
    public TaxDetailDTO addTax(@PathParam("tripsId") Long tripsId, TaxDTO dto) {
         TaxEntity entity = dto.toEntity();
         TaxEntity taxEntity = taxLogic.createTax(entity);
        tripLogic.addTax(tripsId, taxEntity.getId());
        return new TaxDetailDTO(taxEntity);
    }
    
      @PUT
    @Path("{id: \\d+}")
    public TaxDTO updateFAQ(@PathParam("id") Long id, TaxDTO dto) {
        TaxEntity entity = dto.toEntity();
        entity.setId(id);
        return new TaxDTO(taxLogic.updateTax(entity));
    }

    /**
     * Desasocia un Tax existente de un Trip existente
     *
     * @param tripsId Identificador de la instancia de Trip
     * @param taxId Identificador de la instancia de Tax
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteTax(@PathParam("id") Long id) {
        taxLogic.deleteTax(id);
    }
}
