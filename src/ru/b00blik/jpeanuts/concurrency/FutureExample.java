package ru.b00blik.jpeanuts.concurrency;

import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args) {
        Future<Integer> future = new SquareCalculator().calculate(10);

        try {
            while (!future.isDone()) {
                System.out.println("Calculating...");
                Thread.sleep(100L);
            }

            //Integer result = future.get(500L, TimeUnit.MILLISECONDS);
            Integer result = null;

            result = future.get();
            System.out.println("Result: " + result);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}


class SquareCalculator {

    private ExecutorService executorService
            = Executors.newSingleThreadExecutor();

    public Future<Integer> calculate(Integer input) {
        return executorService.submit(() -> {
            Thread.sleep(1_000L);
            return input * input;
        });
    }

}