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
        Integer[][] cutted_data = new Integer[cut_num][Q] ;
        for (int i=0; i<n; i++){
            cutted_data[i/Q][i%Q] = data[i] ;
        }
        Integer[] mid = new Integer[cut_num] ;
        for (int i=0; i<cut_num; i++){
            System.out.println(cutted_data[i].length);
            Sort.quick_sort(cutted_data[i]);
            mid[i] = cutted_data[i][Q>>1] ;
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
            else M.add(data[data[i]]);
        }
        if (k<L.size()) return select((Integer[])L.toArray(),k) ;
        else if (k>=L.size()+M.size()) return select((Integer[])R.toArray(),k-L.size()-M.size()) ;
        else return M.get(0) ;
    }

    public static void main(String[] args){
        Integer[]  d = {1,2,3,4,5,6,6,7} ;
        System.out.println(LinearSelect.select(d,1));
    }
}
