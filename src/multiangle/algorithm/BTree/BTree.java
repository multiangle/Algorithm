package multiangle.algorithm.BTree;

/**
 * Created by multiangle on 2016/5/29.
 */
public class BTree<T> {
    protected int _size,_order ;  // 关键码总数，阶次
    protected BTNode<T> _root,_hot ;

    protected void solveOverflow( BTNode<T> input ){}
    protected void solveUnderflow( BTNode<T> input ){}

    public BTNode<T> search( T target ){ return null;}
    public boolean insert( T input ){ return  true ;}
    public boolean remove( T output){ return true ;}

}
