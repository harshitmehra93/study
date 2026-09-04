package study.ocp.io;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

class FileAndNioComparison {
    ArrayList<File> files = new ArrayList<File>();

    public static void main(String... args) throws IOException {
        File file = new File("/Users/hamehra/Desktop/java/template/myFile");
        Path path = Path.of("/Users/hamehra/Desktop/java/template/nioFile");
        System.out.println("IO file Exists? " + file.exists());
        System.out.println("NIO file Exists? " + Files.exists(path));

        System.out.println("Parent? " + file.getParent());
        System.out.println("NIO Parent? " + path.getParent());

        System.out.println("Name? " + file.getName());
        System.out.println("NIO Name? " + path.getFileName());

        System.out.println("abs? " + file.isAbsolute());
        System.out.println("NIO abs? " + path.isAbsolute());

        System.out.println("dir? " + file.isDirectory());
        System.out.println("NIO dir? " + Files.isDirectory(path));

        System.out.println("file? " + file.isFile());
        System.out.println("NIO file? " + Files.isRegularFile(path));

        System.out.println("size? " + file.length());
        try {
            System.out.println("NIO size? " + Files.size(path));
        } catch (NoSuchFileException e) {
        }

        System.out.println("=====");
        File[] listFiles = new File("/Users/hamehra/Desktop/java/template").listFiles();
        for (File file1 : listFiles) {
            System.out.print(file1.getName() + ", ");
        }
        System.out.println("\n=====");
        Stream<Path> listPaths = Files.list(Path.of("/Users/hamehra/Desktop/java/template"));
        Comparator<Path> comp =
                new Comparator<Path>() {
                    @Override
                    public int compare(Path p1, Path p2) {
                        return p1.getFileName().compareTo(p2.getFileName());
                    }
                };
        listPaths.sorted(comp).forEach(f -> System.out.print(f.getFileName() + ", "));
        System.out.println("\n=====");

        System.out.println("Making dir");
        File dir = new File("/Users/hamehra/Desktop/java/template/myDir/1/2/3");
        dir.mkdirs();
        System.out.println("Making path dir");
        Path pathDir = Path.of("/Users/hamehra/Desktop/java/template/myPathDir/1/2/3");
        try {
            Files.createDirectories(pathDir);
        } catch (FileAlreadyExistsException e) {
        }

        // System.out.println("Deleting dir");
        // dir.delete();
        // System.out.println("Deleting path dir");
        // Files.deleteIfExists(pathDir);

        System.out.println("file abs address - " + dir.getAbsolutePath());
        System.out.println("path abs address - " + pathDir.toAbsolutePath());

        System.out.println("file last modified - " + dir.lastModified());
        System.out.println("path last modified - " + Files.getLastModifiedTime(pathDir));

        File newDir = new File("/Users/hamehra/Desktop/java/template/myNewDir");
        System.out.println("renaming " + dir + " to " + newDir);
        dir.renameTo(newDir);

        Path newPathDir = Path.of("/Users/hamehra/Desktop/java/template/myNewPathDir");
        System.out.println("renaming " + pathDir + " to " + newPathDir);
        Files.move(pathDir, newPathDir);
    }
}
