package restcontroller;

import ConstantVariable.Constant;
import Service.CreateWebdriver;
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

@Controller
public class BuffViewYouTube {

    @Autowired
    CreateWebdriver createWebdriver;

    @RequestMapping(value = "/inputvideo", method = RequestMethod.GET)
    public String inputvideo() {

        return "login";
    }

    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public String seleniumGoogle(
            @RequestParam(value = "url", required = true) String url,
            @RequestParam(value = "time", required = true) int time) {
        String output = "";
        try {
            Thread startThread = new Thread() {
                @Override
                public void run() {
                    try {

                        testVideo(url, time);

                    } catch (Exception e) {
                        e.getMessage();
                    }

                }
            };
            startThread.start();

        } catch (Exception e) {
            e.getMessage();
            return "loi : " + e.getMessage();
        }
        return "login";
    }

    public void testVideo(String url, int timeout) throws InterruptedException {
//        ChromeOptions options = new ChromeOptions();
//        options.setBinary(Constant.binaryGoogle);
//        System.setProperty("webdriver.chrome.driver", Constant.dirDriverGoogle);
        while (true) {
            try {
                WebDriver webDriver = createWebdriver.getFirefox();
//                WebDriver webDriver = new ChromeDriver(options);
//                WebDriver webDriver = new ChromeDriver();
                System.out.println("video1 : " + url);
                webDriver.get(url);
                Thread.sleep((timeout * 60 + 5) * 1000);
//                WebElement video = webDriver.findElement(By.id("movie_player"));
                System.out.println("video2 : " + url);
//                video.click();
//                System.out.println("click : " + url);
                webDriver.quit();
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        }

    }

}
