package logic.DataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyHeap<E extends Comparable<E>> {
    private List<E> list = new ArrayList<>();

    public MyHeap(){
        this.list.add(null);
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }


    public void insert(E element){
        list.add(element);

        int currentPosition = list.size() - 1;
        int nextPosition = currentPosition/2;
        while((currentPosition > 1) && (list.get(currentPosition).compareTo(list.get(nextPosition)) > 0)){
            Collections.swap(list,currentPosition,nextPosition);
            currentPosition = nextPosition;
            nextPosition = currentPosition/2;
        }
    }

    public E remove(){

        E returnValue = list.get(1);

        list.set(1,list.get(list.size() - 1));
        list.remove(list.size() - 1);

        int currentIndex = 1;
        int nextIndex = 2;

        while(nextIndex < list.size()){

            boolean isNext = false;
            if((nextIndex + 1 >= list.size()) || (list.get(nextIndex).compareTo(list.get(nextIndex + 1)) > 0)){
                isNext = true;
            }

            if(isNext){
                if(list.get(currentIndex).compareTo(list.get(nextIndex)) >= 0){
                    break;
                }
                Collections.swap(list,currentIndex,nextIndex);
            }else{
                if(list.get(currentIndex).compareTo(list.get(nextIndex + 1)) >= 0){
                    break;
                }
                Collections.swap(list,currentIndex,nextIndex + 1);
            }

            currentIndex = nextIndex;
            nextIndex = currentIndex * 2;
        }

        return returnValue;
    }

    public int size(){
        return list.size() - 1;
    }

    public E getItem(int i){
        return list.get(i+1);
    }

    public E remove(int i){
        E value = list.get(i+1);
        list.remove(i+1);
        List<E> newList = new ArrayList<>();
        newList.addAll(list);
        list.clear();
        list.add(null);
        for(int j=1; j<newList.size(); j++){
            this.insert(newList.get(j));
        }
        return value;
    }

    // get与getItem的区别是get或得顺序输出，用于将heap输出到例如ObservableList中。getItem直接或得数组中某位置的
    // 元素值，不是严格排序的，可用于heap的遍历，内存开销小于get
    public E get(int i){
        //构建一个替代堆
        MyHeap<E> temp = new MyHeap<>();
        for(int k=1; k<list.size(); k++){
            temp.insert(list.get(k));
        }

        for(int j=0; j<i; j++){
            temp.remove();
        }
        return temp.remove();
    }

}
