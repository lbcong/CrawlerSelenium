package Service;

import Bean.SystemCofig;
import java.io.File;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetPathDriver {

    @Autowired
    ServletContext servletContext;
    @Autowired
    SystemCofig SystemCofig;
    public static String webDriverFirefox;
    public static String dirDriverFirefox;

    public static String webDriverGoogle;
    public static String dirDriverGoogle;

    public SetPathDriver() {

    }

    public String getPath() {
        try {
            String realpath = servletContext.getRealPath("");
            String[] temp;
            switch (SystemCofig.os) {
                case "Linux":
                    temp = realpath.split("apache-tomcat-8.0.46", 2);

                    return temp[0];

                case "Windows":
                    temp = realpath.split("target", 2);
                    return temp[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setPathGoogle() {
        String realpath = getPath();
        webDriverGoogle = "webdriver.chrome.driver";
        switch (SystemCofig.os) {
            case "Linux":

                dirDriverGoogle = realpath + File.separator + "chromedriver";

                break;
            case "Windows":

                dirDriverGoogle = realpath + File.separator + "chromedriver.exe";

                break;
        }

    }

    public void setPathFireFox() {
        String realpath = getPath();
        webDriverFirefox = "webdriver.gecko.driver";
        switch (SystemCofig.os) {
            case "Linux":
                dirDriverFirefox = realpath + File.separator + "geckodriver";
                break;
            case "Windows":
                dirDriverFirefox = realpath + File.separator + "geckodriver.exe";

                break;
        }

    }

}
