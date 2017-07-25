/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import ConstantVariable.Constant;
import Pojos.AccountInfo;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WriteFile {

    public void writeFile(List<AccountInfo> listAccountInfo) {
        try {

            //ghi file
            FileOutputStream outputStream = new FileOutputStream(Constant.dirFileResult);
            //OutputStreamWriter  BufferedWriter dung de ghi string
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            for (int i = 0; i < listAccountInfo.size(); i++) {
                bufferedWriter.write(listAccountInfo.get(i).getUser() + "|"
                        + listAccountInfo.get(i).getIp());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (Exception e) {
            e.getMessage();
        }
    }
}
