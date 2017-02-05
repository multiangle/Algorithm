package multiangle.algorithm.BTree;

/**
 * Created by multiangle on 2016/5/29.
 * B树中，每个节点至多m分支，m-1个关键码
 * 非根节点至少ceil(m>>1)分支，ceil(m>>1)-1关键码
 * 根节点至少2个分支，1个关键码
 */
public class BTree<T extends  Comparable<T>> {
    protected int _size,_order ;  // 关键码总数，阶次
    protected BTNode<T> _root,_hot ;
    protected  int _m ; // 分支数目

    protected void solveOverflow( BTNode<T> node ){
        while (node.key.size()>_m-1){
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

    protected void solveUnderflow( BTNode<T> node ){
        // 如果节点规模符合要求，则数目都不做
        if (node.key.size()>=Math.ceil((double)_m/2)-1 ) return ;
        if (node.parent==null && node.key.size()>=0) return ;
        if (node==null) return ;

        // 处理普通节点的情况
        BTNode p = node.parent ;
        int node_index = p.child.indexOf(node) ;
        if (node_index>0 && //当左兄弟中有节点可以借用时
                ((BTNode)p.child.get(node_index-1)).key.size()> (Math.ceil((double)_m/2)-1)){
            BTNode left = (BTNode)p.child.get(node_index-1) ;

            node.key.add(0, (T) p.key.get(node_index - 1)); // 将中间节点位置移入右节点
            p.key.set(node_index - 1, left.key.remove(left.key.size() - 1)) ; // 左节点最后移入中间节点,去掉左节点结尾，即从左节点借一个去当key

            node.child.add(0,(BTNode)left.child.remove(left.child.size()-1)) ;
            if (node.child.get(0)!=null) node.child.get(0).parent = node ;
        }else if ((node_index<p.child.size()-1) && // 当有右兄弟可以借用时
                ((BTNode)p.child.get(node_index+1)).key.size()> (Math.ceil((double)_m/2)-1)){
            BTNode right = (BTNode)p.child.get(node_index+1) ;

            node.key.add((T)p.key.get(node_index));
            p.key.set(node_index,right.key.remove(0)) ;

            node.child.add((BTNode)right.child.remove(0)) ;
            if (node.child.lastElement()!=null) node.child.lastElement().parent = node ;
        }else{ // 左右节点均无法借用时，需要合并节点
            BTNode merged = new BTNode() ;
            if (node_index>0){ // 如果有左兄弟，则与左兄弟合并
                BTNode left = (BTNode)p.child.get(node_index-1) ;

                merged.child.clear();
                // 处理左兄弟
                merged.child.add(left.child.get(0));
                if (merged.child.get(0)!=null) ((BTNode)merged.child.get(0)).parent = merged ;
                for (int i=0;i<left.key.size();i++){
                    merged.key.add(left.key.get(i));
                    BTNode temp = (BTNode)left.child.get(i+1) ;
                    merged.child.add(temp);
                    if (temp!=null) temp.parent = merged ;
                }
                // 处理中间节点
                merged.key.add(p.key.get(node_index-1));
                // 处理右兄弟
                merged.child.add(node.child.get(0));
                if (merged.child.lastElement()!=null) ((BTNode)merged.child.lastElement()).parent = merged ;
                for (int i=0;i<node.key.size();i++){
                    merged.key.add(node.key.get(i));
                    BTNode temp = (BTNode)node.child.get(i+1) ;
                    merged.child.add(temp);
                    if (temp!=null) temp.parent = merged ;
                }
                p.key.remove(node_index-1) ;
                p.child.remove(node_index) ;
                p.child.set(node_index-1,merged) ;
                merged.parent = p ;
            }else{ //如果没有做兄弟，则与右兄弟合并
                BTNode right = (BTNode)p.child.get(node_index+1) ;

                merged.child.clear();
                // 处理左兄弟
                merged.child.add(node.child.get(0));
                if (merged.child.get(0)!=null)((BTNode)merged.child.get(0)).parent = merged ;
                for (int i=0;i<node.key.size();i++){
                    merged.key.add(node.key.get(i));
                    BTNode temp = (BTNode)node.child.get(i+1) ;
                    merged.child.add(temp);
                    if (temp!=null) temp.parent = merged ;
                }
                // 处理中间节点
                merged.key.add(p.key.get(node_index));
                // 处理右兄弟
                merged.child.add(right.child.get(0));
                if (merged.child.lastElement()!=null) ((BTNode)merged.child.lastElement()).parent = merged ;
                for (int i=0;i<right.key.size();i++){
                    merged.key.add(right.key.get(i));
                    BTNode temp = (BTNode)right.child.get(i+1) ;
                    merged.child.add(temp);
                    if (temp!=null) temp.parent = merged ;
                }
                p.key.remove(node_index) ;
                p.child.remove(node_index+1) ;
                p.child.set(node_index,merged) ;
                merged.parent = p ;
            }
            if (p.key.size()==0){ // if p 此时为空
                if (p.parent==null){ // if p 为 root, 则转移root
                    merged.parent = null ;
                    _root = merged ;
                    return ;
                }else{
                    System.out.println("unknown error");
                }

            }
            solveUnderflow(p); //合并以后需要处理上一级的问题
        }

    }

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

    public boolean remove( T target){
        BTNode v = search(target) ;
        if (v==null) return false ;
        int index = v.searchKey(target) ;
        if (v.child.get(0)!=null){
            // 如果v不是叶节点，则找到直接后继
            BTNode u = (BTNode)v.child.get(index+1) ;
            while (u.child.get(0)!=null){
                u = (BTNode) u.child.get(0) ;
            }
            v.key.set(index,u.key.get(0)) ; // 将 直接后继 与 该点进行对换
            v = u ;
            index = 0;
        }
        v.key.remove(index) ; // 此时能够保证待删除的节点一定处于叶节点
        v.child.remove(index+1) ;
        _size-- ;
        solveUnderflow(v);
        return true ;
    }

    public static void main(String[] args){
        BTree bt = new BTree<Integer>(5) ;
        bt.insert(0) ;
        bt.insert(1) ;
        bt.insert(2) ;
        bt.insert(3) ;
        bt.insert(4) ;
        bt.insert(5) ;
        bt.insert(6) ;
        bt.insert(7);
        bt.insert(8);
        bt.insert(9) ;
        System.out.println(bt._root);
        System.out.println(bt._root.child);
        System.out.println(((BTNode) bt._root.child.get(1)).child);
        System.out.println("--------------------------------------");
        bt.remove(9) ;
        bt.remove(8) ;
        bt.remove(7) ;
        bt.remove(6) ;
        bt.remove(5) ;
        bt.remove(4) ;
        bt.remove(3) ;
        bt.remove(2) ;
        bt.remove(1) ;
        bt.remove(0) ;
        System.out.println(bt._root);
        System.out.println(bt._root.child);
//        System.out.println(((BTNode) bt._root.child.get(1)).child);
        System.out.println("--------------------------------------");

    }
}
