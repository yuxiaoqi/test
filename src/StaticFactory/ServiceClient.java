package StaticFactory;

import StaticFactory.providerImpl.ProviderHelloImpl;

/**
 * Created by admin on 2016/11/4.
 */
public class ServiceClient {
    public static void main(String[] args) {
        //注册 provider
        Services.registerDefaultProvider(new ProviderHelloImpl());
        //注册 provider by name
        Services.registerProvider("算法.test",new ProviderHelloImpl());
        //获取 服务
        Service service = Services.newInstance();
        //获取 services by name
        Service serviceTest = Services.newInstance("test2");
        // 调用服务接口
        /*service.say();*/
        serviceTest.say();
    }
}
