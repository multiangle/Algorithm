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
        while ( low <= high ) {
            int target = ( low + high ) / 2 ;
            if ( this.data[target] == element ){
                return target ;
            }
            else if ( element < this.data[target] ) {
                high = target - 1 ;
            }
            else{
                low = target + 1 ;
            }
        }
        return -1 ;
    }

    public static void main(String[] args){
        int[] list = {1,2,3,4,5} ;
        ListInt x = new ListInt(list) ;
        int index = x.binary_search(6) ;
        System.out.println(index);
    }
}
