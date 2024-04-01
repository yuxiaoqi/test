package 各种测试随记;

public interface MyGenericInterface {

    // 方法1：具有两个泛型参数T和U，其中T必须是Comparable<T>的实例
    <T extends Comparable<T>, U> T methodOne(U u, T t);

    // 方法2：具有两个泛型参数K和V，其中V必须继承自Comparable<V>
    <K, V extends Comparable<V>> V methodTwo(K k, V v);

    // 如果需要，可以继续添加其他方法，每个都可以有不同的泛型参数
}