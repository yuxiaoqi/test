package StaticFactory.ServiceImpl;

import StaticFactory.Service;

/**
 * Created by admin on 2016/11/4.
 */
public class ServiceHelloImpl implements Service {

    /**
     * 服务 测试 接口
     */
    @Override
    public void say() {
        System.out.println("i can say");
    }
}
