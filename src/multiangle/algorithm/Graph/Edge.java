package multiangle.algorithm.Graph;

/**
 * Created by multiangle on 2016/5/13.
 */
public class Edge<T> {
    public T data ;        // 数据
    public int weight;     //权重
    public Estatus status ; //类型
}

enum Estatus{
    UNDETERMINED, TREE, CROSS, FORWARD, BACKWARD
}
