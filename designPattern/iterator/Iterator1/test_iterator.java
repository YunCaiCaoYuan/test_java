interface Iterator{
    Object next();
    boolean hasNext();
}
class ConcreteIterator implements Iterator {
    private final Object[] objects;
    private int position;
    public ConcreteIterator(Object[] objects) {
        this.objects = objects;
    }
    @Override
    public Object next() {
        return objects[position++];
    }
    @Override
    public boolean hasNext() {
        if(position >= objects.length) {
            return false;
        }
        return true;
    }
}
interface Aggregate {
    Iterator createIterator();
}
class ConcreteAggregate implements Aggregate{
    private final Object[] objects;
    public ConcreteAggregate(Object[] objects) {
        this.objects = objects;
    }
    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(objects);
    }
}
class Demo {
    public static void main(String[] args) {
        Object[] objects = new Object[2];
        objects[0] = new Object();
        objects[1] = new Object();
        Aggregate aggregate = new ConcreteAggregate(objects);
        Iterator iterator = aggregate.createIterator();
        while(iterator.hasNext()) {
            Object currentObject = iterator.next();
            System.out.println(currentObject.toString());
        }
    }
}
