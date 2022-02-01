public interface PrototypeInterface extends Cloneable {
    PrototypeInterface clone() throws CloneNotSupportedException;
}
class ProtypeA implements PrototypeInterface {
    @Override
    public ProtypeA clone() throws CloneNotSupportedException {
        System.out.println("Cloning new object: A");
        return (ProtypeA) super.clone();
    }
}
class ProtypeB implements PrototypeInterface {
    @Override
    public ProtypeB clone() throws CloneNotSupportedException {
        System.out.println("Cloning new object: B");
        return (ProtypeB) super.clone();
    }
}
//ProtypeA以自己为原型通过拷贝创建一个新的对象newInstanceA
class Demo {
    public static void main(String[] args) throws CloneNotSupportedException {
        ProtypeA source = new ProtypeA();
        System.out.println(source);

        ProtypeA newInstanceA = source.clone();
        System.out.println(newInstanceA);
    }
}

