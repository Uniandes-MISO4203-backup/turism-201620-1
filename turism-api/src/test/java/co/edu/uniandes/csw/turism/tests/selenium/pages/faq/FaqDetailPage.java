/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.tests.selenium.pages.faq;

import co.edu.uniandes.csw.turism.dtos.minimum.FAQDTO;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author lm.ariza10
 */
public class FaqDetailPage {
    @FindBy(id = "delete-faq")
    private WebElement deleteBtn;

    @FindBy(id = "edit-faq")
    private WebElement editBtn;

    @FindBy(id = "list-faq")
    private WebElement listBtn;

    
    @FindBy(id = "name")
    private WebElement name;
    @FindBy(id = "question")
    private WebElement question;
    @FindBy(id = "response")
    private WebElement response;

    public void list() {
        listBtn.click();
    }

    public void edit() {
        editBtn.click();
    }

    public void delete() {
        deleteBtn.click();
    }

    public FAQDTO getData() {
        FAQDTO faq = new FAQDTO();        
        faq.setName(name.getText());
        faq.setQuestion(question.getText());
        faq.setResponse(response.getText());
        return faq;
    }
}
