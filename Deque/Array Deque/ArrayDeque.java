import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    T[] arr;
    int nextFirst;
    int nextLast;
    int size;

    public ArrayDeque() {
        arr = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        /*if (arr[nextFirst] !=null){
            nextFirst=goBack(nextFirst);
        }*/ //don't understand

        arr[nextFirst] = x;
        size++;
        nextFirst = goBack(nextFirst);
        ratioChecker();
    }

    @Override
    public void addLast(T x) {
        arr[nextLast] = x;
        size++;
        nextLast = goFront(nextLast); //
        ratioChecker();
    }

    @Override
    public List<T> toList() { //turns the array into a list. order does matter.
        List<T> returnList = new java.util.ArrayList<>();
        for (int i = 0; i < size; i++) {
            returnList.add(get(i)) ;
        }

        /*int listIndex=nextFirst;
        for(int i=0;i<arr.length;i++){
            if (get(listIndex) != null) {
                returnList.add(arr[listIndex]);
            }
            listIndex=goFront(listIndex);
        }*/

        /*for (T t : arr) { //implement  get use for loop
            if(t!=null){
                returnList.add(t);
            }
        }*/
        /*for (int i = 0; i < size; i++) {
                returnList.add(get(i));
        } */

        return returnList;

    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        for (T t : arr) {
            if (t != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    } //returns size of deque

    @Override
    public T get(int index) { //should return null if index is invalid
        if (index > arr.length  || index < 0) {
            return null;
        }
        /*int listIndex=nextFirst;
        int count=0;
        for(int i=0;i<arr.length;i++){
            if(arr[listIndex]!=null){
                if (count==index) {
                    return arr[listIndex];
                }
                listIndex=goFront(listIndex);
                count++;
            }
            listIndex=goFront(listIndex);
        }
        return null;*/
        int indexget = (nextFirst + 1 +  index) % arr.length;
        return arr[indexget];
    }

    @Override
    public T removeFirst() { //Removes and returns the item at the front of the deque.
        if (isEmpty()) {
            return null;
        }
        nextFirst = goFront(nextFirst);
        //removeFirstIndex = goFront(nextFirst);
        T f = arr[nextFirst]; //to return the element
        arr[nextFirst] = null; //remove the element
        size--; //decrease size of deque
        //nextFirst = goFront(nextFirst);// changed index for next call
        ratioChecker(); //to resize
        return f;
    }

    @Override
    public T removeLast() { // Removes and returns the item at the back of the deque.
        if (isEmpty()) {
            return null;
        }
        nextLast = goBack(nextLast);
        T l = arr[nextLast];
        arr[nextLast] = null;
        size--;
        ratioChecker();
        return l;
    }

    public void ratioChecker() {
        if (arr.length > 16 && size < (0.25 * arr.length)) {
            resizing ((int) (size / 0.25));
        }
        if (size == arr.length) {
            resizing((int) (1.5 * arr.length));
        }
    }

    public void resizing(int newLength) {
        T[] newarr = (T[]) new Object[newLength];
        for (int i = 0; i < size ; i++) {
                newarr[i] = get(i);
        }
        /*int listIndex=nextFirst;
        for(int i=0;i<arr.length;i++){
            if (get(listIndex) != null) {
                newarr[i]=(get(listIndex));
            }
            else{
                i--;
            }
            listIndex=goFront(listIndex);
        }*/

        /*for (T t : arr) {
            if (t != null) {
                newarr[index] = (T) t;
                index++;
            }
        }*/
        /*for(int i =0;i<size;i++){
            newarr[i]= get(i);
        }*/
        nextFirst = newLength - 1; //arr.length+1;//earlier was 0
        nextLast = size;
        arr = newarr;
    }

    public int goBack(int index) {
        if (index == 0) {
            return (arr.length - 1);
        } else {
            return (index - 1);
        }
    }

    public int goFront(int index) {
        /*if (index == arr.length - 1) { //this is right!!
            return 0;
        } else {
            return index++;
        }*/
        return ((index + 1) % arr.length);
    }

}






