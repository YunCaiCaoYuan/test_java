interface PackageState {
    /**
     * 定义了6种状态
     * 1 - 已下单
     * 2 - 仓库处理中
     * 3 - 运输中
     * 4 - 派送中
     * 5 - 待取件
     * 6 - 已签收
     * @param ctx
     */
    void updateState(PackageContext ctx);
}

class PackageContext {
    private PackageState currentState;
    private String packageId;
    public PackageContext(PackageState currentState, String packageId) {
        this.currentState = currentState;
        this.packageId = packageId;
        if(currentState == null) {
            this.currentState = Acknowledged.instance();
        }
    }
    public PackageState getCurrentState() {
        return currentState;
    }
    public void setCurrentState(PackageState currentState) {
        this.currentState = currentState;
    }
    public String getPackageId() {
        return packageId;
    }
    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }
    public void update() {
        currentState.updateState(this);
    }
}

//1 - 已下单
class Acknowledged implements PackageState {
    //Singleton
    private static Acknowledged instance = new Acknowledged();
    private Acknowledged() {}
    public static Acknowledged instance() {
        return instance;
    }

    @Override
    public void updateState(PackageContext ctx) {
        System.out.println("=== state start...");
        System.out.println("1 - Package is acknowledged !!");
        ctx.setCurrentState(WarehouseProcessing.instance());
    }
}
class WarehouseProcessing implements PackageState  {
    //Singleton
    private static WarehouseProcessing instance = new WarehouseProcessing();
    private WarehouseProcessing() {}
    public static WarehouseProcessing instance() {
        return instance;
    }

    @Override
    public void updateState(PackageContext ctx) {
        System.out.println("2 - Package is WarehouseProcessing");
        ctx.setCurrentState(InTransition.instance());
    }
}
class InTransition implements PackageState {
    //Singleton
    private static InTransition instance = new InTransition();

    private InTransition() {}

    public static InTransition instance() {
        return instance;
    }

    //Business logic and state transition
    @Override
    public void updateState(PackageContext ctx) {
        System.out.println("3 - Package is in transition !!");
        ctx.setCurrentState(Delivering.instance());
    }
}
class Delivering implements PackageState {
    //Singleton
    private static Delivering instance = new Delivering();
    private Delivering() {
    }
    public static Delivering instance() {
        return instance;
    }
    //Business logic
    @Override
    public void updateState(PackageContext ctx) {
        System.out.println("4 - Package is Delivering !!");
        ctx.setCurrentState(WaitForPickUp.instance());
    }
}
class WaitForPickUp implements PackageState {
    //Singleton
    private static WaitForPickUp instance = new WaitForPickUp();
    private WaitForPickUp() {}
    public static WaitForPickUp instance() {
        return instance;
    }
    //Business logic and state transition
    @Override
    public void updateState(PackageContext ctx) {
        System.out.println("5 - Package is waiting for pick up !!");
        ctx.setCurrentState(Received.instance());
    }
}
class Received implements PackageState {
    //Singleton
    private static Received instance = new Received();
    private Received() {}
    public static Received instance() {
        return instance;
    }
    //Business logic and state transition
    @Override
    public void updateState(PackageContext ctx) {
        System.out.println("6 - Package is Received !!");
        System.out.println("=== state end ");
    }
}

class Client {
    public static void main(String[] args) {
        PackageContext ctx = new PackageContext(null, "Test123");
        ctx.update();
        ctx.update();
        ctx.update();
        ctx.update();
        ctx.update();
        ctx.update();
    }
}
