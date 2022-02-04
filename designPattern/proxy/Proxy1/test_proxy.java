interface RealObject {
    void doSomething();
}
class RealObjectImpl implements RealObject {
    @Override
    public void doSomething() {
        System.out.println("=== 真实对象输出打印");
    }
}
class Proxy extends RealObjectImpl {
    @Override
    public void doSomething() {
        //这里做一些代理操作或额外的操作
        System.out.println("== 通过代理类来执行真实对象");
        super.doSomething();
    }
}
//单元测试
class Demo {
    public static void main(String[] args) {
        RealObject realObjectProxy = new Proxy();
        realObjectProxy.doSomething();
    }
}