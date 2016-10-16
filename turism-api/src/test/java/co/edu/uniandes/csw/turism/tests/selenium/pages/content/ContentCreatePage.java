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
package co.edu.uniandes.csw.turism.tests.selenium.pages.content;

import co.edu.uniandes.csw.turism.tests.selenium.pages.content.*;
import co.edu.uniandes.csw.turism.dtos.minimum.ContentDTO;
import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.jboss.arquillian.graphene.Graphene.waitGui;

public class ContentCreatePage {

    @FindBy(id = "name")
    private WebElement nameInput;
    @FindBy(id = "contentValue")
    private WebElement contentValueInput;
    @FindBy(id = "date")
    private WebElement dateInput;
    @FindBy(id = "save-trip")
    private WebElement saveBtn;

    @FindBy(id = "cancel-trip")
    private WebElement cancelBtn;

    public void saveContent(ContentDTO content) {
         waitGui().until().element(nameInput).is().visible();
         nameInput.clear();
         nameInput.sendKeys(content.getName());
         waitGui().until().element(contentValueInput).is().visible();
         contentValueInput.clear();
         nameInput.sendKeys(content.getContentValue());
         waitGui().until().element(dateInput).is().visible();
         dateInput.clear();
         dateInput.sendKeys(content.getDate().toString());
         guardAjax(saveBtn).click();
    }
}
