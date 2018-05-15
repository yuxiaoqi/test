package serializable;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/5/3 下午5:17
 */
public class SerializableArrayListDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<String> stringList = new ArrayList<String>();
        stringList.add("hello");
        stringList.add("world");
        stringList.add("hollis");
        stringList.add("chuang");
        System.out.println("init StringList" + stringList);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("stringlist"));
        objectOutputStream.writeObject(stringList);

        File file = new File("stringlist");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        List<String> newStringList = (List<String>)objectInputStream.readObject();
        if(file.exists()){
            file.delete();
        }
        System.out.println("new StringList" + newStringList);
    }
}
