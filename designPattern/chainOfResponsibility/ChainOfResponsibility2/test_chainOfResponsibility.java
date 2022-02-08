class Chain {
    Excutor chain;
    public Chain(){
        buildChain();
    }
    private void buildChain(){
        Excutor e1 = new NegativeExcutor();
        Excutor e2 = new ZeroExcutor();
        Excutor e3 = new PositiveExcutor();
        e1.setNext(e2);
        e2.setNext(e3);
        this.chain = e1;
    }
    public void process(Integer num) {
        chain.handle(num);
    }
}
interface Excutor {
    void setNext(Excutor excutor);
    void handle(Integer num);
}
class NegativeExcutor implements Excutor {
    private Excutor next;
    @Override
    public void setNext(Excutor excutor) {
        this.next = excutor;
    }
    @Override
    public void handle(Integer num) {
        if (null!= num && num < 0) {
            System.out.println("NegativeExcutor获取数字："+num+" ,处理完成！");
        } else {
            if (null != next) {
                System.out.println("===经过NegativeExcutor");
                next.handle(num);
            } else {
                System.out.println("处理中止！-NegativeExcutor");
            }
        }
    }
}
class PositiveExcutor implements Excutor{
    private Excutor next;
    @Override
    public void setNext(Excutor excutor) {
        this.next = excutor;
    }
    @Override
    public void handle(Integer num) {
        if (null!= num && num > 0) {
            System.out.println("PositiveExcutor获取数字："+num+" ,处理完成！");
        } else {
            if (null != next) {
                System.out.println("===经过PositiveExcutor");
                next.handle(num);
            } else {
                System.out.println("处理中止！-PositiveExcutor");
            }
        }
    }
}
class ZeroExcutor implements Excutor{
    private Excutor next;
    @Override
    public void setNext(Excutor excutor) {
        this.next = excutor;
    }
    @Override
    public void handle(Integer num) {
        if (null!= num && num == 0) {
            System.out.println("ZeroExcutor获取数字："+num+" ,处理完成！");
        } else {
            if (null != next) {
                System.out.println("===经过ZeroExcutor");
                next.handle(num);
            } else {
                System.out.println("处理中止！- ZeroExcutor");
            }
        }
    }
}

class Client_ChainOfResponsibility {
    public static void main(String[] args) {
        Chain chain = new Chain();
        chain.process(99);
        System.out.println("------");
        chain.process(-11);
        System.out.println("------");
        chain.process(0);
        System.out.println("------");
        chain.process(null);
    }
}