package multiangle.algorithm.BTree;

import java.util.Vector;

/**
 * Created by multiangle on 2016/5/29.
 */
public class BTNode<T> {
    private BTNode<T> parent ;
    private Vector<T> key ;
    private Vector<BTNode> child ;


    public static void main(String[] args){
        Vector<Integer> a = new Vector() ;
        a.insertElementAt(1,0);
    }
}
