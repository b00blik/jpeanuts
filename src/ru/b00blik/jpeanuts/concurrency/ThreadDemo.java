package ru.b00blik.jpeanuts.concurrency;

public class ThreadDemo {

    public static void main(String[] args){
        new NewThread();

        try{
            for (int i=0; i<5; i++) {
                System.out.println("Parent thread: " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Parent thread interrupted");
        }
        System.out.println("Parent thread end");
    }

}

class NewThread implements Runnable{

    Thread t;

    NewThread(){
        t = new Thread(this, "Demo thread");
        System.out.println("Child thread created: " + t);
        t.start();
    }

    @Override
    public void run() {
        try{
            for (int i=0; i<5; i++) {
                System.out.println("Child thread: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Child thread interrupted");
        }
        System.out.println("Child thread end");
    }
}