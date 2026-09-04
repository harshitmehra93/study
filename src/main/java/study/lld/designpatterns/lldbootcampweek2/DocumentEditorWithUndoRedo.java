package study.lld.designpatterns.lldbootcampweek2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DocumentEditorWithUndoRedo {
    public static void main(String[] args) {

        TypingService typingService = new TypingService();
        Command a = new TypeCommand("Hello", typingService);
        Command b = new TypeCommand("my", typingService);
        Command c = new TypeCommand("name", typingService);
        Command d = new TypeCommand("is", typingService);
        Command e = new TypeCommand("Harshit", typingService);

        JobExecutor executor = new JobExecutor();

        executor.execute(a);
        executor.execute(b);
        executor.execute(c);
        typingService.getDocument();
        executor.undo();
        executor.undo();
        executor.redo();
        executor.execute(d);
        typingService.getDocument();

        List<Command> macro = executor.recordMacro();

        typingService.clear();
        JobExecutor executor2 = new JobExecutor();
        macro.forEach(command -> executor2.execute(command));
        typingService.getDocument();
    }
}

class JobExecutor {
    private final Stack<Command> executed = new Stack<>();
    private final Stack<Command> undoneCommands = new Stack<>();

    void execute(Command command) {
        executed.add(command);
        command.execute();
        undoneCommands.clear();
    }

    void undo() {
        if (executed.isEmpty()) return;
        Command lastRun = executed.pop();
        undoneCommands.push(lastRun);
        lastRun.undo();
    }

    void redo() {
        if (undoneCommands.isEmpty()) return;

        Command command = undoneCommands.pop();
        command.execute();
        executed.push(command);
    }

    List<Command> recordMacro() {
        return new ArrayList<>(executed);
    }
}

interface Command {
    void execute();

    void undo();
}

class TypeCommand implements Command {

    private final String text;
    private final TypingService typingService;

    TypeCommand(String text, TypingService typingService) {
        this.text = text;
        this.typingService = typingService;
    }

    @Override
    public void execute() {
        typingService.type(text);
    }

    @Override
    public void undo() {
        typingService.undo(text);
    }
}

class TypingService {

    StringBuilder sb = new StringBuilder();

    public void type(String text) {
        System.out.println("Typing- " + text);
        sb.append(text);
    }

    public void undo(String text) {
        System.out.println("Undo- " + text);
        sb.delete(sb.length() - text.length(), sb.length());
    }

    public String getDocument() {
        System.out.println(sb.toString());
        return sb.toString();
    }

    void clear() {
        sb = new StringBuilder();
    }
}
