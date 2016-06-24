package multiangle.algorithm.ADT;

/**
 * Created by multiangle on 2016/6/23.
 */
public class ADT {
    public static int KMP(String sT, String sP){ // T,P分别表示文本和待搜索子串
        char[] T = sT.toCharArray() ;
        char[] P = sP.toCharArray() ;
        int[] next = buildNext(P) ;
        int n = T.length ; int m = P.length ;
        int i=0, j = 0 ;
        while (i<n && j<m){
            if (0>j || T[i]==P[j]){
                i++ ;
                j++ ;
            }else{
                j = next[j] ;
            }
        }
        return i-j ;
    }

    private static int[] buildNext(char[] P){
        return null ;
}
}
