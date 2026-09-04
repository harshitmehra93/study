package study.ocp.io; // Exercise 4 — Check File Properties
// Write a program that checks for a given path:

// data.txt

// Print:

// exists
// is directory
// is regular file
// is readable
// is writable
// size

// Use:

// Files.exists
// Files.isDirectory
// Files.isRegularFile
// Files.isReadable
// Files.size

import java.io.*;
import java.nio.file.*;

public class FileProperties {
    public static void main(String... args) {
        Path a = Path.of("data.txt");

        System.out.println(Files.exists(a));
        System.out.println(Files.isDirectory(a));
        System.out.println(Files.isRegularFile(a));
        System.out.println(Files.isReadable(a));

        try {
            System.out.println(Files.size(a));
        } catch (IOException io) {
        }
    }
}
