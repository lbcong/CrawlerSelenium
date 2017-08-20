package restcontroller;

import ConstantVariable.Constant;
import java.io.File;
import java.io.IOException;
import javax.annotation.PostConstruct;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreedingController {

    public static WebDriver webDriver = null;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String greeding() {

        return "Hello ";
    }

    @RequestMapping(value = "/openbrowser", method = RequestMethod.GET)
    public String selenium() {
        String output = "";
        try {
            //File pathToBinary = new File(Constant.binaryFirefox);
            //FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
            //FirefoxProfile firefoxProfile = new FirefoxProfile();
            //WebDriver webDriver = new FirefoxDriver(ffBinary, firefoxProfile);
            //System.setProperty("webdriver.chrome.driver", Constant.dirDriverGoogle);
            System.setProperty(Constant.webDriverFirefox, Constant.dirDriverGoogle);
            ChromeOptions options = new ChromeOptions();
            options.setBinary(Constant.binaryGoogle);
            //webDriver = new HtmlUnitDriver();
            webDriver = new ChromeDriver(options);
            //testVideo();
            openTestSite();
            login("admin", "12345");
            output = getText();

            //closeBrowser();
            return output;
        } catch (Exception e) {
            e.getMessage();
            return "loi : " + e.getMessage();
        }

    }

    public void login(String username, String Password) throws Exception, InterruptedException {

        Thread.sleep(1000);
        WebElement userName_editbox = webDriver.findElement(By.id("usr"));
        Thread.sleep(1000);
        WebElement password_editbox = webDriver.findElement(By.id("pwd"));
        Thread.sleep(1000);
        WebElement submit_button = webDriver.findElement(By.xpath("//input[@value='Login']"));
        Thread.sleep(1000);
        userName_editbox.sendKeys(username);
        Thread.sleep(1000);
        password_editbox.sendKeys(Password);
        Thread.sleep(1000);
        submit_button.click();

    }

    public String getText() throws IOException, InterruptedException {

        Thread.sleep(1000);
        String text = webDriver.findElement(By.xpath("//div[@id='case_login']/h3")).getText();
        return text;

    }

    public void closeBrowser() {
        webDriver.close();
    }

    public void openTestSite() {
        webDriver.navigate().to("http://testing-ground.scraping.pro/login");
    }

}
