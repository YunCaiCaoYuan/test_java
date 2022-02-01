public class Client {
    private Chair myChair;
    private Sofa mySofa;
    private Table myTable;

    //通过抽象工厂来生产家具
    public Client(AbsractFactory af) {
        myChair = af.createChair();
        myChair.print();
        mySofa = af.createSofa();
        mySofa.print();
        myTable = af.createTable();
        myTable.print();
    }
}
