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
package co.edu.uniandes.csw.turism.tests.selenium.pages.client;

import co.edu.uniandes.csw.turism.dtos.minimum.ClientDTO;
import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.jboss.arquillian.graphene.Graphene.waitGui;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClientEditPage {

    @FindBy(id = "name")
    private WebElement nameInput;
    
    @FindBy(id = "firstName")
    private WebElement firstNameInput;
    
    @FindBy(id = "middleName")
    private WebElement middleNameInput;
    
    @FindBy(id = "lastName")
    private WebElement lastNameInput;
    
    @FindBy(id = "email")
    private WebElement emailInput;
    
    @FindBy(id = "phoneNumber")
    private WebElement phoneNumberInput;
    
    @FindBy(id = "homeAddress")
    private WebElement homeAddressInput;    

    @FindBy(id = "save-client")
    private WebElement saveBtn;

    @FindBy(id = "cancel-client")
    private WebElement cancelBtn;

    public void saveClient(ClientDTO client) {
         waitGui().until().element(nameInput).is().visible();
         nameInput.clear();
         nameInput.sendKeys(client.getName());
         waitGui().until().element(firstNameInput).is().visible();
         firstNameInput.clear();
         firstNameInput.sendKeys(client.getFirstName());
         waitGui().until().element(middleNameInput).is().visible();
         middleNameInput.clear();
         middleNameInput.sendKeys(client.getMiddleName());
         waitGui().until().element(lastNameInput).is().visible();
         lastNameInput.clear();
         lastNameInput.sendKeys(client.getLastName());
         waitGui().until().element(emailInput).is().visible();
         emailInput.clear();
         emailInput.sendKeys(client.getEmail());
         waitGui().until().element(phoneNumberInput).is().visible();
         phoneNumberInput.clear();
         phoneNumberInput.sendKeys(client.getPhoneNumber());
         waitGui().until().element(homeAddressInput).is().visible();
         homeAddressInput.clear();
         homeAddressInput.sendKeys(client.getHomeAddress());         
        guardAjax(saveBtn).click();
    }
}
