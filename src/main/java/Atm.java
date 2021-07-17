public class Atm {
    public static int getAvailableInATM() {
        return availableInATM;
    }

    public static void setAvailableInATM(int availableInATM) {
        Atm.availableInATM = availableInATM;
    }

    private static int availableInATM=100000;

private Object monitor=new Object();
   public  int getMoney(String name, int request) {
       System.out.println(name + " went to the ATM");
       Thread timer = new Thread(new Runnable() {//может стоило просто усыпть главный поток?
           @Override
           public void run() {
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

           }
       });
       timer.start();
       synchronized (monitor) {
           if (request > availableInATM) {
               System.out.println("Not enough money for" + " " + name);
               return getAvailableInATM();
           } else if (request <= availableInATM) {
               int leftinAtm = getAvailableInATM() - request;
               setAvailableInATM(leftinAtm);
               System.out.println(name + " withdrew " + request + " money");
               System.out.println("left money in the ATM " + leftinAtm);
           }
           return getAvailableInATM();
       }
   }
}
