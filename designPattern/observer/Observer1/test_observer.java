import java.util.*;

interface Publisher {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notify(double amt);
}
class PublisherImpl implements Publisher {
    private String acct;
    private double balance;
    private List<Observer> myObservers;
    public PublisherImpl(String anAcct, double aBalance) {
        acct = anAcct;
        balance = aBalance;
        myObservers = new ArrayList();
    }
    public void addObserver(Observer o){
        myObservers.add(o);
    }
    public void removeObserver(Observer o) {
        myObservers.remove(o);
    }
    public void notify(double amt) {
        balance -= amt;
        if(balance < 0) {
            overdrawn();
        }
    }
    private void overdrawn() {
        for (Observer o: myObservers) {
            o.notify(acct, balance);
        }
    }
}
interface Observer {
    void notify(String acct, double amt);
}
class ObserverImpl implements Observer {
    @Override
    public void notify(String acct, double amt) {
        System.out.println("=== 接收到通知：账户："+acct + " 账单："+amt);
    }
}
class Demo {
    public static void main(String[] args) {
        Publisher account = new PublisherImpl("TEST123", 10.00);
        Observer bill = new ObserverImpl();
        account.addObserver(bill);
        account.notify(11.00);
    }
}
