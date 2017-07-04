package restcontroller;

import java.io.File;
import java.io.IOException;

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
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreedingController {

    public static WebDriver webDriver = null;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String greeding() {

        return "Hello ";
    }

    @RequestMapping(value = "/openbrowser", method = RequestMethod.GET)
    public String selenium() {
        String output = "";
        try {
            System.setProperty("webdriver.chrome.driver", "D:\\NetBeansProjects\\Service Cloud\\chromedriver_win32\\chromedriver.exe");
            //https://www.youtube.com/watch?v=sYbd4jDn-kA
            // webDriver = new HtmlUnitDriver();
            webDriver = new ChromeDriver();
            //testVideo();
            openTestSite();
            login("admin", "12345");
            output=getText();
            closeBrowser();
            return output;
        } catch (Exception e) {
            e.getMessage();
            return "loi : " + e.getMessage();
        }

    }

    @RequestMapping(value = "/openbrowserGG", method = RequestMethod.GET)
    public String seleniumGoogle() {
        String output = "";
        try {
            System.setProperty("webdriver.chrome.driver", "D:\\NetBeansProjects\\Service Cloud\\chromedriver_win32\\chromedriver.exe");
            //https://www.youtube.com/watch?v=sYbd4jDn-kA
            webDriver = new ChromeDriver();

            testVideo();
            // closeBrowser();
            return "";
        } catch (Exception e) {
            e.getMessage();
            return "loi : " + e.getMessage();
        }

    }

    public void testVideo() throws InterruptedException {

        try {
            //FlashObjectWebDriver flashApp = new FlashObjectWebDriver(webDriver, "movie_player");

            webDriver.get("https://www.youtube.com/watch?v=sYbd4jDn-kA");
            Thread.sleep(8000);

        } catch (Exception e) {
            e.getMessage();
        }

    }

    public void login(String username, String Password) {

        try {
            WebElement userName_editbox = webDriver.findElement(By.id("usr"));
            WebElement password_editbox = webDriver.findElement(By.id("pwd"));
            WebElement submit_button = webDriver.findElement(By.xpath("//input[@value='Login']"));

            userName_editbox.sendKeys(username);
            password_editbox.sendKeys(Password);
            submit_button.click();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    public String getText() throws IOException {

        try {
            String text = webDriver.findElement(By.xpath("//div[@id='case_login']/h3")).getText();
            return text;
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public void closeBrowser() {
        webDriver.close();
    }

    public void openTestSite() {
        webDriver.navigate().to("http://testing-ground.scraping.pro/login");
    }

}
