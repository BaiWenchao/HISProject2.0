package logic.DataStructure;

public class MyStack<T> {
    private T[] list;
    private static final int DEFAULT_MAXIMUM = 100;

    //stands for the first index of free space in an array
    private int firstFree = 0;

    //constructors

    public MyStack() {
        list = (T[]) new Object[DEFAULT_MAXIMUM];  //list = new T[DEFAULT_MAXIMUM]是非法的
    }

    public MyStack(int size) {
        list = (T[]) new Object[size];
    }

    public void push(T element){
        //If stack is full, throw exception
        if(firstFree == list.length){
            throw new StackOverflowError();
        }

        //Add new object
        list[firstFree] = element;


        firstFree++;
    }

    public T pop(){
        if(isEmpty()){
            System.out.println("stack is empty!");
            return null;
        }

        firstFree--;
        return list[firstFree - 1];
    }

    public T peek(){
        if(isEmpty()){
            System.out.println("stack is empty!");
            return null;
        }
        return list[firstFree - 1];
    }

    public boolean isEmpty(){
        if(firstFree == 0){
            return true;
        }
        return false;
    }

    public int depth(){
        return firstFree;
    }

    public boolean equals(MyStack otherStack){    //为什么不用Stack<T> otherStack? 因为所有Stack<T>对象对应的类都是类型为Object的Stack。
        if(this.depth() != otherStack.depth()){
            return false;
        }

        for(int i=0; i<this.depth(); i++){
            if(this.list[i] != otherStack.list[i]){
                return false;
            }
        }

        return true;
    }

    public String toString(){
        String tempString = "TOP\n";
        for(int i=firstFree-1; i>=0; i--){
            tempString += list[i].toString() + "\n";
        }
        return tempString;
    }
}
