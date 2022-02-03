//组件
interface Component {
    void excute();
}

//具体组件
class BaseComponent implements Component {
    @Override
    public void excute() {
        //do something
    }
}

//装饰器
class BaseDecorator implements Component {
    private Component wrapper;
    public BaseDecorator(Component wrapper) {
        this.wrapper = wrapper;
    }
    @Override
    public void excute() {
        wrapper.excute();
    }
}

//具体装饰器A
class DecoratorA extends  BaseDecorator {
    public DecoratorA(Component wrapper) {
        super(wrapper);
    }
    @Override
    public void excute() {
        super.excute();
    }
}

//具体装饰器B
class DecoratorB extends  BaseDecorator {
    public DecoratorB(Component wrapper) {
        super(wrapper);
    }
    @Override
    public void excute() {
        super.excute();
    }
}

class demo {
    public static void main(String[] args) {
        new DecoratorA(new BaseComponent());
    }
}
