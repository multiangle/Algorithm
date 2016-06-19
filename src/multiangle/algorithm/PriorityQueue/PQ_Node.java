package multiangle.algorithm.PriorityQueue;

import java.util.Comparator;

/**
 * Created by multiangle on 2016/6/19.
 */
public class PQ_Node<T> {
    public T value ;
    public int priority ;

    PQ_Node(T value){
        this.value = value ;
        this.priority = 1 ;
    }
    PQ_Node(T value, int priority){
        this.value = value ;
        this.priority = priority ;
    }
}
