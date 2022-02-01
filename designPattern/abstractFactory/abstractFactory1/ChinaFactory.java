//中国的家具工厂
public class ChinaFactory extends AbsractFactory {
    @Override
    Chair createChair() {
        return new ChinaChair();
    }
    @Override
    Sofa createSofa() {
        return new ChinaSofa();
    }
    @Override
    Table createTable() {
        return new ChinaTable();
    }
}
