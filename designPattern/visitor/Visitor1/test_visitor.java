import java.util.*;

interface Visitor {
    void visitA(ElementA elementA);
    void visitB(ElementB elementB);
    //...
    //void visitN(ElementN elementN);
}
class VisitorBehavior implements Visitor {
    @Override
    public void visitA(ElementA elementA) {
        int x = elementA.getAState();
        x++;
        System.out.println("=== 当前A的state为："+x);
        elementA.setAState(x);
    }
    @Override
    public void visitB(ElementB elementB) {
        double x = elementB.getBState();
        x++;
        System.out.println("=== 当前B的state为："+x);
        elementB.setBState(x);
    }
}
interface Element {
    void accept(Visitor v);
}
class ElementA implements Element {
    private int stateForA = 0;
    public void accept(Visitor v) {
        System.out.println("=== 开始访问元素 A......");
        v.visitA(this);
    }
    public int getAState(){
        return stateForA;
    }
    public void setAState(int value){
        stateForA = value;
    }
}
class ElementB implements Element {
    private double stateForB = 0;
    public void accept(Visitor v) {
        System.out.println("=== 开始访问元素 B......");
        v.visitB(this);
    }
    public double getBState(){
        return stateForB;
    }
    public void setBState(double value){
        stateForB = value;
    }
}
// 单元测试
class Demo {
    public static void main(String[] args) {
        List<Element> elementList = new ArrayList<>();
        ElementA elementA = new ElementA();
        elementA.setAState(11);
        ElementB elementB = new ElementB();
        elementB.setBState(12);
        elementList.add(elementA);
        elementList.add(elementB);
        for (Element element :elementList) {
            element.accept(new VisitorBehavior());
        }
    }
}