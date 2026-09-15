package study.lld.designpatterns.lldbootcampweek2;

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
        Command a = new WriteString("a");
        Command b = new WriteString("b");
        Command c = new WriteString("c");
        Command d = new WriteString("d");
        Command e = new WriteString("e");
        Command f = new WriteString("f");

        DocumentEditor editor = new DocumentEditor();
        editor.execute(a);
        editor.execute(b);
        editor.execute(c);
        editor.undo();
        editor.undo();
        editor.redo();
        editor.execute(d);
        editor.undo();
        editor.undo();
        editor.undo();
        System.out.println(editor.toString());
    }
}

class DocumentEditor {
    private final StringBuilder sb;
    private final Stack<Command> executed = new Stack<>();
    private final Stack<Command> undone = new Stack<>();

    DocumentEditor() {
        this.sb = new StringBuilder();
    }

    void execute(Command command) {
        undone.clear();

        executed.push(command);
        command.execute(sb);
    }

    void undo() {
        if (executed.isEmpty()) return;

        Command lastExecuted = executed.pop();
        undone.push(lastExecuted);
        lastExecuted.undo(sb);
    }

    void redo() {
        if (undone.isEmpty()) return;

        Command lastUndone = undone.pop();
        executed.push(lastUndone);
        lastUndone.execute(sb);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

interface Command {
    void execute(StringBuilder sb);

    void undo(StringBuilder sb);
}

class WriteString implements Command {
    final String str;

    WriteString(String str) {
        this.str = str;
    }

    @Override
    public void execute(StringBuilder sb) {
        sb.append(str);
    }

    @Override
    public void undo(StringBuilder sb) {
        sb.delete(sb.length() - str.length(), sb.length());
    }
}
