package Service;

import java.io.File;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetPathDriver {

    @Autowired
    ServletContext servletContext;

    public static String webDriverFirefox;
    public static String dirDriverFirefox;
    public static String binaryFirefox;

    public static String webDriverGoogle;
    public static String dirDriverGoogle;
    public static String binaryGoogle;

    public String getPath() {
        try {
            String realpath = servletContext.getRealPath("");
            String[] temp = realpath.split("target", 2);
            return temp[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setPathGoogle() {
        String realpath = getPath();
        String os = System.getProperty("os.name");
        if (os.contains("Windows")) {
            os = "Windows";
        }
        if (os.contains("Linux")) {
            os = "Linux";
        }

        switch (os) {
            case "Linux":
                webDriverGoogle = "webdriver.chrome.driver";
                dirDriverGoogle = realpath + File.separator + "chromedriver";
                binaryGoogle = "/usr/bin/google-chrome";
                break;
            case "Windows":
                webDriverGoogle = "webdriver.chrome.driver";
                dirDriverGoogle = realpath + File.separator + "chromedriver.exe";
                binaryGoogle = "google-chrome.exe";
                break;
        }

    }

    public void setPathFireFox() {
        String realpath = getPath();

        switch (System.getProperty("os.name")) {
            case "Linux":
                webDriverFirefox = "webdriver.gecko.driver";
                dirDriverFirefox = realpath + File.separator + "geckodriver.exe";
                binaryFirefox = "/usr/bin/firefox";
                break;
            case "Windows":
                webDriverFirefox = "webdriver.gecko.driver";
                dirDriverFirefox = realpath + File.separator + "geckodriver";
                binaryFirefox = "E:\\Soft\\FirefoxPortable\\App\\Firefox64\\firefox.exe";
                break;
        }

    }

}
