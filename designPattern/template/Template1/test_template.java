// 模版方法
// 形式：声明抽象类，抽象类声明方法，继承类实现这些方法

abstract class AbstractClassTemplate {
    void step1(String key){
        //dosomthing
        System.out.println("=== 在模板类里 执行步骤 1");
        if (step2(key)) {
            step3();
        } else {
            step4();
        }
        step5();
    }
    boolean step2(String key){
        System.out.println("=== 在模板类里 执行步骤 2");
        if ("x".equals(key)) {
            return true;
        }
        return false;
    }
    abstract void step3();
    abstract void step4();
    void step5() {
        System.out.println("=== 在模板类里 执行步骤 5");
    }
    void run(String key){
        step1(key);
    }
}
class ConcreteClassA extends AbstractClassTemplate {
    @Override
    void step3() {
        System.out.println("===在子类 A 中 执行：步骤3");
    }
    @Override
    void step4() {
        System.out.println("===在子类 A 中 执行：步骤4");
    }
}
class ConcreteClassB extends AbstractClassTemplate {
    @Override
    void step3() {
        System.out.println("===在子类 B 中 执行：步骤3");
    }
    @Override
    void step4() {
        System.out.println("===在子类 B 中 执行：步骤4");
    }
}
class Demo {
    public static void main(String[] args) {
        AbstractClassTemplate concreteClassA = new ConcreteClassA();
        concreteClassA.run("");
        System.out.println("===========");
        AbstractClassTemplate concreteClassB = new ConcreteClassB();
        concreteClassB.run("x");
    }
}