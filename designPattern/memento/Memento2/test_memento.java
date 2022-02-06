class Blog {
    private long id;
    private String title;
    private String content;
    public Blog(long id, String title) {
        super();
        this.id = id;
        this.title = title;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public BlogMemento createMemento() {
        BlogMemento m = new BlogMemento(id, title, content);
        return m;
    }
    public void restore(BlogMemento m) {
        this.id = m.getId();
        this.title = m.getTitle();
        this.content = m.getContent();
    }
    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

class BlogMemento {
    private final long id;      //fixme:final ???
    private final String title;
    private final String content;
    public BlogMemento(long id, String title, String content) {
        super();
        this.id = id;
        this.title = title;
        this.content = content;
    }
    public long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
}

class Client {
    public static void main(String[] args) {
        Blog blog = new Blog(1, "My Blog");
        blog.setContent("ABC");      //原始的文章内容
        System.out.println(blog);

        BlogMemento memento = blog.createMemento();   //创建blog的备忘录
        blog.setContent("123");      //改变内容
        System.out.println(blog);

        blog.restore(memento);       //撤销操作
        System.out.println(blog);    //这时会显示原始的内容
    }
}
