package restcontroller;

import Service.Dply_co;
import Service.GitHub;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static restcontroller.GreedingController.webDriver;

@RestController
public class Test {

    @Autowired
    GitHub gitHub;
    @Autowired
    Dply_co dply_co;
    WebDriver webDriver;

    @RequestMapping(value = "/create/{username}/{pass}", method = RequestMethod.GET)
    public String createVPS(
            @PathVariable("username") String username,
            @PathVariable("pass") String pass) {
        String output = "";
        try {
            webDriver = new ChromeDriver();
            //webDriver = new HtmlUnitDriver();
            dply_co.OpenDly(webDriver);
            gitHub.LoginGitHub(username, pass, webDriver);
            //dply_co.CreateServer(webDriver);
            //dply_co.getIP(webDriver);
          
            //closeBrowser();
            return output;
        } catch (Exception e) {
            e.getMessage();
            return "loi : " + e.getMessage();
        }

    }

    @RequestMapping(value = "/getInfo/{username}/{pass}", method = RequestMethod.GET)
    public String getInfo(
            @PathVariable("username") String username,
            @PathVariable("pass") String pass) {
        String output = "";
        try {

            dply_co.OpenDly(webDriver);
            gitHub.LoginGitHub(username, pass, webDriver);
            output = dply_co.getIP(webDriver);

            //closeBrowser();
            return output;
        } catch (Exception e) {
            e.getMessage();
            return "loi : " + e.getMessage();
        }

    }

    public void closeBrowser() {
        webDriver.close();
    }

    public void openTestSite() {
        //webDriver.navigate().to("https://github.com/login");
        webDriver.navigate().to("https://dply.co/");
    }

}