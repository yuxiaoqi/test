package observer;

import java.util.Observable;

/**
 * @ClassName:
 * @Description: 被观察者
 * @Author yuxiaoqi
 * @Create 2018/6/6 下午5:10
 */
public class Watched extends Observable{

    @Override
    public void notifyObservers(Object arg) {
        super.setChanged();
        super.notifyObservers(arg);
    }
}
