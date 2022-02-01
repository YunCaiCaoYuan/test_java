//抽象产品
interface IProduct {
    void apply();
}
//核心工厂类
class ProductFactory {
    public static IProduct getProduct(String name){
        if ("a".equals(name)) {
            return new Product_A_Impl();
        }
        return new Product_B_Impl();
    }
}
//具体产品实现A
class Product_A_Impl implements IProduct{
    @Override
    public void apply() {
        System.out.println("use A product now");
    }
}
//具体产品实现B
class Product_B_Impl implements IProduct{
    @Override
    public void apply() {
        System.out.println("use B product now");
    }
}
//client使用者
class Demo {
    public static void main(String[] args) {
        IProduct iProduct = ProductFactory.getProduct("");
        iProduct.apply();
        IProduct iProducta = ProductFactory.getProduct("a");
        iProducta.apply();
    }
}

