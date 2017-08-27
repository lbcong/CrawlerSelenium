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

import java.util.List;
import javax.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ConstantVariable.Constant;
import Service.CreateWebdriver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
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
    @Autowired
    CreateWebdriver createWebdriver;

    private Thread[] thread;
    private int NumberAccount = 2;
    private List<AccountInfo> listAccountInfo = null;
    private boolean FlagActive = false;

    @PostConstruct
    public void initBinder() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/miner", method = RequestMethod.GET)
    public String inputvideo() {

        return "Miner";
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String start(
            @RequestParam(value = "index", required = true) int index) {
        String output = "";
        try {
            Thread startThread = new Thread() {
                @Override
                public void run() {
                    try {
                        //
                        List<String> s_list = readFile.readFile(Constant.dirFileAccount);
                        //
                        listAccountInfo = getInfoAccount.getListInfo(s_list);
                        //
                        NumberAccount = listAccountInfo.size();
                        //
                        System.out.println("NumberAccount:" + NumberAccount);
                        //
                        FlagActive = true;
                        //
                        thread = new Thread[NumberAccount];

//                        for (int i = 0; i < NumberAccount; i++) {
                        createThreadToHack(index);
                        Thread.sleep(120000);
//                        }
                    } catch (Exception e) {
                        e.getMessage();
                    }

                }
            };
            startThread.start();

            return output = "Miner";
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

        WebDriver webDriver = createWebdriver.getGoogle(Constant.binaryGoogleHeroku);

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
            System.out.println("getUser:" + listAccountInfo.get(indexofAccount).getUser() + listAccountInfo.get(indexofAccount).getPass());
            Thread.sleep(1000);
            dply_co.CreateServer(webDriver);

            //
            Thread.sleep(120000);
            //
            String rs = dply_co.getIP(webDriver);

            if (rs == null) {
                continue;
            }
            listAccountInfo.get(indexofAccount).setIp(getInfoAccount.getIp(rs));
            //
            listAccountInfo.get(indexofAccount).setKey(Constant.dirKey + listAccountInfo.get(indexofAccount).getUser() + Constant.typeKeyPPK);
            //
            writeFile.writeFile(listAccountInfo);

            //
            Session session = null;
            connectSSH.connectSSH(listAccountInfo.get(indexofAccount), session);
            // sau 2 tieng

            Thread.sleep(8200000);

        }

    }

}
