package itstep.learning.Async;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultitreadDemo {
    private String pandigital = ""; // Кінцеве число
    private boolean[] isPandigital = new boolean[10]; // Масив для перевірки на унікальність
    private final Object locker = new Object(); // Об'єкт для синхронізації
    private int counter = 10; // Лічильник

    private final ExecutorService pool = Executors.newFixedThreadPool(3); // Пул потоків

    private class PandigitalRunnable implements Runnable {
        @Override
        public void run() {
            int localCounter; // Локальний лічильник
            int numToAdd = numPlus(); // Число для додавання

            synchronized (locker) {
                pandigital += numToAdd;
                localCounter = --counter;
            }
            System.out.println("Current number: " + pandigital); // Вивід поточного числа

            if (localCounter == 0) {
                System.out.println("===> Final pandigital number: " + pandigital); // Вивід кінцевого числа
                pool.shutdown(); // Завершення роботи пулу потоків
            }
        }
    }

    public void runMultithreaded() {
        for (int i = 0; i < 10; i++) {
            pool.submit(new PandigitalRunnable()); // Використовуємо пул потоків для запуску задач
        }

        try {
            pool.awaitTermination(1, TimeUnit.MINUTES); // Очікуємо завершення всіх задач
        } catch (InterruptedException e) {
            System.err.println("Task interrupted: " + e.getMessage());
        }
    }

    private int numPlus() {
        Random random = new Random();

        try {
            TimeUnit.MILLISECONDS.sleep(300); // Затримка
        } catch (InterruptedException ignore) {
        }

        while (true) {
            int num = random.nextInt(10);

            synchronized (locker) {
                if (!isPandigital[num]) {
                    isPandigital[num] = true;
                    return num;
                }
            }
        }
    }
}

