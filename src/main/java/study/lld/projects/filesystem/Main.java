package study.lld.projects.filesystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

/*
Time: 1 hour 15 mins
- Graph representation of filesystem
- move has to think about cycles
- move/rename/create has to think about same name conflict
- writing to a file and adding to a directory need locking for thread safety
 */
public class Main {
    public static void main(String[] args) {
        /*
        File System
        - ability to create a file and directory
        - ability to read a file
        - ability to list files & directory in a directory
        - ability to search for a file in a directory
        - ability to move a file
        - rollback with WAL / Journaling
        - file permissions

        FileSystemNode
        - id
        - name
        - int parentNodeId
        - int version
        - permissions

        File extends FileSystemNode
        - content: String

        Directory extends FileSystemNode
        - none

        FileSystem
        - init - creates root Directory
        - createFile(name, parentNodeId, content)
        - createDirectory(name, parentNodeId)
        - readFile(nodeId): String
        - writeToFile(nodeId, APPEND/OVERWRITE): void
        - readFile(name, parentNodeId)
        - moveFile(nodeId, parentNodeId)
        - deleteFile(nodeId)
        - listFilesInDirectory(nodeId)
        - findFileByName("name"): List<File> lists files with same name in all directories
        - rename(nodeId, newName)


        Invariants
        - If writer A gets version 1 of file then before writing it it again checks the version of file by doing a fresh call
            If the version is same then write goes through otherwise it fails, it also increments the version number
            Same goes for directory. We will use a transaction to make the read and update atomic
        - files will be saved in a durable storage like DISK, I will abstract it with a DAO.
            Underlying seeks etc will be skipped.
        - If we edit an existing file, then internally we create a copy of the original while editing in the same directory
            then when we complete the dit and save then we check the version of the original, if its as expected then we allow save
            otherwise we throw
        - We can choose to keep versions of files as FIleSystem also maintains jornalling of edits to the file system,
            but we will not do this at this time for simplification
        - A root directory will be created at initialisation of filesystem
        - when we move a file just its parent directory changes.
        - Absolute path of a file is always computed at runtime.
        - currentDirectory/changeDirectory semantics are skipped.
        - move directory should not create cycles / create directory should not create cycles
        - files inside a directory should be unique
         */
    }
}

class FileSystemNode {
    int id;
    String name;
    FileSystemNode parent;
    ReentrantLock writeLock = new ReentrantLock();
}

class File extends FileSystemNode {
    String content;
}

class Directory extends FileSystemNode {
    List<FileSystemNode> children = new ArrayList<>();
}

class FileSystem {
    FileSystemDao fileSystemDao;

    void init() {
        fileSystemDao.createRoot();
    }

    void createFile(String name, Directory parent, String content) {
        throwIfInvalidDirectory(parent);

        parent.writeLock.lock();
        try {
            List<FileSystemNode> nodesInDirectory = fileSystemDao.listNodesInDirectory(parent);
            nodesInDirectory.forEach(
                    node -> {
                        if (Objects.equals(node.name, name)) {
                            throw new RuntimeException(
                                    "file/directory with same name already exists");
                        }
                    });

            fileSystemDao.createFile(name, parent, content);
        } finally {
            parent.writeLock.unlock();
        }
    }

    void createDirectory(String name, Directory parent) {
        throwIfInvalidDirectory(parent);

        parent.writeLock.lock();
        try {
            List<FileSystemNode> nodesInDirectory = fileSystemDao.listNodesInDirectory(parent);
            nodesInDirectory.forEach(
                    node -> {
                        if (Objects.equals(node.name, name)) {
                            throw new RuntimeException(
                                    "file/directory with same name already exists");
                        }
                    });

            fileSystemDao.createDirectory(name, parent);
        } finally {
            parent.writeLock.unlock();
        }
    }

    private void throwIfInvalidDirectory(Directory parent) {
        if (fileSystemDao.get(parent).isEmpty()) {
            throw new RuntimeException("invalid parent directory");
        }
    }

    List<File> findFileByNameInAllPlaces(String name) {
        return fileSystemDao.findFileByNameInAllPlaces(name);
    }

    List<File> findFileInDirectoryAndItsChildren(String fileName, Directory directory) {
        throwIfInvalidDirectory(directory);
        return fileSystemDao.findFileByNameFromDirectory(fileName, directory);
    }

    void writeToFile(File file, String content, WriteType writeType) {
        throwIfInvalidFile(file);
        file.writeLock.lock();
        try {
            fileSystemDao.writeToFile(file, content, writeType);
        } finally {
            file.writeLock.unlock();
        }
    }

    private void throwIfInvalidFile(File file) {}

    void moveNode(FileSystemNode node, Directory newParent) {
        throwIfInvalidDirectory(newParent);
        FileSystemNode oldParent = node.parent;
        takeWriteLocks(oldParent, newParent);
        try {
            if (node.id == newParent.id) {
                throw new RuntimeException("cannot be moved to same node");
            }
            if (fileSystemDao.isNewParentAChildOfNode(node, newParent)) {
                throw new RuntimeException("node cannot be moved down to itss own children");
            }
            newParent.children.forEach(
                    n -> {
                        if (n.name.equals(node.name)) {
                            throw new RuntimeException(
                                    "destination already has a file/dir with same name");
                        }
                    });

            fileSystemDao.changeParent(node, newParent);
        } finally {
            releaseWriteLocks(oldParent, newParent);
        }
    }

    private void takeWriteLocks(FileSystemNode nodeA, FileSystemNode nodeB) {
        if (nodeA.id <= nodeB.id) {
            nodeA.writeLock.lock();
            nodeB.writeLock.lock();
        } else {
            nodeB.writeLock.lock();
            nodeA.writeLock.lock();
        }
    }

    private void releaseWriteLocks(FileSystemNode nodeA, FileSystemNode nodeB) {
        if (nodeA.id <= nodeB.id) {
            nodeB.writeLock.unlock();
            nodeA.writeLock.unlock();
        } else {
            nodeA.writeLock.unlock();
            nodeB.writeLock.unlock();
        }
    }

    List<FileSystemNode> listFilesInDirectory(Directory directory) {
        throwIfInvalidDirectory(directory);
        return directory.children;
    }
}

enum WriteType {
    APPEND,
    OVERWRITE
}

interface FileSystemDao {

    void createRoot();

    Directory getRoot();

    Optional<FileSystemNode> get(FileSystemNode parent);

    List<FileSystemNode> listNodesInDirectory(Directory parent);

    void createFile(String name, Directory parent, String content);

    void createDirectory(String name, Directory parent);

    List<File> findFileByName(String name);

    default List<File> findFileByNameInAllPlaces(String name) {
        return findFileByNameFromDirectory(name, getRoot());
    }

    default List<File> findFileByNameFromDirectory(String fileName, Directory directory) {
        List<File> result = new ArrayList<>();
        walkFilesystemAndFindFilesWithName(directory, result, fileName);
        return result;
    }

    default void walkFilesystemAndFindFilesWithName(
            Directory directory, List<File> result, String fileName) {
        List<FileSystemNode> children = directory.children;
        for (FileSystemNode child : children) {
            if (child instanceof Directory dir) {
                walkFilesystemAndFindFilesWithName(dir, result, fileName);
            } else if (child.name.equals(fileName)) {
                result.add((File) child);
            }
        }
    }

    void writeToFile(File file, String content, WriteType writeType);

    void changeParent(FileSystemNode node, Directory newParent);

    boolean isNewParentAChildOfNode(FileSystemNode node, Directory newParent);
}
