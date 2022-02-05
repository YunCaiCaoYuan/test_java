interface PromotionStrategy {
    /**
     * 返回1 代表 可以参加 满减活动
     * 返回2 代表 可以参加 N折优惠活动
     * 返回3 代表 可以参加 M元秒杀活动
     */
    int recommand(String skuId);
}

class FullReduceStrategy implements PromotionStrategy {

    @Override
    public int recommand(String skuId) {
        System.out.println("=== 执行 满减活动");
        //推荐算法和逻辑写这里
        return 1;
    }
}
class NPriceDiscountStrategy implements PromotionStrategy {
    @Override
    public int recommand(String skuId) {
        System.out.println("=== 执行 N 折扣优惠活动");
        //推荐算法和逻辑写这里
        return 2;
    }
}
class MSpikeStrategy implements PromotionStrategy {
    @Override
    public int recommand(String skuId) {
        System.out.println("=== 执行 M 元秒杀活动");
        //推荐算法和逻辑写这里
        return 3;
    }
}

class Promotional {
    private final PromotionStrategy strategy;
    public Promotional(PromotionStrategy strategy) {
        this.strategy = strategy;
    }
    public void recommand(String skuId) {
        strategy.recommand(skuId);
    }
}

class Client {
    public static void main(String[] args) {
        Promotional fullReducePromotional = new Promotional(new FullReduceStrategy());
        fullReducePromotional.recommand("1122334455");
        Promotional nPriceDiscountPromotional = new Promotional(new NPriceDiscountStrategy());
        nPriceDiscountPromotional.recommand("6677889900");
        Promotional mSpikePromotional = new Promotional(new MSpikeStrategy());
        mSpikePromotional.recommand("11335577");
    }
}