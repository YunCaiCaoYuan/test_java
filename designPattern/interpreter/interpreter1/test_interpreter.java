//抽象表达式类
interface AbstractExpression {
    boolean interpreter(Context context);
}

//上下文信息类
class Context {
    private String data;
    public Context(String data) {
        this.data = data;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
}

//终结符表达式类
class TerminalExpression implements AbstractExpression {
    private String data;
    public TerminalExpression(String data) {
        this.data = data;
    }
    @Override
    public boolean interpreter(Context context) {
        if(context.getData().contains(data)) {
            return true;
        } else {
            return false;
        }
    }
}
//非终结符表达式类
class NonterminalExpression implements AbstractExpression {
    AbstractExpression expr1;
    AbstractExpression expr2;
    public NonterminalExpression(AbstractExpression expr1, AbstractExpression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }
    @Override
    public boolean interpreter(Context context) {
        return expr1.interpreter(context) && expr2.interpreter(context);
    }
}
//单元测试类
class Demo {
    public static void main(String[] args) {
        AbstractExpression person1 = new TerminalExpression("mick");
        AbstractExpression person2 = new TerminalExpression("mia");
        AbstractExpression isSingle = new NonterminalExpression(person1, person2);
        Context context1 = new Context("mick,mia");
        Context context2 = new Context("mia,mock");
        Context context3 = new Context("spike");
        System.out.println(isSingle.interpreter(context1));
        System.out.println(isSingle.interpreter(context2));
        System.out.println(isSingle.interpreter(context3));
    }
}