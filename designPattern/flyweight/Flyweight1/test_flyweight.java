import java.util.*;

//享元类
interface Flyweight {
    void operation(int state);
}
//享元工厂类
class FlyweighFactory {
    // 定义一个池容器
    public Map<String,Flyweight> pool = new HashMap<>();
    public FlyweighFactory() {
        pool.put("A", new ConcreteFlyweight("A"));//将对应的内部状态添加进去
        pool.put("B", new ConcreteFlyweight("B"));
        pool.put("C", new ConcreteFlyweight("C"));
    }
    //根据内部状态来查找值
    public Flyweight getFlyweight(String key) {
        if (pool.containsKey(key)) {
            System.out.println("===享元池中有，直接复用，key："+key);
            return pool.get(key);
        } else {
            System.out.println("===享元池中没有，重新创建并复用，key："+key);
            Flyweight flyweightNew = new ConcreteFlyweight(key);
            pool.put(key,flyweightNew);
            return flyweightNew;
        }
    }
}
//共享的具体享元类
class ConcreteFlyweight implements Flyweight {
    private String uniqueKey;
    public ConcreteFlyweight(String key) {
        this.uniqueKey = key;
    }
    @Override
    public void operation(int state) {
        System.out.printf("=== 享元内部状态：%s,外部状态：%s%n",uniqueKey,state);
    }
}

//非共享的具体享元类
class UnsharedConcreteFlyweight implements Flyweight {
    private String uniqueKey;
    public UnsharedConcreteFlyweight(String key) {
        this.uniqueKey = key;
    }
    @Override
    public void operation(int state) {
        System.out.println("=== 使用不共享的对象，内部状态："+uniqueKey+"，外部状态："+state);
    }
}
/*
输出结果：
        ====享元池中有，直接复用，key：A
        === 享元内部状态：A,外部状态：9
        ====享元池中有，直接复用，key：B
        === 享元内部状态：B,外部状态：8
        ====享元池中没有，重新创建并复用，key：D
        === 享元内部状态：D,外部状态：7
        ====创建不共享的对象，key：uX
        === 使用不共享的对象，内部状态：uX，外部状态：6
 */

class demo {
    public static void main(String[] args) {
        FlyweighFactory ff = new FlyweighFactory();
        ff.getFlyweight("A");
        ConcreteFlyweight cf = new ConcreteFlyweight("A");
        cf.operation(9);
    }
}
