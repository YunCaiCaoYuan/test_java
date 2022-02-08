interface Handler {
    void setNext(Handler handler);
    void handle(Request request);
}
class Request {
    private String data;
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
}
class HandlerA implements Handler{
    private Handler next;
    public HandlerA() {
    }
    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }
    @Override
    public void handle(Request request) {
        System.out.println("HandlerA 执行 代码逻辑，处理："+request.getData());
        request.setData(request.getData().replace("AB",""));
        if (null != next) {
            next.handle(request);
        } else {
            System.out.println("执行中止！");
        }
    }
}
class HandlerB implements Handler {
    private Handler next;
    public HandlerB() {
    }
    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }
    @Override
    public void handle(Request request) {
        System.out.println("HandlerB 执行 代码逻辑，处理："+request.getData());
        request.setData(request.getData().replace("CD",""));
        if (null != next) {
            next.handle(request);
        } else {
            System.out.println("执行中止！");
        }
    }
}
class HandlerC implements Handler{
    private Handler next;
    public HandlerC() {
    }
    @Override
    public void setNext(Handler handler) {
        this.next = handler;
    }
    @Override
    public void handle(Request request) {
        System.out.println("HandlerC 执行 代码逻辑，处理："+request.getData());
        if (null != next) {
            next.handle(request);
        } else {
            System.out.println("执行中止！");
        }
    }
}
class Demo {
    public static void main(String[] args) {
        Handler h1 = new HandlerA();
        Handler h2 = new HandlerB();
        Handler h3 = new HandlerC();
        h1.setNext(h2);
        h2.setNext(h3);
        Request request = new Request();
        request.setData("请求数据ABCDE");
        h1.handle(request);
    }
}
