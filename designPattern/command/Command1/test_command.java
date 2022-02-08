import java.util.ArrayList;
import java.util.List;

interface Command {
    void excute();
}
class Command1 implements Command {
    private final Receiver receiver;
    public Command1(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void excute() {
        receiver.operationA();
    }
}
class Command2 implements Command {
    private final Receiver receiver;
    public Command2(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void excute() {
        receiver.operationB();
    }

}
class Command3 implements Command {
    private final Receiver receiver;
    public Command3(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void excute() {
        receiver.operationC();
    }

}
interface Receiver {
    void operationA();
    void operationB();
    void operationC();
}
class Receiver1 implements Receiver {

    @Override
    public void operationA() {
        System.out.println("操作 A");
    }
    @Override
    public void operationB() {
        System.out.println("操作 B");
    }
    @Override
    public void operationC() {
        System.out.println("操作 C");
    }
}
class Invoker {
    private List<Command> commands;

    public Invoker(){
        commands = new ArrayList<>();
    }

    public void setCommand(Command command) {
        commands.add(command);
    }
    public void run() {
        for (Command cmd: commands) {
            cmd.excute();
        }
    }
}
class Demo {
    public static void main(String[] args) {
        Receiver receiver1 = new Receiver1();
        Invoker invoker = new Invoker();
        invoker.setCommand(new Command1(receiver1));
        invoker.setCommand(new Command2(receiver1));
        invoker.setCommand(new Command3(receiver1));
        invoker.run();
    }
}