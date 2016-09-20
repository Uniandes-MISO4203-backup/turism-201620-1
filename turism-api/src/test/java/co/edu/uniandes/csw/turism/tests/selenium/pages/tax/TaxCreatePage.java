/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.tests.selenium.pages.tax;


import co.edu.uniandes.csw.turism.dtos.minimum.TaxDTO;
import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.jboss.arquillian.graphene.Graphene.waitGui;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.jboss.arquillian.graphene.Graphene.waitGui;
import static org.jboss.arquillian.graphene.Graphene.waitGui;
import static org.jboss.arquillian.graphene.Graphene.waitGui;

/**
 *
 * @author lm.ariza10
 */
public class TaxCreatePage {
    @FindBy(id = "name")
    private WebElement nameInput;
    @FindBy(id = "description")
    private WebElement descriptionInput;
    @FindBy(id = "value")
    private WebElement valueInput;

    @FindBy(id = "save-tax")
    private WebElement saveBtn;

    @FindBy(id = "cancel-tax")
    private WebElement cancelBtn;

    public void saveTrip(TaxDTO tax) {
         waitGui().until().element(nameInput).is().visible();
         nameInput.clear();
         nameInput.sendKeys(tax.getName());
         waitGui().until().element(descriptionInput).is().visible();
         descriptionInput.clear();
         descriptionInput.sendKeys(tax.getDescription());
         waitGui().until().element(valueInput).is().visible();
         valueInput.clear();
         valueInput.sendKeys(tax.getValue().toString());
        guardAjax(saveBtn).click();
    }
}
