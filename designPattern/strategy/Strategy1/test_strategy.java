class Context {
    public void request(Strategy s) {
        s.operation();
    }
}
interface Strategy {
    void operation();
}
class StrategyA implements Strategy {
    @Override
    public void operation() {
        System.out.println("=== 执行策略 A ......");
    }
}
class StrategyB implements Strategy {
    @Override
    public void operation() {
        System.out.println("=== 执行策略 B ......");
    }
}
class demo {
    public static void main(String[] args) {
        new Context().request(new StrategyA());
    }
}