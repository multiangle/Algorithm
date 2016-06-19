package multiangle.algorithm.PriorityQueue;

/**
 * Created by multiangle on 2016/6/18.
 */
public interface PQ_Interface<T> {

    void insert(PQ_Node<T> v) ; //按优先级次序插入词条

    T getMax() ; //取出优先级最高词条

    T delMax() ; //删除优先级最高词条
}
