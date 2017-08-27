package Service;

import Bean.SystemCofig;
import ConstantVariable.Constant;
import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateWebdriver {

    @Autowired
    SetPathDriver setPathDriver;
    @Autowired
    SystemCofig SystemCofig;

    public WebDriver getFirefox(String binaryFirefox) {
        setPathDriver.setPathFireFox();
        //
        WebDriver webDriver = null;
        try {
            System.setProperty(SetPathDriver.webDriverFirefox, SetPathDriver.dirDriverFirefox);
            File pathToBinary = new File(binaryFirefox);
            FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            webDriver = new FirefoxDriver(ffBinary, firefoxProfile);
            return webDriver;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            return webDriver;
        }
    }

    // neu file binary de o? thuc muc khac' khong phai /user/bin , vi du heroku
    public WebDriver getGoogle(String binaryGoogle) {
        //
        setPathDriver.setPathGoogle();
        //
        WebDriver webDriver = null;
        try {
            //
            System.setProperty(SetPathDriver.webDriverGoogle, SetPathDriver.dirDriverGoogle);

            switch (SystemCofig.os) {
                case "Linux":
                    ChromeOptions options = new ChromeOptions();
                    options.setBinary(binaryGoogle);
                    webDriver = new ChromeDriver(options);
                    break;
                case "Windows":
                    webDriver = new ChromeDriver();
                    break;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            return webDriver;
        }

    }

}
