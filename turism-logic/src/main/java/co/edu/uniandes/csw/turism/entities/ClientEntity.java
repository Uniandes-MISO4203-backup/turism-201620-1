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
package co.edu.uniandes.csw.turism.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;

/**
 * @generated
 */
@Entity
public class ClientEntity extends BaseEntity implements Serializable {

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String homeAddress;
    private String image;    
    
    @PodamExclude
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEntity> wishList = new ArrayList<>();

    
    @PodamExclude
    @OneToMany(mappedBy = "client")
    private List<RaitingEntity> raitings = new ArrayList<>();

    /**
     * Obtiene la colección de wishList.
     *
     * @return colección wishList.
     * @generated
     */
    public List<ItemEntity> getWishList() {
        return wishList;
    }

    /**
     * Establece el valor de la colección de wishList.
     *
     * @param wishlist
     * @generated
     */
    public void setWishList(List<ItemEntity> wishlist) {
        this.wishList = wishlist;
    }

    /**
     * 
     * @return 
     */
    public List<RaitingEntity> getRaitings() {
        return raitings;
    }

    /**
     * 
     * @param raitings 
     */
    public void setRaitings(List<RaitingEntity> raitings) {
        this.raitings = raitings;
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
