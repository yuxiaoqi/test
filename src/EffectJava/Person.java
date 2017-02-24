package EffectJava;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Description： Builder模式
 * Created by yyq
 * Date  2016/12/3.
 * version 1.0.0.0
 */
@Data
public class Person {
    private static final Logger log = LoggerFactory.getLogger(Person.class);
    private Integer age;
    private String name;
    private Integer hight;
    private String hobits;

    public Person(Bulider bulider) {
        this.age = bulider.age;
        this.name = bulider.name;
        this.hight = bulider.hight;
        this.hobits = bulider.hobits;
    }

    public static class Bulider {
        //Init value
        private Integer age;
        private String name;
        private Integer hight;
        private String hobits;

        public Bulider(Integer age, String name) {
            this.age = age;
            this.name = name;
        }

        public Bulider setHight(Integer hight) {
            this.hight = hight;
            return this;
        }

        public Bulider setHobits(String hobits) {
            this.hobits = hobits;
            return this;
        }

        public Person build() {
            return new Person(this);
        }


    }
    public String toString(){
        return super.toString()+" age ="+age+" name="+name+"\n"+" hight="+hight+" hobits="+hobits;
    }
    public static void main(String[] args) {
        /**
         * 构造器方法
         * 1：构造器 通过设置不同参数 来构造不同的实例对像 缺点：不灵活
         * 2：javaBean 模式 。多出调用 线程不安全，需要额外的维护线程的安全。有点：灵活
         * 3：builder 模式，既可以保证安全性，又可以很灵活的
         */
        PropertyConfigurator.configure("src/log4j-contrl.properties");
        Person person = new Person.Bulider(23,"haha").setHobits("吃饭").setHight(183).build();
        log.info("对象----{}",person.toString());
    }
}
