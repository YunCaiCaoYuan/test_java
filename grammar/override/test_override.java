package grammar.override;
 
class BaseClass {
    // public void doSomething(String str){
    //     System.out.println("Base impl:"+str);
    // }

    //Change argument from String to Object
    public void doSomething(Object str){
        System.out.println("Base impl:"+str);
    }
}

class ChildClass extends BaseClass{
    @Override
    public void doSomething(String str){
        System.out.println("Child impl:"+str);
    }
 }

class OverrideTest {
    public static void main(String[] args) {
        BaseClass bc = new ChildClass();
        bc.doSomething("override");
    }
}