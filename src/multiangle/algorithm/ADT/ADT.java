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
            if (j<0 || T[i]==P[j]){
                i++ ;
                j++ ;
            }else{
                j = next[j] ;
            }
        }
        return i-j ;
    }

    private static int[] buildNext(char[] P){ // 在P[0,j)中，最大自匹配的真前缀和真后缀的长度
        // next[j+1]应该 = 1+next[j] | 1+next[next[j]] 。。。中选择
        int[] next = new int[P.length] ;
        next[0] = -1 ;
        for (int i=1; i<next.length; i++){
            int index=next[i-1] ;
            while (index > -1){
                if (P[i-1] == P[index]){
                    break ;
                }else{
                    index = next[index] ;
                }
            }
            index += 1 ;
            next[i] = index ;
        }
        return next ;
    }

    public static void main(String[] args){
        int[] res = ADT.buildNext( "000100001".toCharArray() );
        for (int r:res){
            System.out.print(Integer.toString(r)+"\t");
        }
    }
}
