package multiangle.algorithm.Graph;

/**
 * Created by multiangle on 2016/5/13.
 */
public class Vertex<T> {
    public T data ;                       // 数据
    public int inDegree,outDegree ;    //出入度数
    public VStatus status ;              // 如下三种状态
    public int dTime,fTime ;            // 时间标签
    public int parent ;                 // 遍历树种的父节点
    public int priority ;               // 遍历树中优先级
}

enum VStatus{
    UNDISCOVERED,DISCOVERED,VISITED
}
