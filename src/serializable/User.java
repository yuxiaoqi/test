package serializable;

import java.io.Serializable;

public class User implements Serializable {


    private static final long serialVersionUID = 6545711505683529496L;
    public static String test ="test";

    private String name;
    private int age;

    private transient String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", test=" + test +
                '}';
    }
}