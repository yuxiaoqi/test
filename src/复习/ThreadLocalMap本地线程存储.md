## ThreadLocal 中重要的基础类
 * java.lang.ThreadLocal.ThreadLocalMap   **ThreadLocalMap** 在ThreadLocal中的内部类
 * 在 **ThreadLocalMap** 中还有一个 **java.lang.ThreadLocal.ThreadLocalMap.Entry** 一个继承了 持有ThreadLocal的若引用
 * 示意图 java.lang.ThreadLocal【static java.lang.ThreadLocal.ThreadLocalMap【java.lang.ThreadLocal.ThreadLocalMap.Entry extends WeakReference<ThreadLocal<?>> 】】
 * 这两个类就是实现的关键： ThreadLocalMap 其实他不是我们传统意义上的map：不是 node这种 有k v;
   * **ThreadLocalMap 中的key是 ThreadLocal 对象**。根据  ThreadLocal对象的hashcode&（n-1）来获取Entry[] 索引
   * 通过线性探测的方法解决hash冲突：就是 hashcode&（n-1）获取到i后，如果存在并且 ThreadLocal != Entry.get(),就移动到i+1的位置往后移
   * 依次判断 要么为空插入条目。要么有值ThreadLocal = Entry.get() 将值替换
 * 在让我们看看  **Thread 中 ThreadLocal.ThreadLocalMap threadLocals** 会有一个 ThreadLocal.ThreadLocalMap对象
   * 来作为线程本地存储的 map  Thread线程中的 ThreadLocal对象都会放到 ThreadLocal.ThreadLocalMap中
