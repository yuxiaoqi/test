package properties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by admin on 2016/9/8.
 */
public class PropertyTest {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            //读取配置文件
            InputStream in  = new BufferedInputStream(new FileInputStream("src/test.properties"));
            properties.load(in);
            if(Boolean.parseBoolean(properties.getProperty("ispass"))){
                System.out.println("名字是"+properties.getProperty("username"));
                System.out.println("密码是："+properties.getProperty("passworld"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
