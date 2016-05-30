package multiangle.algorithm.BTree;

/**
 * Created by multiangle on 2016/5/29.
 */
public class BTree<T extends  Comparable<T>> {
    protected int _size,_order ;  // 关键码总数，阶次
    protected BTNode<T> _root,_hot ;

    protected void solveOverflow( BTNode<T> input ){}
    protected void solveUnderflow( BTNode<T> input ){}

    public BTree(){
        _size = 0 ;
        _root = new BTNode<T>() ;
        _hot = null ;
    }

    public BTNode<T> search( T target ){
        _hot = null ;
        BTNode node = _root ;
        if (_root == null) return null ;
        while(node != null){
            int index = node.searchKey(target) ;
            if (index>=0 && node.key.get(index).equals(target)) return node ;
            _hot = node ;
            node = (BTNode)node.child.get(index+1) ;
        }
        return null ;
    }
    public boolean insert( T input ){
        BTNode node = search(input) ;
        if (node!=null) return false ;
        int index = _hot.searchKey(input) ; //在_hot中确定插入位置
        _hot.key.add(index+1, input);  // 将新关键码插至对应位置
        _hot.child.add(index+2, null) ; // 创建一个空子树指针
        _size++ ;
        solveOverflow(_hot); // 如果发生上溢，则分裂。
        return true ;
    }
    public boolean remove( T output){ return true ;}

}
