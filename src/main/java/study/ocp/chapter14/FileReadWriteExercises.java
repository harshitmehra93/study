package study.ocp.chapter14; // Exercise 5 — Create Directory Structure
// Write code to create this directory structure:

// logs/
//     2026/
//         march/

// Use:

// Files.createDirectories()

// Exercise 6 — Copy File with Replace
// Write code to copy:

// report.txt

// to

// backup/report.txt

// Requirements:
// * create directory if needed
// * replace existing file
// Use:

// Files.copy
// StandardCopyOption.REPLACE_EXISTING

// import java.nio.file.*;
// import java.io.*;
// public class Test37{
// 	public static void main(String... args) throws IOException{
// 		Path a = Path.of("./report.txt");
// 		if(!Files.exists(a)){
// 			Files.createFile(a);
// 		}

// 		Path b = Path.of("./backup/report2.txt");
// 		if(!Files.exists(b.getParent())){
// 			Files.createDirectories(b);

// 		}
// 		try{Files.copy(a,b);}
// 		catch(IOException e){e.printStackTrace();}
// 	}
// }

// Exercise 7 — Read File Line by Line
// Given notes.txt, print:

// 1 -> first line
// 2 -> second line
// 3 -> third line

// Use:

// Files.lines()

// Practice:
// * Streams
// * IO integration

// import java.nio.file.*;
// import java.io.*;
// import java.util.*;
// import java.util.stream.*;
// public class Test37{
// 	public static void main(String... args) throws IOException{
// 		Path a = Path.of("./notes.txt");
// 		if(!Files.exists(a)){
// 			Files.createFile(a);
// 		}

// 		Stream<String> lines = Files.lines(a);
// 		lines.forEach(System.out::println);
// 	}
// }

// Exercise 8 — Find Long Lines
// Print lines longer than 80 characters from a file.
// Use:

// Files.lines()
// stream.filter()

// import java.nio.file.*;
// import java.io.*;
// import java.util.*;
// import java.util.stream.*;
// public class Test37{
// 	public static void main(String... args) throws IOException{
// 		Path a = Path.of("./notes.txt");
// 		if(!Files.exists(a)){
// 			Files.createFile(a);
// 		}

// 		Stream<String> lines = Files.lines(a);
// 		lines.filter(s->s.length()>80).forEach(System.out::println);
// 	}
// }

// Exercise 9 — Count Words in File
// Compute:

// Total lines
// Total words
// Total characters

// Use:

// Files.lines()
// Streams

// With flatMap and mapToInt
// import java.nio.file.*;
// import java.io.*;
// import java.util.*;
// import java.util.stream.*;
// import java.util.concurrent.atomic.*;
// public class Test37{
// 	public static void main(String... args) throws IOException{
// 		Path a = Path.of("./notes.txt");

// 		try(Stream<String> lines = Files.lines(a)){
// 			System.out.println("lines = "+lines.count());
// 		}

// 		try(Stream<String> lines = Files.lines(a)){
// 			long wordsCount = lines
// 				.flatMap(line->Arrays.stream(line.split(" ")))
// 				.count();

// 			System.out.println("words = "+wordsCount);
// 		}

// 		try(Stream<String> lines = Files.lines(a)){
// 			long characterCount = lines
// 				.flatMap(line->Arrays.stream(line.split(" ")))
// 				.mapToInt(String::length)
// 				.sum();
// 			System.out.println("characterCount = "+characterCount);
// 		}
// 	}
// }

// With reduce
// import java.nio.file.*;
// import java.io.*;
// import java.util.*;
// import java.util.stream.*;
// import java.util.concurrent.atomic.*;
// public class Test37{
// 	public static void main(String... args) throws IOException{
// 		Path a = Path.of("./notes.txt");

// 		try(Stream<String> lines = Files.lines(a)){
// 			long[] result = lines
// 								.reduce(
// 									new long[3],
// 									(arr,line)->{
// 										arr[0]++; // increment line

// 										// split line into words
// 										String[] words = line.split(" ");
// 										arr[1]+=words.length; // num of words in line

// 										for(String word : words)
// 											arr[2]+=word.length(); // length of each word

// 										return arr;
// 									},
// 									(arr1,arr2)->arr1
// 								);
// 			System.out.println(result[0]);
// 			System.out.println(result[1]);
// 			System.out.println(result[2]);

// 		}
// 	}
// }

// 4. Writing Files
// Exercise 10 — Write Log File
// Write the following messages to app.log:

// Application started
// Loading config
// Application finished

// Requirements:
// * append mode
// * UTF-8 encoding
// Use:

// Files.writeString()
// StandardOpenOption.APPEND

// import java.nio.file.*;
// import java.io.*;
// import java.util.*;
// import java.util.stream.*;
// import java.util.concurrent.atomic.*;
// public class Test37{
// 	public static void main(String... args) throws IOException{
// 		Path a = Path.of("./logs/notes.txt");

// 		try{Files.writeString(a, "Hello\n");}
// 		catch(IOException e){}

// 		try{Files.writeString(a, "Hello again", StandardOpenOption.APPEND);}
// 		catch(IOException e){}
// 	}
// }

// Exercise 11 — Export Data
// Given:

// List<String> users = List.of("Alice","Bob","Charlie");

// Write them into

// users.txt

// One per line.

// Use:
// Files.write()

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

public class FileReadWriteExercises {
    public static void main(String... args) throws IOException {
        Path a = Path.of("./logs/notes.txt");

        List<String> users = List.of("Alice", "Bob", "Charlie");

        users.stream()
                .forEach(
                        s -> {
                            try {
                                Files.writeString(a, s + "\n", StandardOpenOption.APPEND);
                            } catch (IOException e) {
                            }
                        });
    }
    ;
}
