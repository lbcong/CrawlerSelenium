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
public class GitHub {

    public boolean LoginGitHub(String username, String passw, WebDriver webDriver) {

        try {
            Thread.sleep(2000);
            WebElement user = webDriver.findElement(By.id("login_field"));
            Thread.sleep(2000);
            WebElement password = webDriver.findElement(By.id("password"));
            Thread.sleep(2000);
            WebElement submit_button = webDriver.findElement(By.xpath("//input[@value='Sign in']"));
            Thread.sleep(2000);
            user.sendKeys(username);
            Thread.sleep(2000);
            password.sendKeys(passw);
            Thread.sleep(2000);
            submit_button.click();
            return true;
        } catch (Exception e) {
             System.out.println("LoginGitHub:"+e.getMessage()); 
        }
        return false;

    }

    public boolean LogoutGitHub(WebDriver webDriver) {

        try {
            WebElement dropDownListBox = webDriver.findElement(By.id("user-links"));
            dropDownListBox.click();

            WebElement submit_button = webDriver.findElement(By.xpath("//button[@class='dropdown-item dropdown-signout']"));
            submit_button.click();
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }
}
