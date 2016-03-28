package multiangle.algorithm;

/**
 * Created by multiangle on 2016/3/27.
 */
public class ListInt {

    private int[] data ;

    ListInt(){
        this.data = null ;
    }

    ListInt(int[] data){
        this.data = data ;
    }

    private int binary_search(int element){

        int low = 0 ;
        int high = this.data.length-1 ;
        while ( low < high ) {
            int target = ( low + high ) >> 1 ;
            if ( element < this.data[target] ){
                high = target - 1 ;
            }
            else if ( this.data[target] < element ) {
                low = target + 1 ;
            }
            else{
                return target ;
            }
        }
        return -1 ;
    }

    private int binary_search_2(int element){

        int low = 0 ;
        int high = this.data.length  ;
        while ( 1 < high-low){
            int target = ( low + high ) >> 1 ;
            if (element<this.data[target]) high = target ;
            else low = target ;
        }
//        return element == this.data[low]?low:-1 ;
        return low ;
    }

    public static void main(String[] args){
        int[] list = {1,2,3,4,4,4,4,5,7} ;
        ListInt x = new ListInt(list) ;
        int index = x.binary_search_2(7) ;
        System.out.println(index);
    }
}
