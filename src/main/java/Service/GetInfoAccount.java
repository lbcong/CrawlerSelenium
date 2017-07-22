/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Pojos.AccountInfo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GetInfoAccount {

    public List<AccountInfo> getListInfo(List<String> ListsInfo) {

        List<AccountInfo> lists = new ArrayList<>();
        try {
            for (int i = 0; i < ListsInfo.size(); i++) {
                if (!ListsInfo.get(i).equals("") && !ListsInfo.get(i).equals(" ")) {
                    AccountInfo info = new AccountInfo();
                    String[] temp = ListsInfo.get(i).split("\\|");
                    if (temp.length >= 3) {
                        info.setUser(temp[0]);
                        info.setPass(temp[1]);
                        info.setKey(temp[2]);

                        lists.add(info);
                    }

                }

            }
            return lists;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public String getIp(String s_Ip) {
        String newtext = s_Ip;
        try {
            newtext = newtext.replaceAll("\\s+", "");
            newtext = newtext.replaceAll("Ubuntu16.04-", "");
            return newtext;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

}
