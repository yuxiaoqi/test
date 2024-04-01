package polymorphic;

public class B extends A{
    public String show(B obj){
        return ("并发编程.B and 并发编程.B");
    }

    public String show(A obj){
        return ("并发编程.B and 并发编程.A");
    } 
}
