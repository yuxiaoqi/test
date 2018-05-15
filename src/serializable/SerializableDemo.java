package serializable;

import sun.misc.IOUtils;

import java.io.*;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/5/3 下午12:08
 */
public class SerializableDemo {

    public static void main(String[] args) {
        User user = new User();
        String aa = user.test;
        user.setAge(5);
        user.setGender("gender");
        user.setName("name");

        //序列化
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            org.apache.poi.util.IOUtils.closeQuietly(oos);
        }

        //反序列化
        File file = new File("tempFile");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            User newUser = (User) ois.readObject();
            System.out.println(newUser);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            org.apache.poi.util.IOUtils.closeQuietly(ois);

        }

    }
}
