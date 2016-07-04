package multiangle.algorithm;

import java.util.ArrayList;

/**
 * Created by multiangle on 2016/7/3.
 */
public class LinearSelect {
    public static Integer select(Integer[] data, int k){
        int Q = 5 ;
        int n = data.length ;
        int cut_num  = (int)Math.ceil((double)n/Q) ;
        Integer[][] cutted_data = new Integer[cut_num][] ;
        for (int i=0; i<n; i++){
            if (i%Q==0){
                int len = Math.min(Q,n-i) ;
                cutted_data[i/Q] = new Integer[len] ;
            }
            cutted_data[i/Q][i%Q] = data[i] ;
        }
        Integer[] mid = new Integer[cut_num] ;
        for (int i=0; i<cut_num; i++){
//            System.out.println(cutted_data[i].length);
            Sort.quick_sort(cutted_data[i]);
            mid[i] = cutted_data[i][cutted_data[i].length>>1] ;
        }
        Sort.quick_sort(mid);
        Integer mid2 = mid[cut_num>>1] ;
        ArrayList<Integer> L,M,R ;
        L = new ArrayList<Integer>() ;
        M = new ArrayList<Integer>() ;
        R = new ArrayList<Integer>() ;
        for (int i=0; i<n; i++){
            if (data[i]<mid2) L.add(data[i]);
            else if (data[i]>mid2) R.add(data[i]);
            else M.add(data[i]);
        }
        if (k<L.size()) return select(L.toArray(new Integer[L.size()]), k) ;
        else if (k>=L.size()+M.size()) return select(R.toArray(new Integer[R.size()]),k-L.size()-M.size()) ;
        else return M.get(0) ;
    }

    public static void main(String[] args){
        Integer[]  d = {7,6,5,4,3,2,1} ;
        System.out.println(LinearSelect.select(d,1));
    }
}
