/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restcontroller;

import Pojos.AccountInfo;
import Service.ConnectSSH;
import Service.Dply_co;
import Service.GetInfoAccount;
import Service.GitHub;
import Service.ReadFile;
import Service.WriteFile;
import com.jcraft.jsch.Session;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static restcontroller.GreedingController.webDriver;
import ConstantVariable.Constant;
import java.io.File;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import static restcontroller.GreedingController.webDriver;

@RestController
public class CreateVpsDly {

    @Autowired
    GitHub gitHub;
    @Autowired
    ReadFile readFile;
    @Autowired
    GetInfoAccount getInfoAccount;
    @Autowired
    ConnectSSH connectSSH;
    @Autowired
    WriteFile writeFile;
    @Autowired
    Dply_co dply_co;

    private Thread[] thread;
    private int NumberAccount = 2;
    private List<AccountInfo> listAccountInfo = null;
    private boolean FlagActive = false;

    @PostConstruct
    public void initBinder() {
        try {
            //
            //System.setProperty(Constant.webDriverGoogle, Constant.dirDriverGoogle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String start() {
        String output = "";
        try {
            Thread startThread = new Thread() {
                @Override
                public void run() {
                    try {

                        List<String> s_list = readFile.readFile(Constant.dirFileAccount);
                        listAccountInfo = getInfoAccount.getListInfo(s_list);
                        NumberAccount = listAccountInfo.size();
                        System.out.println("NumberAccount:" + NumberAccount);
                        //
                        FlagActive = true;

                        thread = new Thread[NumberAccount];

                        for (int i = 0; i < NumberAccount; i++) {
                            createThreadToHack(i);
                            Thread.sleep(120000);
                        }
                    } catch (Exception e) {
                        e.getMessage();
                    }

                }
            };
            startThread.start();

            return output = "starting";
        } catch (Exception e) {
            e.getMessage();
            return "loi : " + e.getMessage();
        }

    }

    public void createThreadToHack(int id_thread) {

        thread[id_thread] = new Thread() {
            @Override
            public void run() {
                try {
                    createVPS(id_thread);
                } catch (Exception e) {
                    System.out.println("loi:" + e.getMessage());
                }

            }
        };
        thread[id_thread].start();
    }

    public void createVPS(int indexofAccount) throws Exception {

        String output = "";

//        File pathToBinary = new File(Constant.binaryFirefox);
//        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
//        FirefoxProfile firefoxProfile = new FirefoxProfile();
//        WebDriver webDriver = new FirefoxDriver(ffBinary, firefoxProfile);
        System.setProperty(Constant.webDriverGoogle, Constant.dirDriverGoogle);
        ChromeOptions options = new ChromeOptions();
        options.setBinary(Constant.binaryGoogle);

        WebDriver webDriver = new ChromeDriver(options);

        while (true) {
            if (!FlagActive) {
                break;
            }
            //
            dply_co.OpenDly(webDriver);
            Thread.sleep(2000);
            //
            gitHub.LoginGitHub(listAccountInfo.get(indexofAccount).getUser(),
                    listAccountInfo.get(indexofAccount).getPass(),
                    webDriver);
            //
            System.out.println("getUser:" + listAccountInfo.get(indexofAccount).getUser());
            Thread.sleep(100);
            dply_co.CreateServer(webDriver);
            System.out.println("CreateServer:");
            //
            Thread.sleep(120000);
            //
            String rs = dply_co.getIP(webDriver);
            System.out.println("getIP:");

            if (rs == null) {
                continue;
            }
            listAccountInfo.get(indexofAccount).setIp(getInfoAccount.getIp(rs));
            //
            listAccountInfo.get(indexofAccount).setKey(Constant.dirKey + listAccountInfo.get(indexofAccount).getUser() + Constant.typeKeyPub);
            //
            writeFile.writeFile(listAccountInfo);
            //
            Session session = null;
            connectSSH.connectSSH(listAccountInfo.get(indexofAccount), session);
            // sau 2 tieng

            Thread.sleep(7200000);

        }

    }

}
