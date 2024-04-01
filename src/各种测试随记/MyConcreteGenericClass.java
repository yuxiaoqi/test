package 各种测试随记;

public class MyConcreteGenericClass implements MyGenericInterface {

    // 使用 String 作为 T，Integer 作为 U 的具体实现

    public static void main(String[] args) {
        MyGenericInterface instance = new MyConcreteGenericClass();
// 调用方法，传入具体的类型参数
        String resultOne = instance.methodOne(10, "Hello");
        String resultTwo = instance.methodTwo(20, "World");
        System.out.println("Result One: " + resultOne);
        System.out.println("Result Two: " + resultTwo);
    }

    @Override
    public <T extends Comparable<T>, U> T methodOne(U u, T t) {
        return t;
    }

    @Override
    public <K, V extends Comparable<V>> V methodTwo(K k, V v) {
        return v;
    }
}