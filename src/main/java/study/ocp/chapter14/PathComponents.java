package study.ocp.chapter14; // Given

// Path p = Path.of("/users/harshit/projects/java/data/file.txt");

// Write code that prints:

// File name:
// Parent:
// Root:
// Name count:
// First element:
// Last element:

// Goal: practice
// * getFileName()
// * getParent()
// * getRoot()
// * getName()
// * getNameCount()
import java.nio.file.*;

class PathComponents {
    public static void main(String... args) {
        Path p = Path.of("/users/harshit/projects/java/data/file.txt");
        System.out.println(p.getFileName());
        var parent = p.getParent();
        while (parent != null) {
            System.out.println(parent);
            parent = parent.getParent();
        }

        System.out.println(p.getRoot());
        System.out.println(p.getNameCount());
        for (int i = p.getNameCount() - 1; i > 0; i--) System.out.println(p.getName(i));
        System.out.println();
    }
}
