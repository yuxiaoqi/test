package polymorphic;

public class A {
    public String show(D obj) {
        return ("并发编程.A and D");
    }

    public String show(A obj) {
        return ("并发编程.A and 并发编程.A");
    } 

}
