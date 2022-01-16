package ru.b00blik.jpeanuts.concurrency;

public class ExtendThread {

    public static void main(String[] args){
        new NewThread2();

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

class NewThread2 extends Thread {

    NewThread2(){
        super("Demo thread");
        System.out.println("Child thread: " + this);
        start();
    }

    public void run(){
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