package EffectJava;

import lombok.Data;

/**
 * Created by yyq
 * Date  2016/12/6.
 * version 1.0.0.0
 */

public enum Elvis {
    //每一个实例 必须要
    INstance("test") {
        @Override
        public void test() {

        }
    };
    private String name;
    private Elvis(String name){
        this.name = name;
    }

    //枚举类型中创建抽象方法
    public abstract void test();

}
