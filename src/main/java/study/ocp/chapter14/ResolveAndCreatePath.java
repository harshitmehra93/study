package study.ocp.chapter14; // Write a program that produces the following path:
// /users/harshit/projects/logs/app.log
// Using:
// Path.resolve()
// Starting from
// /users/harshit/projects
import java.nio.file.*;

class ResolveAndCreatePath {
    public static void main(String... args) throws Exception {
        Path p = Path.of("./logs");
        Files.createDirectories(p);

        Path f = p.resolve(Path.of("app.log"));
        Files.createFile(f);
    }
}
