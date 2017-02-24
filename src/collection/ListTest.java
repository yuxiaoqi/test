package collection;

import com.google.common.collect.Lists;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/3/24.
 */
@Slf4j
public class ListTest {


            public static void main(String[] args)
            {
                //    非泛型
                List my= new ArrayList();
                Integer a = 5;
                my.add(a);
                Integer aa = (Integer) my.iterator().next();
                System.out.print(aa);

                // 采用泛型的方式
                 List<Integer> myGeneric = new ArrayList<Integer>();
                 Integer aGeneric = 4;
                 myGeneric.add(aGeneric);
                 Integer aaGeneric = myGeneric.iterator().next();
                 System.out.println(aaGeneric);

                //List 为空的 size（）方法使用

                 ArrayList<String> strings  = null;
              //  int temp=(strings==null) ? 0 : strings.size();
                System.out.println("数目:"+((strings==null) ? 0 : strings.size()) );
                //区别
                System.out.println("数目:"+(strings==null ? 0 : strings.size()) );
                Lists.newArrayList("test");




        }
        }
