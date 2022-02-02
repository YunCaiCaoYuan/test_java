abstract class AbstractBehavior {
    public abstract void operation1();
    public abstract void operation2();
}

abstract class AbstractEntity {
    //行为对象
    protected AbstractBehavior myBehavior;
    //实体与行为的关联
    public AbstractEntity(AbstractBehavior aBehavior) {
        myBehavior = aBehavior;
    }
    //子类需要实现的方法
    public abstract void request();
}

class DetailEntityA extends AbstractEntity {
    public DetailEntityA(AbstractBehavior aBehavior) {
        super(aBehavior);
    }

    @Override
    public void request() {
        super.myBehavior.operation1();
    }
}

class DetailEntityB extends AbstractEntity {
    public DetailEntityB(AbstractBehavior aBehavior) {
        super(aBehavior);
    }
    @Override
    public void request() {
        super.myBehavior.operation2();
    }
}

class DetailBehaviorA extends AbstractBehavior{
    @Override
    public void operation1() {
        System.out.println("op-1 from DetailBehaviorA");
    }

    @Override
    public void operation2() {
        System.out.println("op-2 from DetailBehaviorA");
    }
}

class DetailBehaviorB extends AbstractBehavior {
    @Override
    public void operation1() {
        System.out.println("op-1 from DetailBehaviorB");
    }

    @Override
    public void operation2() {
        System.out.println("op-2 from DetailBehaviorB");
    }
}
class demo {
    public static void main(String[] args) {
        new DetailEntityA(new DetailBehaviorA()).request();
        new DetailEntityB(new DetailBehaviorB()).request();
    }
}