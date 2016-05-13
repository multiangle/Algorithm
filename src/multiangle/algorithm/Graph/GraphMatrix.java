package multiangle.algorithm.Graph;

import java.util.ArrayList;

/**
 * Created by multiangle on 2016/5/13.
 */
public class GraphMatrix<TV,TE> {
    private ArrayList<Vertex<TV>> V ;
    private ArrayList<ArrayList<Edge<TE>>> E ;

    GraphMatrix(){
        V = null ;
        E = null ;
    }
    public TV getData(int i){return V.get(i).data ;}
    public int getInDegree(int i){return V.get(i).inDegree;}
    public int getOutDegree(int i){return V.get(i).outDegree;}
    public VStatus getVertexStatus(int i){return V.get(i).status ;}
    public int getDTime(int i){return V.get(i).dTime ;}
    public int getFTime(int i){return V.get(i).fTime ;}
    public int getParent(int i){return V.get(i).parent ;}
    public int getPriority(int i){return V.get(i).priority ;}
}
