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
package co.edu.uniandes.csw.turism.tests.selenium.pages.trip;

import co.edu.uniandes.csw.turism.dtos.minimum.TripDTO;
import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.jboss.arquillian.graphene.Graphene.waitGui;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TripEditPage {

    @FindBy(id = "name")
    private WebElement nameInput;
    @FindBy(id = "image")
    private WebElement imageInput;
    @FindBy(id = "price")
    private WebElement priceInput;
    @FindBy(id = "date")
    private WebElement dateInput;
    @FindBy(id = "origin")
    private WebElement originInput;
    @FindBy(id = "destination")
    private WebElement destinationInput;    
    @FindBy(id = "transportType")
    private WebElement transportTypeInput;
    @FindBy(id = "specialRequirements")
    private WebElement specialRequirementsInput;
    @FindBy(id = "quota")
    private WebElement quotaInput;
    @FindBy(id = "duration")
    private WebElement durationInput;
    
    @FindBy(id = "save-trip")
    private WebElement saveBtn;

    @FindBy(id = "cancel-trip")
    private WebElement cancelBtn;

    public void saveTrip(TripDTO trip) {
         waitGui().until().element(nameInput).is().visible();
         nameInput.clear();
         nameInput.sendKeys(trip.getName());
         waitGui().until().element(imageInput).is().visible();
         imageInput.clear();
         imageInput.sendKeys(trip.getImage());
         waitGui().until().element(priceInput).is().visible();
         priceInput.clear();
         priceInput.sendKeys(trip.getPrice().toString());
         waitGui().until().element(dateInput).is().visible();
         dateInput.clear();
         dateInput.sendKeys(trip.getDate().toString());
         waitGui().until().element(originInput).is().visible();
         originInput.clear();
         originInput.sendKeys(trip.getOrigin());
         waitGui().until().element(destinationInput).is().visible();
         destinationInput.clear();
         destinationInput.sendKeys(trip.getDestination());         
         waitGui().until().element(transportTypeInput).is().visible();
         transportTypeInput.clear();
         transportTypeInput.sendKeys(trip.getTransportType());
         waitGui().until().element(specialRequirementsInput).is().visible();
         specialRequirementsInput.clear();
         specialRequirementsInput.sendKeys(trip.getSpecialRequirements());  
         waitGui().until().element(quotaInput).is().visible();
         quotaInput.clear();
         quotaInput.sendKeys(trip.getQuota().toString());
         waitGui().until().element(durationInput).is().visible();
         durationInput.clear();
         durationInput.sendKeys(trip.getDuration().toString());

        guardAjax(saveBtn).click();
    }
}
