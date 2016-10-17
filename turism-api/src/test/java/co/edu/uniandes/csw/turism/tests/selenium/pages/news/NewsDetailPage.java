/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.tests.selenium.pages.news;

import co.edu.uniandes.csw.turism.dtos.minimum.NewsDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author lm.ariza10
 */
public class NewsDetailPage {
    @FindBy(id = "delete-news")
    private WebElement deleteBtn;

    @FindBy(id = "edit-news")
    private WebElement editBtn;

    @FindBy(id = "list-news")
    private WebElement listBtn;

    
    @FindBy(id = "name")
    private WebElement name;
    @FindBy(id = "content")
    private WebElement content;
    @FindBy(id = "date")
    private WebElement date;

    public void list() {
        listBtn.click();
    }

    public void edit() {
        editBtn.click();
    }

    public void delete() {
        deleteBtn.click();
    }

    public NewsDTO getData() {
        NewsDTO news = new NewsDTO();        
        news.setName(name.getText());
        news.setContent(content.getText());
        try {
            news.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(date.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(NewsDetailPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return news;
    }
}
