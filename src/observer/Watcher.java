package observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @ClassName:
 * @Description:观察者
 * @Author yuxiaoqi
 * @Create 2018/6/6 下午5:07
 */
public class Watcher implements Observer{
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String){
            if (arg.equals("openWindows")){
                System.out.println("我是观察者已经打开窗口");
            }
        }
    }

    public static void main(String[] args) {
        String test = "null_xjdrall3";
        if (test.contains("_")){
            String temp = test.split("_")[1];

        }
    }
}
