/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.tests.selenium.pages.news;


import co.edu.uniandes.csw.turism.dtos.minimum.NewsDTO;
import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.jboss.arquillian.graphene.Graphene.waitGui;

/**
 *
 * @author lm.ariza10
 */
public class NewsCreatePage {
    @FindBy(id = "name")
    private WebElement nameInput;
    @FindBy(id = "content")
    private WebElement contentInput;
    @FindBy(id = "date")
    private WebElement dateInput;

    @FindBy(id = "save-news")
    private WebElement saveBtn;

    @FindBy(id = "cancel-news")
    private WebElement cancelBtn;

    public void saveTrip(NewsDTO news) {
         waitGui().until().element(nameInput).is().visible();
         nameInput.clear();
         nameInput.sendKeys(news.getName());
         waitGui().until().element(contentInput).is().visible();
         contentInput.clear();
         contentInput.sendKeys(news.getContent());
         waitGui().until().element(dateInput).is().visible();
         dateInput.clear();
         dateInput.sendKeys(news.getDate().toString());
        guardAjax(saveBtn).click();
    }
}
