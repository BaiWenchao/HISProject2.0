package logic.DataStructure;

public class MyPriorityQueue<E extends Comparable<E>> {
    private MyHeap<E> heap = new MyHeap<>();


    //写上constructor和get，set不然JSON会搞事情
    public MyPriorityQueue() {
    }

    public MyHeap<E> getHeap() {
        return heap;
    }

    public void setHeap(MyHeap<E> heap) {
        this.heap = heap;
    }

    public void insert(E element){
        heap.insert(element);
    }

    public E remove(){
        return heap.remove();
    }

    public int size(){
        return heap.size();
    }

    public E getItem(int i) {
        return heap.getItem(i);
    }

    public E remove(int i){
        return heap.remove(i);
    }

    public E get(int i){
        return heap.get(i);
    }

}
