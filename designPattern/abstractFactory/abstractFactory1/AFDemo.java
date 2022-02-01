public class AFDemo {
    public static void main(String[] args) {
        AbsractFactory cf = new ChinaFactory();
        new Client(cf);
    }
}