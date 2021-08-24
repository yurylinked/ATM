import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class ExecutorService {

    private static int Sum() {
        int million = 0;
        for (int i = 0; i <= 1000000; i++) {
            million += i;
        }
        System.out.println(million);
        return million;
    }

    private static void divisionby7() {
        long sum = 0l;
        for (int i = 0; i <= 1_000_000; i++) {
            if (i % 7 == 0) {
                sum += i;
            }
        }
        System.out.println(sum);

    }

    private static void collection1000() {
        List<Integer> arrayList = new ArrayList<>();
        int numberOfEvenNumbers = 0;
        for (int i = 0; i < 1000; i++) {
            arrayList.add((int) (Math.random() * 1000));//
            // заполнил рандомными до 1000
            // проверил работает, видимо проблема будет в след цикле

        }

        for (int i = 0; i < arrayList.size(); i++) {// ??? в этом цикле проблема так как
            // колво четных чисел в коллекции не может быть 500(результат всегда 500..)
            if (arrayList.get(i) % 2 == 0) {
                numberOfEvenNumbers++;
            }
        }
        System.out.println("Колво четных чисел в листе = " + numberOfEvenNumbers);
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);//счетчик на 3 потока
        java.util.concurrent.ExecutorService executorService = Executors.newFixedThreadPool(3);
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                Sum();
                countDownLatch.countDown();
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                divisionby7();
                countDownLatch.countDown();
            }
        };
        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                collection1000();
                countDownLatch.countDown();
            }
        };
        long beforTime = System.currentTimeMillis();//старт замера перед запуском 3 потоков
        executorService.execute(runnable1);
        executorService.execute(runnable2);
        executorService.execute(runnable3);
        executorService.shutdown();//прерываем ожидание executora
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long afterTime = System.currentTimeMillis();
        long time = afterTime - beforTime;
        System.out.println("Currency time" + " " + time);
    }
}
