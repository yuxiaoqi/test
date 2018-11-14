package observer;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/6/6 下午5:24
 */
public class WatcherTest {
    public static void main(String[] args) {
        Watcher watcher = new Watcher();
        Watched watched = new Watched();
        watched.addObserver(watcher);
        watched.notifyObservers("openWindows");
    }
}
