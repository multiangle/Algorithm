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

    PQ_ComplHeap(T[] A){
        for (T a:A){
            insert(a);
        }
    }
    PQ_ComplHeap(){
        data = new ArrayList<PQ_Node<T>>() ;
    }

    protected void swapNode(int a, int b){
        PQ_Node temp = data.get(a) ;
        data.set(a, data.get(b)) ;
        data.set(b, temp) ;
    }

    protected void percolateDown(int index){
        while (index*2+1 < data.size()){
            if (index*2+2 < data.size()){ // 如果index有左右节点
                PQ_Node<T> left = data.get(index*2+1) ;
                PQ_Node<T> right = data.get(index*2+2) ;
                if (data.get(index).priority < Math.max(left.priority,right.priority)){ //需要交换
                    if (left.priority > right.priority) { // 与左节点交换
                        swapNode(index, index*2+1);
                        index = index*2+1 ;
                    }else{ // 与右节点交换
                        swapNode(index, index*2+2);
                        index = index*2+2 ;
                    }
                }else{
                    break ;
                }
            }else{ // 如果只有左节点
                if (data.get(index).priority < data.get(index*2+1).priority){
                    swapNode(index,index*2+1);
                    break ;
                }else{
                    break ;
                }

            }
        }
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

    public void insert(T value){
        PQ_Node<T> n = new PQ_Node<T>(value) ;
        data.add(n);
        int i = data.size()-1 ;
        percolateUp(i);
    }

    public void insert(T value, int priority){ //按优先级次序插入词条
        PQ_Node<T> n = new PQ_Node<T>(value, priority) ;
        data.add(n);
        int i = data.size()-1 ;
        percolateUp(i);
    }

    public T getMax(){
        return data.get(0).value ;
    } //取出优先级最高词条

    public T delMax(){
        T max_element = data.get(0).value ;
        data.set(0,data.remove(data.size()-1)) ;
        percolateDown(0);
        return max_element ;
    } //删除优先级最高词条

    public String toString(){
        String ret = "" ;
        for (PQ_Node n:data){
            ret += n.value ;
        }
        return ret ;
    }

    public static void main(String[] args){
        PQ_ComplHeap<Integer> pqc = new PQ_ComplHeap() ;
        for (int i=0;i<10;i++){
            pqc.insert(i,i);
        }
        System.out.println(pqc);
        pqc.delMax() ;
        System.out.println(pqc);
        pqc.delMax() ;
        System.out.println(pqc);
        pqc.delMax() ;
        System.out.println(pqc);
        pqc.delMax() ;
        System.out.println(pqc);
        pqc.delMax() ;
        System.out.println(pqc);
        pqc.delMax() ;
        System.out.println(pqc);

    }
}
