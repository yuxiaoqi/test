package StaticFactory.providerImpl;

import StaticFactory.Provider;
import StaticFactory.Service;
import StaticFactory.ServiceImpl.ServiceHelloImpl;

/**
 * Created by admin on 2016/11/4.
 */
public class ProviderHelloImpl  implements Provider{
    @Override
    public Service newService() {
        return new ServiceHelloImpl();
    }
}
