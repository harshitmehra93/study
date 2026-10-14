package study.lld.projects.unixfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Time: 45m But was missing the core invariant of Predicates
Learnings
- For a filtering heavy design with composition chaining and multiple filter use Predicates and their AND/OR composition
- other learning are derived from FileSystem project
- traverse to all nodes regardless of file/dir because we want to include the target directory in the matching predicate too
 */
public class Main {
    public static void main(String[] args) {
        /*
        Unix find
        - ability to find a file from a specified directory and into all child directories
        - return list of directories and files with same name, or target is subset of the file/dir name.
        - support findByNameStarting from a directory
        - modelling of file and directory as file system node. With root directory having a null parent
        - ability to create a file in a parent directory
        - ability to create a directory in a parent directory
        - The DAO of the filesystem will be abstracted, underlying could be DISK or a Database. We will skip these details
        - User should be able to search for a file in a directory and get list of Strings, these string will be absolute path of file/directory
        - getAbsolute path will be computed at runtime for each file/dir
        - this is mostly a read project so no mutation / concurrency problems may occur, all the mutation of files/directory
            will be handled inside FileSystem API which we will not write fully and assume its thread safe.

        FileSystemNode
        - id
        - name
        - parent: FileSystemNode

        File
        - content: String
        - size

        Directory
        -

        UnixFind
        - find(name,startingDirectory,Predicate): List<String>

        FindPredicate
        - matches(FileSystemNode)

        ExactNameMatchPredicate implements FindPredicate
        - expectedName
        - matches(FileSystemNode): boolean

        IsFilePredicate implements FindPredicate
        - matches(FileSystemNode): boolean

        IsDirectoryPredicate implements FindPredicate
        - matches(FileSystemNode): boolean

        GreaterThanSizePredicate
        - sizeFloorLimit
        - matches(FileSystemNode): boolean

        AndPredicate  implements FindPredicate
        - List<Predicates> predicates
        - matches(FileSystemNode): boolean

        OrPredicate  implements FindPredicate
        - List<Predicates> predicates
        - matches(FileSystemNode): boolean

        FileSystem
        - createFile(name,parent)
        - createDirectory
        - getAbsolutePath(FileSystemNode node)
        - getRoot: Directory
        - listNodesInDirectory(Directory): List<FileSystemNode>
         */
    }
}

class FileSystemNode {
    int id;
    String name;
    FileSystemNode parent;
}

class File extends FileSystemNode {
    String content;
    int sizeInBytes;
}

class Directory extends FileSystemNode {}

class UnixFind {
    FileSystem fileSystem;

    List<String> find(Directory startingDirectory, FindPredicate findPredicate) {
        List<String> result = new ArrayList<>();
        traverse(startingDirectory, findPredicate, result);
        return result;
    }

    private void traverse(FileSystemNode node, FindPredicate findPredicate, List<String> result) {
        if (findPredicate.matches(node)) {
            result.add(fileSystem.getAbsolutePath(node));
        }
        if (node instanceof Directory dir) {
            List<FileSystemNode> children = fileSystem.listNodesInDirectory(dir);
            for (FileSystemNode child : children) {
                traverse(child, findPredicate, result);
            }
        }
    }
}

interface FindPredicate {
    boolean matches(FileSystemNode node);
}

class ExactNameMatchPredicate implements FindPredicate {
    private final String expectedName;

    ExactNameMatchPredicate(String expectedName) {
        this.expectedName = expectedName;
    }

    @Override
    public boolean matches(FileSystemNode node) {
        return node.name.equals(expectedName);
    }
}

class IsFilePredicate implements FindPredicate {

    @Override
    public boolean matches(FileSystemNode node) {
        return node instanceof File;
    }
}

class IsDirectoryPredicate implements FindPredicate {

    @Override
    public boolean matches(FileSystemNode node) {
        return node instanceof Directory;
    }
}

class IsGreaterThanASpecifiedSizeBytesPredicate implements FindPredicate {
    private final int floorSizeInBytes;

    IsGreaterThanASpecifiedSizeBytesPredicate(int floorSizeInBytes) {
        this.floorSizeInBytes = floorSizeInBytes;
    }

    @Override
    public boolean matches(FileSystemNode node) {
        if (node instanceof File file) {
            return file.sizeInBytes > floorSizeInBytes;
        }
        return false;
    }
}

class AndPredicate implements FindPredicate {
    private final List<FindPredicate> predicates;

    AndPredicate(FindPredicate... predicates) {
        this.predicates = new ArrayList<>();
        this.predicates.addAll(Arrays.asList(predicates));
    }

    @Override
    public boolean matches(FileSystemNode node) {
        for (FindPredicate p : predicates) {
            if (!p.matches(node)) {
                return false;
            }
        }
        return true;
    }
}

class OrPredicate implements FindPredicate {
    private final List<FindPredicate> predicates;

    OrPredicate(FindPredicate... predicates) {
        this.predicates = new ArrayList<>();
        this.predicates.addAll(Arrays.asList(predicates));
    }

    @Override
    public boolean matches(FileSystemNode node) {
        for (FindPredicate p : predicates) {
            if (p.matches(node)) {
                return true;
            }
        }
        return false;
    }
}

class FileSystem {

    public List<FileSystemNode> listNodesInDirectory(Directory startingDirectory) {
        return null;
    }

    public String getAbsolutePath(FileSystemNode node) {
        return null;
    }
}
