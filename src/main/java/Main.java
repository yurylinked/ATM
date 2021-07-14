public class Main {

    public static void main(String[] args) {

        Atm atm = new Atm();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atm.getMoney("Ivan",90000);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                atm.getMoney("Alex", 10000);
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                atm.getMoney("Daria",90000);
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
    }
}