/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.turism.tests.selenium.pages.raitings;

import co.edu.uniandes.csw.turism.dtos.minimum.RaitingDTO;
import co.edu.uniandes.csw.turism.tests.selenium.pages.trip.TripDetailPage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author da.salinas3247
 */
public class RaitingDetailPage {
    @FindBy(id = "delete-raiting")
    private WebElement deleteBtn;

    @FindBy(id = "edit-raiting")
    private WebElement editBtn;

    @FindBy(id = "list-raiting")
    private WebElement listBtn;

   
    @FindBy(id = "value")
    private WebElement value;
    @FindBy(id = "date")
    private WebElement date;
    @FindBy(id = "textComment")
    private WebElement textComment;

    public void list() {
        listBtn.click();
    }

    public void edit() {
        editBtn.click();
    }

    public void delete() {
        deleteBtn.click();
    }

    public RaitingDTO getData() {
        RaitingDTO raiting = new RaitingDTO();        
        raiting.setValue(new Integer (value.getText()));
        try {
            raiting.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(date.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(RaitingDetailPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        raiting.setTextComment(textComment.getText());
        return raiting;
    }
}

