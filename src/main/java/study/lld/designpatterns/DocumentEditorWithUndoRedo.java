package study.lld.designpatterns;

import java.util.Stack;

public class DocumentEditorWithUndoRedo {
    public static void main(String[] args) {
        /*
        1. Document : String
        2. DocumentEditor
            executeCommand
            undo
            redo
        3. Command
        4. WriteString
         */
        Document document = new Document();
        Command a = new WriteString("a", document);
        Command b = new WriteString("b", document);
        Command c = new WriteString("c", document);
        Command d = new WriteString("d", document);
        Command e = new WriteString("e", document);
        Command f = new WriteString("f", document);

        DocumentEditor editor = new DocumentEditor();
        editor.execute(a);
        editor.execute(b);
        editor.execute(c);
        System.out.println(document);
        editor.undo();
        editor.undo();
        editor.redo();
        editor.execute(d);
        System.out.println(document);
        editor.undo();
        editor.undo();
        editor.undo();
        editor.execute(e);
        System.out.println(document);
    }
}

class DocumentEditor {
    private final Stack<Command> executed = new Stack<>();
    private final Stack<Command> undone = new Stack<>();

    DocumentEditor() {}

    void execute(Command command) {
        undone.clear();

        executed.push(command);
        command.execute();
    }

    void undo() {
        if (executed.isEmpty()) return;

        Command lastExecuted = executed.pop();
        undone.push(lastExecuted);
        lastExecuted.undo();
    }

    void redo() {
        if (undone.isEmpty()) return;

        Command lastUndone = undone.pop();
        executed.push(lastUndone);
        lastUndone.execute();
    }
}

class Document {
    final StringBuilder sb;

    Document() {
        this.sb = new StringBuilder();
    }

    void append(String str) {
        sb.append(str);
    }

    void deleteFromEnd(int len) {
        sb.delete(sb.length() - len, sb.length());
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

interface Command {
    void execute();

    void undo();
}

class WriteString implements Command {
    final String str;
    final Document document;

    WriteString(String str, Document document) {
        this.str = str;
        this.document = document;
    }

    @Override
    public void execute() {
        document.append(str);
    }

    @Override
    public void undo() {
        document.deleteFromEnd(str.length());
    }
}
