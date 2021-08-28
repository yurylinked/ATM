
public class MainQueue {
    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final Object MONITOR = new Object();
    private static String nextletter = A;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MONITOR) {
                    for (int i = 0; i < 5; i++) {
                        if (!nextletter.equals(A)) {
                            try {
                                MONITOR.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.print(A);
                            nextletter = B;
                            MONITOR.notifyAll();
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MONITOR) {
                    for (int i = 0; i < 5; i++) {
                        if (!nextletter.equals(B)) {
                            try {
                                MONITOR.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.print(B);
                            nextletter = C;
                            MONITOR.notifyAll();
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MONITOR) {
                    for (int i = 0; i < 5; i++) {
                        if (!nextletter.equals(C)) {
                            try {
                                MONITOR.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.print(C);
                            nextletter = A;
                            MONITOR.notifyAll();
                        }
                    }
                }
            }
        }).start();
    }
}