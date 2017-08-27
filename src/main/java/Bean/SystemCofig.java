package Bean;

import org.springframework.stereotype.Service;

@Service
public class SystemCofig {

    public static String os;

    public SystemCofig() {
        if (System.getProperty("os.name").contains("Windows")) {
            os = "Windows";
        }
        if (System.getProperty("os.name").contains("Linux")) {
            os = "Linux";
        }
    }
}
