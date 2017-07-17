/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.io.IOException;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Service;

@Service
public class Dply_co {

    public boolean CreateServer(WebDriver webDriver) {
        Random rd = new Random();
        int temp = rd.nextInt(9999);
        try {
            WebElement linkcreate = webDriver.findElement(By.xpath("//a[@class='waves-effect waves-light btn-large green']"));
            linkcreate.click();
            //
            WebElement name = webDriver.findElement(By.id("servername"));
            name.sendKeys("fwefwsdfsdase"+temp);
            //
            Select os = new Select(webDriver.findElement(By.id("os")));
            os.selectByVisibleText("Ubuntu 16.04");
            //
            Select region = new Select(webDriver.findElement(By.id("region")));
            region.selectByVisibleText("Toronto");
            //
            Select plan = new Select(webDriver.findElement(By.id("plan")));
            plan.selectByVisibleText("2 Hours (FREE)");
            //
            Select key = new Select(webDriver.findElement(By.id("key")));
            key.selectByIndex(1);
            //
            WebElement submit_button = webDriver.findElement(By.xpath("//button[@class='g-recaptcha ']"));
            submit_button.click();
            int x = 9;
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    public boolean OpenDly(WebDriver webDriver) {

        try {
            webDriver.navigate().to("https://dply.co/");
            
            WebElement link = webDriver.findElement(By.xpath("//a[@class='waves-effect waves-light btn-large blue darken-1 signin']"));
            link.click();
            int x = 9;
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    //
    public String getIP(WebDriver webDriver) throws IOException {
        String info = "https://dply.co/dashrel";
        webDriver.get(info);
        try {
            String text = webDriver.findElement(By.xpath("//div[@class='col s12 grey darken-4 grey-text']")).getText();
            return text;
        } catch (Exception e) {
            return e.getMessage();
        }

    }
}
