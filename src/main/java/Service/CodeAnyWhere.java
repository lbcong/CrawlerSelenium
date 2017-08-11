/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeAnyWhere {

  

    public boolean LoginCodeAnyWhere(String username, String passw, WebDriver webDriver) {

        try {
            Thread.sleep(2000);
            webDriver.navigate().to("https://codeanywhere.com");
            Thread.sleep(2000);
            WebElement login = webDriver.findElement(By.xpath("//a[@class='navigation-link login-toggle']"));

            Thread.sleep(1000);
            login.click();
            Thread.sleep(2000);
            WebElement user = webDriver.findElement(By.id("login-email"));
            Thread.sleep(2000);
            WebElement password = webDriver.findElement(By.id("login-password"));
            Thread.sleep(2000);
            WebElement submit_button = webDriver.findElement(By.xpath("//button[@class='login-btn btn btn-md btn-secondary-outline']"));
            Thread.sleep(2000);
            user.sendKeys(username);
            Thread.sleep(2000);
            password.sendKeys(passw);
            Thread.sleep(2000);
            submit_button.click();
            System.out.println("Login: done");
            return true;
        } catch (Exception e) {
            System.out.println("Login:" + e.getMessage());
        }
        return false;

    }

    public String getInfo(WebDriver webDriver) {

        try {
            Actions myAction = new Actions(webDriver);
            Thread.sleep(3000);
            WebElement element = webDriver.findElement(By.xpath("//div[@id='expanded connection devbox gtnode']//*[@class='gtnode-inner']//*[@class='arrow-icon']"));
            Thread.sleep(2000);

            myAction.contextClick(element).build().perform();
            Actions myAction1 = new Actions(webDriver);
            myAction1.moveByOffset(52, 126).build().perform();
            Thread.sleep(1000);
            myAction1.click().build().perform();
            Thread.sleep(1000);
            WebElement element2 = webDriver.findElement(By.xpath("//div[@class='markdown-body']/ul/li/a/code"));
            return element2.getText();
            
        } catch (Exception e) {
            System.out.println("getInfo:" + e.getMessage());
        }
        return null;
    }
}
