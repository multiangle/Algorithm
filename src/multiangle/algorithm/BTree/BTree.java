package multiangle.algorithm.BTree;

/**
 * Created by multiangle on 2016/5/29.
 */
public class BTree<T extends  Comparable<T>> {
    protected int _size,_order ;  // 关键码总数，阶次
    protected BTNode<T> _root,_hot ;
    protected  int _m ; // 关键码数目

    protected void solveOverflow( BTNode<T> node ){
        while (node.key.size()>_m){
            int mid = _m>>1 ;

            BTNode<T> left = new BTNode<T>() ;
            BTNode<T> right = new BTNode<T>() ;

            // 左右子树建立与原各子树的联系（包括向下和向上的）
            left.child.set(0,node.child.get(0)) ;
            if (node.child.get(0)!=null) node.child.get(0).parent = left ;
            right.child.set(0,node.child.get(mid+1)) ;
            if (node.child.get(mid+1)!=null) node.child.get(mid+1).parent = right ;
            for (int i=0;i<node.key.size();i++){
                if (i<mid) {
                    left.key.add(node.key.get(i));
                    BTNode temp = node.child.get(i+1) ;
                    left.child.add(temp) ;
                    if (temp!=null) temp.parent = left ;
                }
                else if (i>mid){
                    right.key.add(node.key.get(i));
                    BTNode temp = node.child.get(i+1) ;
                    right.child.add(temp) ;
                    if (temp!=null) temp.parent = right ;
                }
            }

            if (node.parent==null) {
                // node是根节点
                BTNode<T> mid_node = new BTNode<T>(node.key.get(mid)) ;
                mid_node.child.set(0,left) ;
                mid_node.child.set(1,right) ;
                left.parent = right.parent = mid_node ;
                _root = mid_node ;
                node.clean();
                node = null ;
                break ;
            }else{
                // node就是一个普通节点
                BTNode p = node.parent ;
                int index = p.searchKey(node.key.get(mid)) ;
                p.key.add(index+1,node.key.get(mid));
                p.child.set(index+1,right) ; //先将原i+1 替换为right
                p.child.add(index+1,left); // 再插入left, right后推成为i+2
                left.parent = right.parent = p ;
                node.clean();
                node = p ;
            }
        }
    }

    protected void solveUnderflow( BTNode<T> input ){}

    public BTree(int m){
        _size = 0 ;
        _root = new BTNode<T>() ;
        _hot = null ;
        _m = m ;
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
        _hot.child.add(index+1, null) ; // 创建一个空子树指针
        _size++ ;
        solveOverflow(_hot); // 如果发生上溢，则分裂。
        return true ;
    }
    public boolean remove( T output){ return true ;}

    public static void main(String[] args){
        BTree bt = new BTree<Integer>(3) ;
        bt.insert(0) ;
        bt.insert(1) ;
        bt.insert(2) ;
        bt.insert(3) ;
        bt.insert(4) ;
        bt.insert(5) ;
        bt.insert(6) ;
        bt.insert(7) ;
        bt.insert(8) ;
        bt.insert(9) ;
        System.out.println(bt._root);
        System.out.println(bt._root.child);
        System.out.println( ((BTNode)bt._root.child.get(1)).child );
    }
}
