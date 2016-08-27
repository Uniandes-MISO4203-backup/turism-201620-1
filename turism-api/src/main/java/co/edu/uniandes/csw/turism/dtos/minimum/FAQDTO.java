/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.minimum;

import co.edu.uniandes.csw.turism.entities.FAQEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lm.ariza10
 */
@XmlRootElement
public class FAQDTO implements Serializable {

    private Long id;
    private String name;
    private String question;
    private String response;
    
     /**
     * @generated
     */
    public FAQDTO() {
    }

    /**
     * Crea un objeto TripDTO a partir de un objeto TripEntity.
     *
     * @param entity Entidad TripEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public FAQDTO(FAQEntity entity) {
	   if (entity!=null){
        this.id=entity.getId();
        this.name=entity.getName();
        this.question=entity.getQuestion();
        this.response=entity.getResponse();
       }
    }

    /**
     * Convierte un objeto TripDTO a TripEntity.
     *
     * @return Nueva objeto TripEntity.
     * @generated
     */
    public FAQEntity toEntity() {
        FAQEntity entity = new FAQEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setQuestion(this.getQuestion());
        entity.setResponse(this.getResponse());
    return entity;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
   
    
}
