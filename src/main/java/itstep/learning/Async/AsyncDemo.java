package itstep.learning.Async;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AsyncDemo{
    private String pandigital = ""; // Кінцеве число
    private boolean[] isPandigital = new boolean[10]; // Масив для перевірки на унікальність
    private final Object locker = new Object(); // Об'єкт для синхронізації
    private int counter = 10; // Лічильник

    private class PandigitalRunnable implements Runnable{
        @Override
        public void run() {
            int localCounter; // Локальний лічильник
            int numToAdd = numPlus(); // Число для додавання

            synchronized (locker){
                pandigital += numToAdd;
                localCounter = --counter;
            }
            System.out.println("Current number: " + pandigital); // Вивід поточного числа

            if (localCounter == 0){
                System.out.println("===> Final pandigital number: " + pandigital); // Вивід кінцевого числа
            }
        }
    }

    public void threadDemo(){
        for (int i = 0; i < 10; i++){
            Thread thread = new Thread(new PandigitalRunnable());
            thread.start();
        }
    }

    private int numPlus() {
        Random random = new Random();

        try{
            TimeUnit.MILLISECONDS.sleep(300); // Затримка
        }
        catch (InterruptedException ignore){
        }

        while (true)
        {
            int num = random.nextInt(10);

            //System.out.println("=========================="); // Вивід для перевірки
            //System.out.println(Arrays.toString(isPandigital)); // Вивід для перевірки
            //System.out.println("Suggested num: " + num); // Вивід для перевірки

            if(!isPandigital[num])
            {
                isPandigital[num] = true;

                //System.out.println("OK"); // Вивід для перевірки
                //System.out.println("=========================="); // Вивід для перевірки

                return num;
            }
        }
    }
}
