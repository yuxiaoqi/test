package collection.Generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: yuyanqi
 * @Date 2016/5/21 15:38
 * @Vesion V1.0
 */
public class test4 {
    public static void main(String[] args){
            //return 只会回滚test4 方法 不会回滚了 main方法
             new test4().testReturn();
          System.out.print("after ------return");
    }

    private  void testReturn(){
        if(2==2){
            return;
        }
    }

}
