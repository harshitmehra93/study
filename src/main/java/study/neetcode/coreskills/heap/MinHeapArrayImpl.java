package study.neetcode.coreskills.heap;

public class MinHeapArrayImpl implements Heap {
    int size=0;
    int[] heap = new int[10000];

    MinHeapArrayImpl(){}
    MinHeapArrayImpl(int[] arr){
        heap=arr;
        size=arr.length;
        for(int i=arr.length/2-1;i>=0;i--)
            minHeapifyIterative(i);
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Integer get() {
        if(size==0)
            throw new HeapException("heap underflow");
        int head = heap[0];
        heap[0]=heap[size-1];
        size--;
        minHeapifyIterative(0);
        return head;
    }

    private void minHeapify(int index) {
        int leftIndex=getLeft(index);
        int rightIndex=getRight(index);
        int smallest=index;
        if(leftIndex<size&&heap[leftIndex]<heap[smallest]){
            smallest=leftIndex;
        }
        if(rightIndex<size&&heap[rightIndex]<heap[smallest]){
            smallest=rightIndex;
        }
        if(smallest!=index){
            exchange(index,smallest);
            minHeapifyIterative(smallest);
        }
    }

    private void exchange(int index, int smallest) {
        int tmp = heap[index];
        heap[index]=heap[smallest];
        heap[smallest]=tmp;
    }

    private int getRight(int index) {
        return 2*index+2;
    }

    private int getLeft(int index) {
        return 2*index+1;
    }

    @Override
    public void push(int i) {
        heap[size]=i;
        size++;
        minHeapifyParent(size-1);
    }

    private void minHeapifyParent(int index) {
        if(index==0) {return;}
        int parentIndex = getParent(index);
        int smallest=index;
        if(heap[smallest]<heap[parentIndex]){
            smallest=parentIndex;
        }
        if(smallest!=index){
            exchange(parentIndex,index);
            minHeapifyParent(parentIndex);
        }
    }

    private void minHeapifyIterative(int index){
        int pointer = index;
        while(pointer<size){
            var left=getLeft(pointer);
            var right = getRight(pointer);
            var smallest=pointer;
            if(left<size&&heap[smallest]>heap[left]){
                smallest=left;
            }
            if(left<size&&heap[smallest]>heap[right]){
                smallest=right;
            }
            if(smallest!=pointer){
                exchange(pointer,smallest);
                pointer=smallest;
            }else {
                break;
            }
        }
    }

    private int getParent(int index) {
        return (index-1)/2;
    }
}
