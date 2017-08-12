/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Service;

@Service
public class Codenvy {

    public boolean LoginCodenvy(String username, String passw, WebDriver webDriver) {

        try {
            Thread.sleep(2000);
            String array[] = username.split("@");
            String temp = array[0];

            webDriver.navigate().to("https://codenvy.io/site/login");
            Thread.sleep(5000);
            WebElement user = webDriver.findElement(By.id("username"));
            Thread.sleep(2000);
            WebElement password = webDriver.findElement(By.xpath("//input[@name='password']"));
            Thread.sleep(2000);
            WebElement submit_button = webDriver.findElement(By.xpath("//input[@value='Login']"));
            Thread.sleep(2000);
            user.sendKeys(username);
            Thread.sleep(2000);
            password.sendKeys(passw);
            Thread.sleep(2000);
            submit_button.click();
            System.out.println("Login: done");
             Thread.sleep(5000);
            webDriver.navigate().to("https://codenvy.io/dashboard/#/ide/" + temp + "/work1");
            
            Thread.sleep(180000);
            System.out.println("spam https://codenvy.io/dashboard/#/ide/" + temp + "/work1 : done");
            while (true) {
                Thread.sleep(2000);
                System.out.println("run spam");
                Actions myAction = new Actions(webDriver);
                Thread.sleep(1000);
                myAction.moveByOffset(794, 200).build().perform();
                Thread.sleep(1000);
                myAction.sendKeys("delete").build().perform();
            }

        } catch (Exception e) {
            System.out.println("Login:" + e.getMessage());
        }
        return false;

    }

    public String getInfo(WebDriver webDriver) {

        try {
            Actions myAction = new Actions(webDriver);
            Thread.sleep(10000);
            WebElement element = webDriver.findElement(By.xpath("//div[@id='a65b435d2e1de8071bf54197be523db0']//*[@class='gtnode-inner']//*[@class='arrow-icon']"));
            Thread.sleep(4000);

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
