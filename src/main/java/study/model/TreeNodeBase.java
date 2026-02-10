package study.model;

public interface TreeNodeBase<T> {
    T getValue();
    TreeNodeBase<T> getLeft();
    TreeNodeBase<T> getRight();
}
