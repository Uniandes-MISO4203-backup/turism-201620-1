/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.tests.selenium.pages.comment;

import co.edu.uniandes.csw.turism.dtos.minimum.CommentDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author da.prieto1
 */
public class CommentDetailPage {
    @FindBy(id = "delete-comment")
    private WebElement deleteBtn;

    @FindBy(id = "edit-comment")
    private WebElement editBtn;

    @FindBy(id = "list-comment")
    private WebElement listBtn;
    
    @FindBy(id = "name")
    private WebElement name;
    
    @FindBy(id = "text")
    private WebElement text;
    
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
    
    public CommentDTO getData() {
        CommentDTO comment = new CommentDTO();   
        comment.setName(name.getText());
        comment.setText(text.getText());
         try {
            comment.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(date.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(CommentDetailPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comment;
    }
}
