/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ConstantVariable.ConstantFtpServer;

@Service
public class UploadServices {

    @Autowired
    Session session;

    public String uploadFileTxtToSFtpServer(String Info, String name) throws FileNotFoundException {

        //FileInputStream fis = null;
        // FileOutputStream fos = null;
        String message = "";
        String filePath = new File("").getAbsolutePath();
        filePath.concat("nb-configuration.xml");

        try {
            //config
            Channel channel = null;
            ChannelSftp channelSftp = null;
            session.setTimeout(15000);
            if (!session.isConnected()) {
                session.setPassword(ConstantFtpServer.password);
                session.connect();
            }

            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;

            channelSftp.cd(ConstantFtpServer.homedir + "/range");//local

            //write data to bytes
            //byte[] bytes = ObjectToByte(ListsInfo);
            Path path = Paths.get("/app/nb-configuration.xml");
            OutputStream outputStream = channelSftp.put(ConstantFtpServer.homedir + "/range/" + name + ".txt");//remote
            //write byte to stream
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(Info);
            bufferedWriter.newLine();

            bufferedWriter.close();
            //outputStream.write(bytes);

            Files.copy(path, outputStream);

            channel.disconnect();

            return "filename.txt";
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }
}
