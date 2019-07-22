public class MyHeap {
    private int[] heap;
    private int curSize; //current size of heap
    private int maxSize;
    private final int DEFAULT_SIZE = 10;
    private final int ROOT = 1;
    
    public MyHeap() {
        heap = new int[DEFAULT_SIZE + 1]; 
        curSize = 0;
        maxSize = DEFAULT_SIZE;
        
    }
    public MyHeap(int capacity) {
        heap = new int[capacity + 1]; 
        curSize = 0;
        maxSize = capacity;
    }
    private void expandHeap() {
        maxSize = maxSize * 2;
        int[] temp = heap;
        heap = new int[maxSize + 1];
        for(int i = 1; i <= temp.length; i++) {
            heap[i] = temp[i];
        }
    }
    
    private int leftChild(int position) {
        return heap[position*2];
    }
    
    private int rightChild(int position) {
        return heap[position*2+1];
    }
    
    private int parentOf(int position) {
        return heap[position/2]; 
    }
    
    private void swap(int position1, int position2) {
        int temp = heap[position1];
        heap[position1] = heap[position2];
        heap[position2] = temp;
    }
    
    private void maxHeapify(int curPos) {
        if(curPos >= (curSize/2) && curPos <= curSize) {
            //if is leaf node
            return;
        }
        if(heap[curPos] < leftChild(curPos) || heap[curPos] < rightChild(curPos)) {
            if(leftChild(curPos) > rightChild(curPos)) {
                swap(curPos * 2, curPos);
                maxHeapify(curPos * 2);
            }else {
                swap(curPos * 2 + 1, curPos);
                maxHeapify(curPos * 2 + 1);
            }
        }
    }
    
    public void insert(int item) {
        curSize++;
        if(curSize >= maxSize) {
            expandHeap();
        }heap[curSize] = item;
        int curPosition = curSize;
        while(curPosition != ROOT && heap[curPosition] > parentOf(curPosition)) {
            //if cur node is greater
            swap(curPosition, curPosition/2); 
            //swap with parent node
            curPosition = curPosition / 2;
        }
    }
    
    public int deleteMax() {
        int max = heap[ROOT];
        heap[ROOT] = heap[curSize];
        heap[curSize--] = 0;
        maxHeapify(ROOT);
        return max;
    }
    
    public void printHeap() {
        for(int i = 0; i < heap.length; i++) {
            System.out.println(heap[i]);
        }
    }
    
    public static void main(String[] args) {
        MyHeap heap = new MyHeap();
        heap.insert(8);
        heap.insert(2);
        heap.insert(4);
        heap.insert(9);
        heap.insert(3);
        System.out.println("Max: " + heap.deleteMax());
        heap.printHeap();
        System.out.println("Max: " + heap.deleteMax());
        heap.printHeap();
    }
}