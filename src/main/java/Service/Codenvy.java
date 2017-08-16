/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Service;

@Service
public class Codenvy {

    public boolean LoginCodenvy(String username, String passw, WebDriver webDriver) {
        Random rd = new Random();
        int r = 0;
        boolean status = false;
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
            Thread.sleep(60000);
            webDriver.navigate().to("https://codenvy.io/dashboard/#/ide/" + temp + "/work1");

            Thread.sleep(180000);
            System.out.println("spam https://codenvy.io/dashboard/#/ide/" + temp + "/work1 : done");
            while (true) {
                try {
                    r = rd.nextInt(9999);
                    Thread.sleep(2000);
                    System.out.println("run spam");
                    Actions myAction = new Actions(webDriver);

                    Thread.sleep(1000);
                    myAction.moveByOffset(794, 200).build().perform();
                    Thread.sleep(1000);
                    if (!status) {
                        myAction.keyDown(Keys.ALT).keyDown(Keys.SHIFT).sendKeys("N").keyUp(Keys.ALT).keyUp(Keys.SHIFT).build().perform();
                        Thread.sleep(1000);
                        myAction.sendKeys("wefwef4we3" + r).build().perform();
                        Thread.sleep(1000);
                        myAction.sendKeys(Keys.ENTER).perform();
                        status = true;
                    }

                    Thread.sleep(1000);
                    myAction.sendKeys("delsdfeete" + r).build().perform();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {
            System.out.println("Login:" + e.getMessage());
        }
        return false;

    }

}
