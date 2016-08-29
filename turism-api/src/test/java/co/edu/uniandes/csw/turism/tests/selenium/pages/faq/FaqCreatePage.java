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
import static org.jboss.arquillian.graphene.Graphene.waitGui;

/**
 *
 * @author lm.ariza10
 */
public class FaqCreatePage {
    @FindBy(id = "name")
    private WebElement nameInput;
    @FindBy(id = "question")
    private WebElement questionInput;
    @FindBy(id = "response")
    private WebElement responseInput;

    @FindBy(id = "save-faq")
    private WebElement saveBtn;

    @FindBy(id = "cancel-faq")
    private WebElement cancelBtn;

    public void saveTrip(FAQDTO faq) {
         waitGui().until().element(nameInput).is().visible();
         nameInput.clear();
         nameInput.sendKeys(faq.getName());
         waitGui().until().element(questionInput).is().visible();
         questionInput.clear();
         questionInput.sendKeys(faq.getQuestion());
         waitGui().until().element(responseInput).is().visible();
         responseInput.clear();
         responseInput.sendKeys(faq.getResponse().toString());
        guardAjax(saveBtn).click();
    }
}
