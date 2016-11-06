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
package co.edu.uniandes.csw.turism.dtos.minimum;

import co.edu.uniandes.csw.turism.entities.RaitingEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author da.salinas3247
 */
@XmlRootElement
public class RaitingDTO implements Serializable{
    
    private Long id;
    private String name;
    private Integer value;
    private Date date;
    private String textComment;
    
    /**
     * Constructor de la clase DTO mínima
     */    
    public RaitingDTO(){
    }

    public RaitingDTO(RaitingEntity purchaseRaitingEntity) {
        if (purchaseRaitingEntity != null)
        {
            this.id = purchaseRaitingEntity.getId();
            this.name = purchaseRaitingEntity.getName();
            this.value = purchaseRaitingEntity.getValue();
            this.date = purchaseRaitingEntity.getDate();
            this.textComment = purchaseRaitingEntity.getTextComment();
        }
    }
    
    public RaitingEntity toEntity(){
        
        RaitingEntity purchaseRaitingEntity = new RaitingEntity();
        
        purchaseRaitingEntity.setId(this.getId());
        purchaseRaitingEntity.setName(this.getName());
        purchaseRaitingEntity.setValue(this.getValue());
        purchaseRaitingEntity.setDate(this.getDate());
        purchaseRaitingEntity.setTextComment(this.getTextComment());
        
        return purchaseRaitingEntity;
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTextComment() {
        return textComment;
    }

    public void setTextComment(String textComment) {
        this.textComment = textComment;
    }
    
      
}
