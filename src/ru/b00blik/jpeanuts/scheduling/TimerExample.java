package ru.b00blik.jpeanuts.scheduling;

import java.util.Timer;
import java.util.TimerTask;

public class TimerExample {

    public static void main(String[] args) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Planned timer task");
            }
        };

        Timer timer = new Timer("ExampleTimer");
        timer.schedule(task, 1_000L, 1_000L);

        try {
            Thread.sleep(2_500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
    }

}
