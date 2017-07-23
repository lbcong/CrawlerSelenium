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

    Constant constant;
    WebDriver webDriver;

    private Thread[] thread;
    private int NumberAccount = 14;
    private List<AccountInfo> listAccountInfo = null;
    private boolean FlagActive = false;

    @PostConstruct
    public void initBinder() {
        try {
            //
            System.setProperty(constant.webDriverGoogle, constant.binaryGoogle);
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

                        List<String> s_list = readFile.readFile(constant.dirFileAccount);
                        listAccountInfo = getInfoAccount.getListInfo(s_list);
                        //
                        FlagActive = true;
                        //
                        thread = new Thread[NumberAccount];
                        //
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
                    e.getMessage();
                }

            }
        };
        thread[id_thread].start();
    }

    public void createVPS(int indexofAccount) {
        String output = "";
        try {
            WebDriver webDriver = new ChromeDriver();

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
                Thread.sleep(100);
                dply_co.CreateServer(webDriver);
                //
                Thread.sleep(180000);
                //
                String rs = dply_co.getIP(webDriver);

                if (rs == null) {
                    continue;
                }

                listAccountInfo.get(indexofAccount).setIp(getInfoAccount.getIp(rs));
                //
                listAccountInfo.get(indexofAccount).setKey(constant.dirKey + listAccountInfo.get(indexofAccount).getUser() + ".ppk");
                //
                writeFile.writeFile(listAccountInfo);
                //
                Session session = null;
                connectSSH.connectSSH(listAccountInfo.get(indexofAccount), session);
                // sau 2 tieng

                Thread.sleep(7200000);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    public void closeBrowser() {
        webDriver.close();
    }
}
