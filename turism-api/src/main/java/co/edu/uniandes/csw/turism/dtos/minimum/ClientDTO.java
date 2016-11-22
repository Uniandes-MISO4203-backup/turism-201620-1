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

import co.edu.uniandes.csw.turism.entities.ClientEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @generated
 */
@XmlRootElement
public class ClientDTO implements Serializable{

    private Long id;
    private String name;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String homeAddress;
    private String image;

    /**
     * Constructor de la clase DTO mínima
     */
    public ClientDTO() {
        super();
    }

    /**
     * Crea un objeto ClientDTO a partir de un objeto ClientEntity.
     *
     * @param entity Entidad ClientEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ClientDTO(ClientEntity entity) {
	   if (entity!=null){
        this.id=entity.getId();
        this.name=entity.getName();
        this.firstName = entity.getFirstName();
        this.middleName = entity.getMiddleName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.phoneNumber = entity.getPhoneNumber();
        this.homeAddress = entity.getHomeAddress();
        this.image = entity.getImage();
       }
    }

    /**
     * Convierte un objeto ClientDTO a ClientEntity.
     *
     * @return Nueva objeto ClientEntity.
     * @generated
     */
    public ClientEntity toEntity() {
        ClientEntity entity = new ClientEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setFirstName(this.getFirstName());
        entity.setMiddleName(this.getMiddleName());
        entity.setLastName(this.getLastName());
        entity.setEmail(this.getEmail());
        entity.setPhoneNumber(this.getPhoneNumber());
        entity.setHomeAddress(this.getHomeAddress());
        entity.setImage(this.getImage());
    return entity;
    }

    /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     * @generated
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el atributo name.
     *
     * @return atributo name.
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el valor del atributo name.
     *
     * @param name nuevo valor del atributo
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Obtiene el atributo firstName.
     *
     * @return atributo firstName.
     * @generated
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Establece el valor del atributo firstName.
     *
     * @param firstName nuevo valor del atributo
     * @generated
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Obtiene el atributo middleName.
     *
     * @return atributo middleName.
     * @generated
     */        
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Establece el valor del atributo middleName.
     *
     * @param middleName nuevo valor del atributo
     * @generated
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Obtiene el atributo lastName.
     *
     * @return atributo lastName.
     * @generated
     */    
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el valor del atributo lastName.
     *
     * @param lastName nuevo valor del atributo
     * @generated
     */
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Obtiene el atributo email.
     *
     * @return atributo email.
     * @generated
     */    
    public String getEmail() {
        return email;
    }

    /**
     * Establece el valor del atributo email.
     *
     * @param email nuevo valor del atributo
     * @generated
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el atributo phoneNumber.
     *
     * @return atributo phoneNumber.
     * @generated
     */    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Establece el valor del atributo phoneNumber.
     *
     * @param phoneNumber nuevo valor del atributo
     * @generated
     */    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Obtiene el atributo homeAddress.
     *
     * @return atributo homeAddress.
     * @generated
     */    
    public String getHomeAddress() {
        return homeAddress;
    }

    /**
     * Establece el valor del atributo homeAddress.
     *
     * @param homeAddress nuevo valor del atributo
     * @generated
     */    
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
    
    /**
     * Obtiene el atributo lastName.
     *
     * @return atributo lastName.
     * @generated
     */    
    public String getImage() {
        return image;
    }

    /**
     * Establece el valor del atributo image.
     *
     * @param image nuevo valor del atributo
     * @generated
     */
    public void setImage(String image) {
        this.image = image;
    }
}
