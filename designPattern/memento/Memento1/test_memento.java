class Originator {
    private String state = "原始对象";  //打印当前状态
    private String id;
    private String name;
    private String phone;
    public Originator() {
    }
    //省略get、set方法


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Memento create() {
        return new Memento(id, name, phone);
    }
    public void restore(Memento m) {
        this.state = m.getState();
        this.id = m.getId();
        this.name = m.getName();
        this.phone = m.getPhone();
    }
    @Override
    public String toString() {
        return "Originator{" +
                "state='" + state + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

class Memento {
    private String state = "从备份对象恢复为原始对象";  //打印当前状态
    private String id;
    private String name;
    private String phone;
    public Memento(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    //省略get、set方法


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getState() {
        return state;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Memento{" +
                "state='" + state + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

class Demo {
    public static void main(String[] args) {
        Originator originator = new Originator();
        originator.setId("1");
        originator.setName("mickjoust");
        originator.setPhone("1234567890");
        System.out.println(originator);

        Memento memento = originator.create();
        originator.setName("修改");
        System.out.println(originator);

        originator.restore(memento);
        System.out.println(originator);
    }
}