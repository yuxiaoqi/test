package EffectJava;

import java.util.Date;

/**
 * Created by yyq
 * Date  2017/1/10.
 * version 1.0.0.0
 */
public class AutoBoxingTest {
    public static void main(String[] args) {
        //Long sum = 0L; //基本数据类型的自动装箱
        long sum = 0;
        Date startDate =new Date();
        System.out.println("开始时间--"+startDate);
        for (long i =0;i < Integer.MAX_VALUE;i++){
            sum += i;   //如果采用装箱类 每一次循环机会创建新的 对象 效率会很低 浪费时间
        }
        Date endDate = new Date();
        System.out.println("结果---"+sum);
        System.out.println("结束时间--"+endDate);
    }

}
