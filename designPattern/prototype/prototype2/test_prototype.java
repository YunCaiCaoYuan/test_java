import java.util.HashMap;
import java.util.Map;

interface IPrototype extends Cloneable {
    //继承Cloneable接口，重写clone()方法便能使用父类的Object.clone()复制方法
    //也可以直接实现Cloneable接口，效果一样。这里我们为了统一业务接口层级，子类都实现IPrototype接口
    IPrototype clone() throws CloneNotSupportedException;
}
class Movie implements IPrototype {
    //打印并拷贝对象
    @Override
    public Movie clone() throws CloneNotSupportedException {
        System.out.println("Cloning Movie object..");
        return (Movie) super.clone();
    }
    //方便结果展示
    @Override
    public String toString() {
        return "Movie{}";
    }
}
class EBook implements IPrototype {
    @Override
    public EBook clone() throws CloneNotSupportedException {
        System.out.println("Cloning Book object..");
        return (EBook) super.clone();
    }
    @Override
    public String toString() {
        return "EBook{}";
    }
}

enum ModelType {
    MOVIE("movie"),
    EBOOK("eBook");
    private String name;
    ModelType(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
class PrototypeFactory {
    //这里充当注册表的作用，用于存放原始对象，作为对象拷贝的基础
    private static Map<String, IPrototype> prototypes = new HashMap<>();
    //初始化时就将原始对象放入注册表中
    static {
        prototypes.put(ModelType.MOVIE.getName(), new Movie());
        prototypes.put(ModelType.EBOOK.getName(), new EBook());
    }
    //获取对象时，是使用name来进行对象拷贝
    public static IPrototype getInstance(final String s) throws CloneNotSupportedException {
        return prototypes.get(s).clone();
    }
}

class demo {
    public static void main(String[] args) {
        try {
            String moviePrototype  = PrototypeFactory.getInstance(ModelType.MOVIE.getName()).toString();
            System.out.println(moviePrototype);
            String eBookPrototype  = PrototypeFactory.getInstance(ModelType.EBOOK.getName()).toString();
            System.out.println(eBookPrototype);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}