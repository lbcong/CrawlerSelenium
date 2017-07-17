/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restcontroller;

import Service.Dply_co;
import Service.GitHub;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateVpsDly {

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
            System.setProperty("webdriver.chrome.driver", "D:\\NetBeansProjects\\Service Cloud\\chromedriver_win32\\chromedriver.exe");
            webDriver = new ChromeDriver();

            dply_co.OpenDly(webDriver);
            gitHub.LoginGitHub(username, pass, webDriver);
            dply_co.CreateServer(webDriver);
            dply_co.getIP(webDriver);

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

            webDriver = new HtmlUnitDriver();
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
}
