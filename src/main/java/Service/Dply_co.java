/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Service;
import static restcontroller.GreedingController.webDriver;

@Service
public class Dply_co {

    public boolean CreateServer(WebDriver webDriver) {
        Random rd = new Random();
        int temp = rd.nextInt(9999);
        try {
            Thread.sleep(2000);
            WebElement linkcreate = webDriver.findElement(By.xpath("//a[@class='waves-effect waves-light btn-large green']"));
            Thread.sleep(2000);
            linkcreate.click();
            Thread.sleep(2000);
            //

            Select os = new Select(webDriver.findElement(By.id("os")));
            Thread.sleep(2000);
            String s = os.getFirstSelectedOption().getText();
            Thread.sleep(2000);
            os.selectByVisibleText("Ubuntu 16.04");
            //
            Thread.sleep(2000);
            WebElement name = webDriver.findElement(By.id("servername"));
            Thread.sleep(2000);
            name.sendKeys("f3wefws3d4fsd5ase" + temp);
            //
            Thread.sleep(2000);
            Select region = new Select(webDriver.findElement(By.id("region")));
            Thread.sleep(2000);
            region.selectByVisibleText("Toronto");
            //
            Thread.sleep(2000);
            Select plan = new Select(webDriver.findElement(By.id("plan")));
            Thread.sleep(2000);
            plan.selectByVisibleText("2 Hours (FREE)");
            //
            Thread.sleep(2000);
            WebElement key = webDriver.findElement(By.id("key"));
            Thread.sleep(2000);
            key.sendKeys(ConstantVariable.Constant.SSHKey_hacklslol1);
            //
            Thread.sleep(2000);
            WebElement submit_button = webDriver.findElement(By.xpath("//button[@class='g-recaptcha ']"));
            Thread.sleep(2000);
            submit_button.click();
            System.out.println("CreateServer:");
            return true;
        } catch (Exception e) {
            System.out.println("CreateServer:" + e.getMessage());
        }
        return false;
    }

    public boolean OpenDly(WebDriver webDriver) {

        try {
            Thread.sleep(2000);
            webDriver.navigate().to("https://dply.co/");
            Thread.sleep(5000);
            WebElement link = webDriver.findElement(By.xpath("//a[@class='waves-effect waves-light btn-large blue darken-1 signin']"));
            Thread.sleep(2000);
            link.click();

            return true;
        } catch (Exception e) {
            System.out.println("OpenDly:" + e.getMessage());
        }
        return false;
    }

    //
    public String getIP(WebDriver webDriver) throws IOException {
        String info = "https://dply.co/dashrel";
        webDriver.get(info);
        try {
            Thread.sleep(2000);
            String text = webDriver.findElement(By.xpath("//div[@class='col s12 grey darken-4 grey-text']")).getText();
            System.out.println("getIP:");
            return text;
        } catch (Exception e) {
            System.out.println("getIP:" + e.getMessage());
            return null;
        }

    }
}
