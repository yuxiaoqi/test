package collection;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by admin on 2016/4/7.
 */
@Slf4j
@SuppressWarnings("unchecked")
public class LogTest {

    public  static  void main(String [] args){
        Test test=null;
     // log.info("我的空测试--{}",(test==null? 0 :test.toString()));
    }
}


class Test{
    int a;
    int b;

}


