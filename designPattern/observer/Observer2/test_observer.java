import java.util.*;

interface MessageObserver {
    void update(Message m);
}
interface Subject {
    void attach(MessageObserver o);  //增加观察者
    void detach(MessageObserver o);  //删除观察者
    void notifyUpdate(Message m);    //更新通知
}
class Message {
    final String content;
    public Message (String m) {
        this.content = m;
    }
    public String getContent() {
        return content;
    }
}
class MessagePublisher implements Subject {
    private List<MessageObserver> observers = new ArrayList<>();
    @Override
    public void attach(MessageObserver o) {
        observers.add(o);
    }
    @Override
    public void detach(MessageObserver o) {
        observers.remove(o);
    }
    @Override
    public void notifyUpdate(Message m) {
        observers.forEach(x->x.update(m));
    }
}
class MessageSubscriber1 implements MessageObserver {
    @Override
    public void update(Message m) {
        System.out.println("MessageSubscriber1 :: " + m.getContent());
    }
}
class MessageSubscriber2 implements MessageObserver {
    @Override
    public void update(Message m) {
        System.out.println("MessageSubscriber2 :: " + m.getContent());
    }
}
class MessageSubscriber3 implements MessageObserver {
    @Override
    public void update(Message m) {
        System.out.println("MessageSubscriber3 :: " + m.getContent());
    }
}

class Client {
    public static void main(String[] args) {
        MessageObserver s1 = new MessageSubscriber1();
        MessageObserver s2 = new MessageSubscriber2();
        MessageObserver s3 = new MessageSubscriber3();
        Subject p = new MessagePublisher();
        p.attach(s1);//
        p.attach(s2);
        p.notifyUpdate(new Message("First Message"));   //s1和s2会收到消息通知
        p.detach(s1);
        p.attach(s3);
        p.notifyUpdate(new Message("Second Message")); //s2和s3会收到消息通知
    }
}