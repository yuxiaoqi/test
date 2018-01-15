package producerAndConsume;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2017/12/14 下午8:14
 */
public class StorageThread1 implements Runnable {

    private Storage storage;

    public StorageThread1(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        storage.hello();
        try {
            System.out.println(Thread.currentThread().getName()+"执行完第一个 hello 休息 5秒");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        storage.hello();
    }
}
