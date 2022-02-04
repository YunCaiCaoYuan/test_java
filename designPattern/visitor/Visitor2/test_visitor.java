interface Router {
    void sendData(char[] data);
    void accept(RouterVisitor v);
}
class DLinkRouter implements Router{
    @Override
    public void sendData(char[] data) {

    }

    @Override
    public void accept(RouterVisitor v) {
        v.visit(this);
    }
}
class TPLinkRouter implements Router {
    @Override
    public void sendData(char[] data) {
    }
    @Override
    public void accept(RouterVisitor v) {
        v.visit(this);
    }
}
interface RouterVisitor {
    void visit(DLinkRouter router);
    void visit(TPLinkRouter router);
}
class LinuxRouterVisitor implements RouterVisitor{
    @Override
    public void visit(DLinkRouter router) {
        System.out.println("=== DLinkRouter Linux visit success!");
    }
    @Override
    public void visit(TPLinkRouter router) {
        System.out.println("=== TPLinkRouter Linux visit success!");
    }

}
class WindowsRouterVisitor implements RouterVisitor{
    @Override
    public void visit(DLinkRouter router) {
        System.out.println("=== DLinkRouter Windows visit success!");
    }
    @Override
    public void visit(TPLinkRouter router) {
        System.out.println("=== TPLinkRouter Windows visit success!");
    }
}

class Client {
    public static void main(String[] args) {
        LinuxRouterVisitor linuxRouterVisitor = new LinuxRouterVisitor();
        WindowsRouterVisitor windowsRouterVisitor = new WindowsRouterVisitor();
        DLinkRouter dLinkRouter = new DLinkRouter();
        dLinkRouter.accept(linuxRouterVisitor);
        dLinkRouter.accept(windowsRouterVisitor);
        TPLinkRouter tpLinkRouter = new TPLinkRouter();
        tpLinkRouter.accept(linuxRouterVisitor);
        tpLinkRouter.accept(windowsRouterVisitor);
    }
}