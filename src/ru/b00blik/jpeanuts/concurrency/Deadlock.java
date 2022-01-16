package ru.b00blik.jpeanuts.concurrency;

public class Deadlock implements Runnable{

    A a = new A();
    B b = new B();

    public Deadlock() {
        Thread.currentThread().setName("MainThread");
        Thread t = new Thread(this, "RacingTread");
        t.start();

        a.foo(b);
        System.out.println("Back to main thread");
    }

    @Override
    public void run() {
        b.bar(a);
        System.out.println("Back to racing tread");
    }

    public static void main(String[] args){
        new Deadlock();
    }
}

class A {

    synchronized void foo(B b) {

        String name = Thread.currentThread().getName();

        System.out.println(name + " entering to A.foo");

        try{
            Thread.sleep(1_000);
        } catch (Exception e) {
            System.out.println("A is interrupted");
        }

        System.out.println(name + " going to call B.last");
        b.last();

    }

    synchronized void last() {
        System.out.println("inside A.last");
    }

}

class B {

    synchronized void bar(A a) {

        String name = Thread.currentThread().getName();

        System.out.println(name + " entering to B.bar");

        try{
            Thread.sleep(1_000);
        } catch (Exception e) {
            System.out.println("B is interrupted");
        }

        System.out.println(name + " going to call A.foo");
        a.last();

    }

    synchronized void last() {
        System.out.println("inside B.last");
    }

}
