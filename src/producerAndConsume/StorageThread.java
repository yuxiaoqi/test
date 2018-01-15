package producerAndConsume;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2017/12/14 下午8:12
 */
public class StorageThread implements Runnable{

    private Storage storage;

    public StorageThread(Storage storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        storage.say();
    }



}
