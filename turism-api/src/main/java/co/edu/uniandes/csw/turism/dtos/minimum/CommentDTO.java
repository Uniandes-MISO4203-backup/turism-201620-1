/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.dtos.minimum;

import co.edu.uniandes.csw.turism.entities.CommentEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author da.prieto1
 */
@XmlRootElement
public class CommentDTO implements Serializable {

    private Long id;
    private String name;
    private String text;
    private Date date;

    /**
     * Constructor de la clase DTO mínima
     */
    public CommentDTO() {
    }

    public CommentDTO(CommentEntity commentEntity) {
        if (commentEntity != null) {
            this.id = commentEntity.getId();
            this.name = commentEntity.getName();
            this.text = commentEntity.getText();
            this.date = commentEntity.getDate();
        }
    }

    public CommentEntity toEntity() {
        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setId(this.getId());
        commentEntity.setName(this.getName());
        commentEntity.setText(this.getText());
        commentEntity.setDate(this.getDate());

        return commentEntity;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
