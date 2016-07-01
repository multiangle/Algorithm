package multiangle.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * Created by multiangle on 2016/4/3.
 */
public class Sort {

    // Bubble Sort
    // the complexity of time is O(n^2)
    public static <T extends  Comparable<? super T>>
    void bubble_sort( T[] inputArray ){
        int low = 0 ;
        int high = inputArray.length-1 ;
        while ( low < high ){
            int index = low ;
            int last_change_index = low ;
            // inner loop
            while (index < high){
                if (inputArray[index].compareTo(inputArray[index+1])>0){
                    T temp = inputArray[index] ;
                    inputArray[index] = inputArray[index+1] ;
                    inputArray[index+1] = temp ;
                    last_change_index = index ;
                }
                index++ ;
            }
            System.out.println(last_change_index);
            System.out.println(Arrays.toString(inputArray));
            high = last_change_index ;
        }
    }

    // Merge Sort
    // the level of time complexity is O(n log n)
    public static <T extends Comparable<? super T>>
    void merge_sort( T[] inputArray, int low, int high){
        // sort the sequence in [low,high)
        if ((high - low) > 1){
            int mid = (low + high) >> 1 ;
            merge_sort(inputArray, low, mid);
            merge_sort(inputArray,mid,high);

            // create sub list
            T[] temp = Arrays.copyOfRange(inputArray,low,high) ;
            int index = low ;
            int left_index = 0 ;
            int left_limit = mid - low ;
            int right_index = mid-low ;
            int right_limit = high - low ;

            // merge two branches
            while ((left_index<left_limit)&&(right_index<right_limit)){
                if (temp[left_index].compareTo(temp[right_index])<=0){
                    inputArray[index++] = temp[left_index++] ;
                }else{
                    inputArray[index++] = temp [right_index++] ;
                }
            }

            // deal the left single branch
            if (left_index == left_limit){
                while(right_index<right_limit) inputArray[index++] = temp[right_index++] ;
            }else{
                while(left_index<left_limit) inputArray[index++] = temp[left_index++] ;
            }
        }
        System.out.println(Arrays.toString(inputArray));
    }

    public static <T extends Comparable<? super T>>
    void merge_sort(T[] inputArray){
        merge_sort(inputArray,0,inputArray.length);
    }
    public static <T extends  Comparable<? super T>>
    void quick_sort(T[] inputArray, int low, int high){ // 处理[low,high]的序列
        int low_range = low, high_range = high ;
        int type = -1 ;
        int tag_index = low++ ;
        T tag = inputArray[tag_index] ;
        while(low<=high){
            if (type==-1){
                while (inputArray[high--].compareTo(tag)>=0){if (low>high) break ;}
                if (inputArray[high+1].compareTo(tag)<0){
                    type = 1 ;
                    inputArray[tag_index] = inputArray[high+1] ;
                    tag_index = high+1 ;
                }
            }else{
                while (inputArray[low++].compareTo(tag)<=0){if (low>high) break ;}
                if (inputArray[low-1].compareTo(tag)>0){
                    type = -1 ;
                    inputArray[tag_index] = inputArray[low-1] ;
                    tag_index = low-1 ;
                }
            }

        }
        inputArray[tag_index] = tag ;
        if (low_range<tag_index-1)
            quick_sort(inputArray,low_range,tag_index-1);
        if (tag_index+1<high_range)
            quick_sort(inputArray,tag_index+1,high_range);
    }
    public static <T extends Comparable<? super T>>
    void quick_sort(T[] inputArray){
        quick_sort(inputArray,0,inputArray.length-1);
    }
    public static <T extends Comparable<? super T>>
    void quick_sort2(T[] inputArray){
        quick_sort2(inputArray,0,inputArray.length);
    }
    public static <T extends Comparable<? super T>>
    void quick_sort2(T[] inputArray, int low, int high){ //[low,high)
        T tag = inputArray[low] ;
        int mid = low ;
        for(int k=low+1; k<high ;k++){
            if (inputArray[k].compareTo(tag)<0){
                T temp = inputArray[k] ;
                inputArray[k] = inputArray[++mid] ;
                inputArray[mid] = temp ;
            }
        }
        T temp = inputArray[mid] ;
        inputArray[mid] = tag ;
        inputArray[low] = temp ;
        if (low<mid)
            quick_sort2(inputArray,low,mid);
        if (mid+1<high)
            quick_sort2(inputArray,mid+1,high);
    }

    public static void main(String[] args){
        Double[] data = {1.0,2.0,3.0,4.0,5.5,5.1,6.6,4.2,3.2,1.5,3.8,3.9,4.5,3.8} ;
        Integer[] data2 = {4,1,2,5,6} ;
        Sort.quick_sort2(data);
        for (Double i:data){
            System.out.println(i);
        }
//        System.out.println(Arrays.toString(data));

    }


}
