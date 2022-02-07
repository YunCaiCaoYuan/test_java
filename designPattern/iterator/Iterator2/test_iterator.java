interface IteratorIterator<E> {
    void reset();       //重置为第一个元素
    E next();           //获取下一个元素
    E currentItem();    //检索当前元素
    boolean hasNext();  //判断是否还有下一个元素存在.
}

interface ListList<E> {
    IteratorIterator<E> iterator();
}

class Topic {
    private String name;
    public Topic(String name) {
        super();
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

class TopicIterator implements IteratorIterator<Topic> {
    private final Topic[] topics;
    private int position;
    public TopicIterator(Topic[] topics) {
        this.topics = topics;
        position = 0;
    }
    @Override
    public void reset() {
        position = 0;
    }
    @Override
    public Topic next() {
        return topics[position++];
    }
    @Override
    public Topic currentItem() {
        return topics[position];
    }
    @Override
    public boolean hasNext() {
        if(position >= topics.length) {
            return false;
        }
        return true;
    }
}

class TopicList implements ListList<Topic>{
    private final Topic[] topics;
    public TopicList(Topic[] topics)
    {
        this.topics = topics;
    }
    @Override
    public IteratorIterator<Topic> iterator() {
        return new TopicIterator(topics);
    }
}

class Client {
    public static void main(String[] args) {
        Topic[] topics = new Topic[5];
        topics[0] = new Topic("topic1");
        topics[1] = new Topic("topic2");
        topics[2] = new Topic("topic3");
        topics[3] = new Topic("topic4");
        topics[4] = new Topic("topic5");
        ListList<Topic> list = new TopicList(topics);
        IteratorIterator<Topic> iterator = list.iterator();
        while(iterator.hasNext()) {
            Topic currentTopic = iterator.next();
            System.out.println(currentTopic.getName());
        }
    }
}


