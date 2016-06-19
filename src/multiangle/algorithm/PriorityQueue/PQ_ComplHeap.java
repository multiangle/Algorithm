package multiangle.algorithm.PriorityQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

/**
 * Created by multiangle on 2016/6/19.
 */
public class PQ_ComplHeap<T> implements PQ_Interface<T>{ // 完全二叉堆
    protected ArrayList<PQ_Node<T>> data ;

    protected void percolateDown(int n, int i){

    }

    protected void percolateUp(int index){
        while (index>0){
            int p_index = (index-1)>>1 ;
            PQ_Node<T> p = data.get(p_index) ;
            PQ_Node<T> n = data.get(index) ;
            if (n.priority > p.priority){
                data.set(p_index, n) ;
                data.set(index, p) ;
                index = p_index ;
            }else{
                break ;
            }
        }
    }

    protected void heapify(int n){

    }

    PQ_ComplHeap(T[] A){
        for (T a:A){
            PQ_Node<T> n = new PQ_Node<T>(a) ;
            insert(n);
        }
    }
    PQ_ComplHeap(){
        data = new ArrayList<PQ_Node<T>>() ;
    }

    public void insert(PQ_Node<T> node){ //按优先级次序插入词条
        data.add(node);
        int i = data.size()-1 ;
        percolateUp(i) ;
    }

    public T getMax(){
        return null ;
    } //取出优先级最高词条

    public T delMax(){
        return null ;
    } //删除优先级最高词条

    public static void main(String[] args){

    }
}
