/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.tests.selenium.pages.tax;

import co.edu.uniandes.csw.turism.dtos.minimum.FAQDTO;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author lm.ariza10
 */
public class TaxDetailPage {
    @FindBy(id = "delete-tax")
    private WebElement deleteBtn;

    @FindBy(id = "edit-tax")
    private WebElement editBtn;

    @FindBy(id = "list-tax")
    private WebElement listBtn;

    
    @FindBy(id = "name")
    private WebElement name;
    @FindBy(id = "description")
    private WebElement description;
    @FindBy(id = "value")
    private WebElement value;

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
        FAQDTO tax = new FAQDTO();        
        tax.setName(name.getText());
        tax.setQuestion(description.getText());
        tax.setResponse(value.getText());
        return tax;
    }
}
