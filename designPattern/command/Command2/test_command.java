import java.util.*;

interface Command {
    void execute();
}
class Open implements Command {
    private Editor editor;
    public Open(Editor editor) {
        this.editor = editor;
    }
    @Override
    public void execute() {
        editor.open();
    }
}
class Save implements Command {
    private Editor editor;
    public Save(Editor editor) {
        this.editor = editor;
    }
    @Override
    public void execute() {
        editor.save();
    }
}
class Close implements Command{
    private Editor editor;
    public Close(Editor editor) {
        this.editor = editor;
    }
    @Override
    public void execute() {
        editor.close();
    }
}
interface Editor {
    void open();
    void save();
    void close();
}

class Html5Editor implements Editor {

    @Override
    public void open() {
        System.out.println("=== Html5Editor 执行 open 操作");
    }
    @Override
    public void save() {
        System.out.println("=== Html5Editor 执行 save 操作");
    }
    @Override
    public void close() {
        System.out.println("=== Html5Editor 执行 close 操作");
    }
}

class MarkDownEditor implements Editor {
    @Override
    public void open() {
        System.out.println("=== MarkDownEditor 执行 open 操作");
    }
    @Override
    public void save() {
        System.out.println("=== MarkDownEditor 执行 save 操作");
    }
    @Override
    public void close() {
        System.out.println("=== MarkDownEditor 执行 close 操作");
    }
}


class WebEditFlow {
    private final List<Command> commands;
    public WebEditFlow() {
        commands = new ArrayList<>();
    }
    public void setCommand(Command command) {
        commands.add(command);
    }
    public void run() {
        commands.forEach(Command::execute);
    }
}
class Client {
    public static void main(String[] args) {
        Html5Editor html5Editor = new Html5Editor();
        MarkDownEditor markDownEditor = new MarkDownEditor();
        Open html5Open = new Open(html5Editor);
        Save html5Save = new Save(html5Editor);
        Close html5Close = new Close(html5Editor);
        Open markDownOpen = new Open(markDownEditor);
        Save markDownSave = new Save(markDownEditor);
        Close markDownClose = new Close(markDownEditor);
        WebEditFlow webEditFlow = new WebEditFlow();
        webEditFlow.setCommand(html5Open);
        webEditFlow.setCommand(html5Save);
        webEditFlow.setCommand(html5Close);
        webEditFlow.setCommand(markDownOpen);
        webEditFlow.setCommand(markDownSave);
        webEditFlow.setCommand(markDownClose);
        webEditFlow.run();
    }
}