package multiangle.algorithm;

import java.util.ArrayList;

/**
 * Created by multiangle on 2016/4/13.
 */
public class Stack<T> {

    private ArrayList<T> data ;
    private int size ;

    public Stack(){
        data = new ArrayList<T>() ;
        size = 0 ;
    }

    public boolean push(T input){
        try{
            data.add(input) ;
            size++ ;
            return true ;
        }catch (Error e){
            return false ;
        }
    }

    public T pop(){
        if (size==0) return null ;
        return data.remove(--size) ;
    }

    public T top(){
        if (size==0) return null ;
        return data.get(size-1) ;
    }

    public int size(){
        return size ;
    }

    public boolean isEmpty(){
        return data.isEmpty() ;
    }

    public String toString(){
        return this.data.toString() ;
    }
}