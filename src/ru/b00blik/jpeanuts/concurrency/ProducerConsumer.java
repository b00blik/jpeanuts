package ru.b00blik.jpeanuts.concurrency;

public class ProducerConsumer {

    public static void main(String[] args) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);
        System.out.println("ProducerConsumer started");
    }

}


class Consumer implements Runnable {

    Q q;

    public Consumer(Q q) {
        this.q = q;
        new Thread(this, "Receiver").start();
    }

    @Override
    public void run() {
        while (true) {
            //System.out.println("Consumer calling");
            q.get();
        }
    }
}

class Producer implements Runnable {

    Q q;

    public Producer(Q q) {
        this.q = q;
        new Thread(this, "Sender").start();
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            //System.out.println("Producer calling");
            q.put(i++);
        }
    }
}

class Q {
    int n;

    boolean valueSet = false;

    synchronized int get() {
        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Received: " + n);

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        valueSet = false;
        notify();
        return n;
    }

    synchronized void put(int n) {
        while (valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        this.n = n;
        valueSet = true;

        System.out.println("Sent: " + n);

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        notify();

    }

}