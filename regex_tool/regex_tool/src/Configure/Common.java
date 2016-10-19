package Configure;

import java.io.FileInputStream;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * Created by johnzheng on 2016/9/27.
 */
public class Common {

    public static String getValue(String key){
        String value = null;
        try {
            FileInputStream in = new FileInputStream(URLDecoder.decode(Common.class.getResource("/").getPath()+"/Configure/config.properties","utf-8"));
            Properties p = new Properties();
            p.load(in);
            value = (String)p.get(key);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return value;
    }

}
