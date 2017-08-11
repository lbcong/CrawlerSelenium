/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
            return true;
        } catch (Exception e) {
            System.out.println("LoginGitHub:" + e.getMessage());
        }
        return false;

    }
}
