/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.tests.selenium.pages.tax;

import co.edu.uniandes.csw.turism.dtos.minimum.FAQDTO;
import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.jboss.arquillian.graphene.Graphene.waitGui;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.jboss.arquillian.graphene.Graphene.waitGui;

/**
 *
 * @author lm.ariza10
 */
public class TaxEditPage {
    
    @FindBy(id = "name")
    private WebElement nameInput;
    @FindBy(id = "description")
    private WebElement descriptionInput;
    @FindBy(id = "value")
    private WebElement valueInput;

    @FindBy(id = "save-trip")
    private WebElement saveBtn;

    @FindBy(id = "cancel-trip")
    private WebElement cancelBtn;

    public void saveFAQ(FAQDTO trip) {
         waitGui().until().element(nameInput).is().visible();
         nameInput.clear();
         nameInput.sendKeys(trip.getName());
         waitGui().until().element(descriptionInput).is().visible();
         descriptionInput.clear();
         descriptionInput.sendKeys(trip.getQuestion());
         waitGui().until().element(valueInput).is().visible();
         valueInput.clear();
         valueInput.sendKeys(trip.getResponse().toString());
        guardAjax(saveBtn).click();
    }
}
