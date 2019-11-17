package logic.DataStructure;

public class MyQueue<E> {
    private E[] list;
    private static final int DEFAULT_MAXIMUM = 100;
    int first = 0;
    int firstFree = 0;
    int length = 0;

    public MyQueue(){
        list = (E[]) new Object[DEFAULT_MAXIMUM];
    }

    public MyQueue(int size){
        list = (E[]) new Object[size];
    }

    public boolean isEmpty(){
        return(this.length == 0);
    }

    public void insert(E element){
        // if queue is full
        if((first == firstFree) && (list[first] != null)){
            throw new IndexOutOfBoundsException();
        }

        // insert element
        list[firstFree] = element;
        length++;

        // deal with firstFree
        firstFree++;
        if(firstFree == list.length){
            firstFree = 0;
        }
    }

    public E remove() throws Exception {
        if(isEmpty()){
            throw new Exception("the queue is empty!");
        }
        E tempElement = list[first];
        //remove the first element
        list[first] = null;
        length--;

        //deal with first
        first++;
        if(first == list.length){
            first = 0;
        }

        //return element
        return tempElement;
    }

    public E getFront(){
        if(isEmpty()){
            System.out.println("the queue is empty!");
            return null;
        }

        return list[first];
    }



}

