package YOURPACKAGEHERE;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class MyList<T> extends AbstractList<T> {
    private List<T> list;

    MyList(List<T> list)
    {
        this.list = list;
    }
    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public T set(int index, T element) {
        T oldElement = list.get(index);
        list.set(index, element);
        return oldElement;
    }

    @Override
    public boolean add(T element) {
        List<T> newList = new ArrayList<T>();
        newList.addAll(list);
        newList.add(element);
        list = newList;
        return true;
    }

    @Override
    public void add(int index, T element) {
        List<T> newList = new ArrayList<T>();
        newList.addAll(list);
        newList.add(index, element);
        list = newList;
    }

    @Override
    public T remove(int index) {

        T removedElement = list.get(index);
        List<T> newList = new ArrayList<T>();
        for (int i = 0; i < list.size(); i++){
            if (i != index)
                newList.add(list.get(i));
        }

        list = newList;
        return removedElement;
    }
}
