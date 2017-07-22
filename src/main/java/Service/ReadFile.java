/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReadFile {

    public List<String> readFile(String path) {
        BufferedReader br = null;
        FileReader fr = null;
        List<String> lists = new ArrayList<String>();
        try {

            fr = new FileReader(path);
            br = new BufferedReader(fr);

            String read = "";

            br = new BufferedReader(new FileReader(path));

            while ((read = br.readLine()) != null) {
                lists.add(read);
            }
            return lists;

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
        return null;
    }
}
