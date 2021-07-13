public class Concurrency {
    private static final int SIZE=10000000;
    private static final int HALFSIZE=SIZE/2;

    public static void withoutConcurrency () {
        Float[] floats1 = new Float[SIZE];
        for (int i = 0; i < floats1.length; i++) {
            floats1[i] = 1f;
        }
        long beforTime = System.currentTimeMillis();
        for (int i = 0; i < floats1.length; i++) {
            floats1[i] = (float) (floats1[i] * Math.sin(0.2f + i/ 5) * Math.cos(0.2f + i/ 5) * Math.cos(0.4f + i/ 2));;
        }

        long afterTime = System.currentTimeMillis();
        long time=afterTime - beforTime;
        System.out.println(time);
    }


    public static void withConcurrency () {
        Float[] floats2 = new Float[SIZE];
        for (int i = 0; i < floats2.length; i++) {
            floats2[i] = 1f;
        }

        Float[] floats3 = new Float[HALFSIZE];
        Float[] floats4 = new Float[HALFSIZE];
        long beforTime = System.currentTimeMillis();

        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                System.arraycopy(floats2,0,floats3,0,SIZE/2);
                for (int i = 0; i < floats3.length; i++) {
                    floats3[i] = (float) (floats2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
        }
        });

        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                System.arraycopy(floats2,floats2.length/2,floats4,0,HALFSIZE);
                for (int i = 0; i < floats4.length; i++) {
                    floats4[i] = (float) (floats4[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
thread1.start();
thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(floats3,0,floats2,0,floats3.length);
        System.arraycopy(floats4,0,floats2,HALFSIZE,floats4.length);
        long afterTime = System.currentTimeMillis();
        long time = afterTime - beforTime;
        System.out.println("Currency time"+ " " +time);


    }

    public static void main(String[] args) {
        withoutConcurrency();
        withConcurrency();
    }
}