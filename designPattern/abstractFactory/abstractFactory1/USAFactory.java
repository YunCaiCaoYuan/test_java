//美国的家具工厂
public class USAFactory extends AbsractFactory{
    @Override
    Chair createChair() {
        return new USAChair();
    }
    @Override
    Sofa createSofa() {
        return new USASofa();
    }
    @Override
    Table createTable() {
        return new USATable();
    }
}
