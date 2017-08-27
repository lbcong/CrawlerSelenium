/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restcontroller;

import ConstantVariable.Constant;
import Service.CodeAnyWhere;
import Service.CreateWebdriver;
import Service.UploadServices;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KeepLiveCodeanywhere {

    @Autowired
    UploadServices uploadServices;
    @Autowired
    CodeAnyWhere codeAnyWhere;
    @Autowired
    CreateWebdriver createWebdriver;

    @RequestMapping(value = "/loginCodeAny", method = RequestMethod.GET)
    public String inputvideo() {

        return "CodeAnyLogin";
    }

    @RequestMapping(value = "/CodeAny", method = RequestMethod.GET)
    public String seleniumGoogle(
            @RequestParam(value = "user", required = true) String user,
            @RequestParam(value = "pass", required = true) String pass,
            @RequestParam(value = "id", required = true) String id) {
        String output = "";
        try {
            Thread startThread = new Thread() {
                @Override
                public void run() {
                    try {

                        openBrowser(user, pass, id);
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
        return "CodeAnyLogin";
    }

    public void openBrowser(String user, String pass, String id) {

        WebDriver webDriver = createWebdriver.getGoogle(Constant.binaryGoogleHeroku);

        try {
            codeAnyWhere.LoginCodeAnyWhere(user, pass, webDriver);
            String info = codeAnyWhere.getInfo(webDriver, id);
            if (info != null) {
                uploadServices.uploadFileTxtToSFtpServer(info, user);
                System.out.println("upload : done " + info);
            }

        } catch (Exception e) {
            System.out.println("openbrowser : " + e.getMessage());
        }

    }

}
