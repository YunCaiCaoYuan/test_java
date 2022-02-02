import java.util.ArrayList;
import java.util.List;

abstract class AbstractNode {
    public abstract boolean isRoot();
    public abstract int getId();
    public abstract int getParentId();
    public abstract void setId(int id);
    public abstract void setParentId(int parentId);
    public abstract void add(AbstractNode abstractNode);
    public abstract void remove(AbstractNode g);
    public abstract AbstractNode getChild(int i);
}

class Node extends AbstractNode {
    private List<AbstractNode> children;
    private int id;
    private int pid;
    public Node() {
        children = new ArrayList<AbstractNode>();
    }
    @Override
    public boolean isRoot() {
        return -1 == pid;
    }
    @Override
    public int getId() {
        return id;
    }
    @Override
    public int getParentId() {
        return pid;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public void setParentId(int parentId) {
        this.pid = parentId;
    }
    public void add(AbstractNode c) {
        c.setParentId(this.pid+children.size());
        c.setId(c.getParentId()+1);
        children.add(c);
    }
    public void remove(AbstractNode c) {
        children.remove(c);
    }
    public AbstractNode getChild(int i) {
        return children.get(i);
    }
}

class Leaf extends AbstractNode {
    private int id;
    private int pid;
    @Override
    public boolean isRoot() {
        return false;
    }
    @Override
    public int getId() {
        return this.id;
    }
    @Override
    public int getParentId() {
        return this.pid;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public void setParentId(int parentId) {
        this.pid  = parentId;
    }
    @Override
    public void add(AbstractNode abstractNode) {
        throw new UnsupportedOperationException("这个是叶子节点，无法增加");
    }
    @Override
    public void remove(AbstractNode g) {
        throw new UnsupportedOperationException("这个是叶子节点，无法删除");
    }
    @Override
    public AbstractNode getChild(int i) {
        return null;
    }
}

class demo {
    public static void main(String[] args) {
        AbstractNode rootNode = new Node();
        rootNode.setId(0);
        rootNode.setParentId(-1);
        AbstractNode node1 = new Node();
        node1.add(new Leaf());
        node1.add(new Leaf());
        rootNode.add(new Leaf());
        rootNode.add(new Leaf());
        rootNode.add(node1);

        System.out.println(node1.getId());
    }
}
