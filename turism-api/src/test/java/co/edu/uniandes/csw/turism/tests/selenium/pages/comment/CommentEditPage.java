/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.tests.selenium.pages.comment;

import co.edu.uniandes.csw.turism.dtos.minimum.CommentDTO;
import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.jboss.arquillian.graphene.Graphene.waitGui;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author da.prieto1
 */
public class CommentEditPage {
    
    @FindBy(id = "name")
    private WebElement nameInput;
    
    @FindBy(id = "text")
    private WebElement textInput;
    
    @FindBy(id = "date")
    private WebElement dateInput;

    @FindBy(id = "save-comment")
    private WebElement saveBtn;

    @FindBy(id = "cancel-comment")
    private WebElement cancelBtn;

    public void saveRaiting(CommentDTO comment) {
         waitGui().until().element(nameInput).is().visible();
         nameInput.clear();
         nameInput.sendKeys(comment.getName());
         waitGui().until().element(textInput).is().visible();
         textInput.clear();
         textInput.sendKeys(comment.getText());
         waitGui().until().element(dateInput).is().visible();
         dateInput.clear();
         dateInput.sendKeys(comment.getDate().toString());
        guardAjax(saveBtn).click();
    }
}
