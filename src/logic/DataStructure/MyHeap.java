package logic.DataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyHeap<E extends Comparable<E>> {
    private List<E> list = new ArrayList<>();

    public MyHeap() {
        this.list.add(null);
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
}
