package Service;

import ConstantVariable.Constant;
import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.stereotype.Service;

@Service
public class CreateWebdriver {

    public WebDriver getFirefox() {

        try {
            System.setProperty(Constant.webDriverFirefox, Constant.dirDriverFirefox);
            File pathToBinary = new File(Constant.binaryFirefox);
            FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            WebDriver webDriver = new FirefoxDriver(ffBinary, firefoxProfile);
            return webDriver;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
