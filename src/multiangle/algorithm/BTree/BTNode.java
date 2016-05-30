package multiangle.algorithm.BTree;

import java.util.Comparator;
import java.util.Vector;

/**
 * Created by multiangle on 2016/5/29.
 */
public class BTNode<T extends Comparable<T>> {
    protected BTNode<T> parent ;
    protected Vector<T> key ;
    protected Vector<BTNode> child ;


    public static void main(String[] args){
        Vector<Integer> a = new Vector() ;
        a.insertElementAt(1,0);
    }

    public BTNode(){
        parent = null ;
        key = new Vector<T>() ;
        child = new Vector<BTNode>() ;
        child.add(0, null);
    }

    public BTNode(T value){
        parent = null ;
        key = new Vector<T>() ;
        key.add(0,value);
        child = new Vector<BTNode>() ;
        child.add(0,null) ;
        child.add(1,null) ;
    }

    /**
     * 如果搜到值，则返回相应的值所在的数组位置。否则返回不大于目标的节点位置。
     * 特别的，有可能返回-1值，表示目标小于数组中所有值。
     * @param target
     * @return
     */
    public int searchKey( T target ){
        for (int i=0 ; i<key.size(); i++){
            if (key.get(i).compareTo(target) == 0) return i ;
            else if (key.get(i).compareTo(target) > 0) return i-1 ;
        }
        return key.size()-1 ;

    }

}
