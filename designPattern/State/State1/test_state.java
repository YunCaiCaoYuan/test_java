interface State {
    void handle(Context context);
}
class StateA implements State{
    private static StateA instance = new StateA();
    public StateA() {
    }
    public static StateA instance(){
        return instance;
    }
    @Override
    public void handle(Context context) {
        System.out.println("=== 进入状态A");
        context.setCurrentState(StateB.instance());
    }
}
class StateB implements State {
    private static StateB instance = new StateB();
    public StateB() {
    }
    public static StateB instance(){
        return instance;
    }
    @Override
    public void handle(Context context) {
        System.out.println("=== 进入状态B");
        context.setCurrentState(this);
    }
}
class Context {
    private State currentState;
    public Context(State currentState) {
        this.currentState = currentState;
        if (null == currentState) {
            this.currentState  = StateA.instance();
        }
    }
    public State getCurrentState() {
        return currentState;
    }
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
    public void request(){
        currentState.handle(this);
    }
}

class Demo {
    public static void main(String[] args) {
        new Context(new StateA()).request();
        new Context(new StateB()).request();
    }
}
