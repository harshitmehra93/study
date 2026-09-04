package study.ocp.chapter14; // 5. Byte Streams
// Exercise 12 — Binary File Copy
// Write a method that copies any binary file using:

// FileInputStream
// FileOutputStream

// Read buffered chunks, not single bytes.
// Use:

// byte[] buffer

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

public class BufferedBinaryFileCopy {
    public static void main(String... args) throws IOException {
        File a = new File("./logs/notes.txt");
        FileInputStream fio = new FileInputStream(a);
    }
}
