import java.util.*;

interface ChatRoom {
    void sendMessage(String msg, String userId);
    void addUser(User user);
}
class ChatRoomImpl implements ChatRoom {
    private Map<String, User> usersMap = new HashMap<>();
    @Override
    public void sendMessage(String msg, String userId) {
        User u = usersMap.get(userId);
        u.receive(msg);
    }
    @Override
    public void addUser(User user) {
        this.usersMap.put(user.getId(), user);
    }
}
abstract class User {
    private ChatRoom mediator;
    private String id;
    private String name;
    public User(ChatRoom room, String id, String name){
        this.mediator = room;
        this.name = name;
        this.id = id;
    }
    public abstract void send(String msg, String userId);
    public abstract void receive(String msg);
    public ChatRoom getMediator() {
        return mediator;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}

class ChatUser extends User {
    public ChatUser(ChatRoom room, String id, String name) {
        super(room, id, name);
    }
    @Override
    public void send(String msg, String userId) {
        System.out.println(this.getName() + " :: Sending Message : " + msg);
        getMediator().sendMessage(msg, userId);
    }
    @Override
    public void receive(String msg) {
        System.out.println(this.getName() + " :: Received Message : " + msg);
    }

}
class Client {
    public static void main(String[] args) {
        ChatRoom chatroom = new ChatRoomImpl();
        User user1 = new ChatUser(chatroom,"1", "Spike");
        User user2 = new ChatUser(chatroom,"2", "Mia");
        User user3 = new ChatUser(chatroom,"3", "Max");
        User user4 = new ChatUser(chatroom,"4", "Mick");
        chatroom.addUser(user1);
        chatroom.addUser(user2);
        chatroom.addUser(user3);
        chatroom.addUser(user4);
        user1.send("Hello man", "2");
        user2.send("Hey", "1");
    }
}