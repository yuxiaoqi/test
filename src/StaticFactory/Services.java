package StaticFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by admin on 2016/11/4.
 */
public class Services {
    private Services(){}

    private  static  Map<String,Provider> providerMap = new ConcurrentHashMap<>();
    private static final String DEFAULT_PROVIDER_NAME = "<def>";
    //provider register api
    public static void registerDefaultProvider(Provider p){
        registerProvider(DEFAULT_PROVIDER_NAME,p);
    }
    public static void registerProvider(String name ,Provider p) {
        providerMap.put(name,p);
    }

    //service access api
    public  static  Service newInstance(){
        return newInstance(DEFAULT_PROVIDER_NAME);
    }

    public static Service newInstance(String name) {
        Provider p = providerMap.get(name);
        if (null == p){
            throw new IllegalArgumentException("NO provider registered with name "+name);
        }
        return p.newService();
    }


}
