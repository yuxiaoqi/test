package Generic;



import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yyq
 */
public class Util {
    public static <K,V> void get(K value,V valuse){

    }

    public static void printObject(List<?> ls){
        for (Object o : ls){
            System.out.println(o);
        }
    }


/*    public static void main(String[] args) {
        List<Integer> lsteger = new ArrayList<>();
        lsteger.add(2);
        Util.printObject(lsteger);

    }*/

    public static <E> E getFirstElement(List<E> list){
        if (list == null || list.isEmpty()){
            return null ;
        }
        return list.get(0);
    }
}
