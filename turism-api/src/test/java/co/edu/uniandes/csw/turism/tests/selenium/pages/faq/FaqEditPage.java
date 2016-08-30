/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.tests.selenium.pages.faq;

import co.edu.uniandes.csw.turism.dtos.minimum.FAQDTO;
import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.jboss.arquillian.graphene.Graphene.waitGui;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author lm.ariza10
 */
public class FaqEditPage {
    
    @FindBy(id = "name")
    private WebElement nameInput;
    @FindBy(id = "question")
    private WebElement questionInput;
    @FindBy(id = "response")
    private WebElement responseInput;

    @FindBy(id = "save-trip")
    private WebElement saveBtn;

    @FindBy(id = "cancel-trip")
    private WebElement cancelBtn;

    public void saveFAQ(FAQDTO trip) {
         waitGui().until().element(nameInput).is().visible();
         nameInput.clear();
         nameInput.sendKeys(trip.getName());
         waitGui().until().element(questionInput).is().visible();
         questionInput.clear();
         questionInput.sendKeys(trip.getQuestion());
         waitGui().until().element(responseInput).is().visible();
         responseInput.clear();
         responseInput.sendKeys(trip.getResponse().toString());
        guardAjax(saveBtn).click();
    }
}
