package logic.DataStructure;

public class MyPriorityQueue<E extends Comparable<E>> {
    private MyHeap<E> heap = new MyHeap<>();

    public void insert(E element){
        heap.insert(element);
    }

    public E remove(){
        return heap.remove();
    }

    public int size(){
        return heap.size();
    }

}
