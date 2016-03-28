/**
 * Created by multiangle on 2016/3/22.
 */
public class lcs {
    public static void main(String [] args){
        String test_a="educational" ;
        String test_b="advantage" ;
        String ret=lcs_iterate(test_a,test_b) ;
        System.out.println(ret);
    }

    public static String lcs_iterate(String in_a, String in_b){
        if (in_a.equals("")||in_b.equals("")) return "" ;
        int len_a=in_a.length() ;
        int len_b=in_b.length() ;
        if (in_a.charAt(len_a-1)==in_b.charAt(len_b-1)){
            return lcs_iterate(in_a.substring(0,len_a-1),in_b.substring(0,len_b-1))+in_a.charAt(len_a-1) ;
        }
        else{
            String ret_a = lcs_iterate(in_a.substring(0,len_a-1),in_b) ;
            String ret_b = lcs_iterate(in_a,in_b.substring(0,len_b-1)) ;
            if (ret_a.length() > ret_b.length()) return ret_a ;
            else return ret_b ;
        }
    }
}
