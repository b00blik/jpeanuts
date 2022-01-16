package ru.b00blik.jpeanuts.concurrency;

public class MultiThreadDemo {

    public static void main(String[] args) {
        new NamedThread("one");
        new NamedThread("two");
        new NamedThread("three");

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread end");
    }

}

class NamedThread implements Runnable {

    String name;
    Thread t;

    NamedThread(String s) {
        this.name = s;
        this.t = new Thread(this, name);
        System.out.println("New thread: " + t);
        t.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(t + " : " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println(t + " thread interrupted");
        }
        System.out.println(t + " thread end");
    }
}
