package ThreadSafe.cas;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2017/12/19 下午3:57
 */
public class UserVersion {

    private int version;

    public boolean compareAndSet(int versionExcept,int versionUpdate){
        synchronized (this){
            if (version == versionExcept){
                version=versionExcept;
                return true;
            }
            return false;
        }
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
