import java.util.Arrays;

public class Sorting {
    public static void main(String[] args) {
        char[] a = {'C', 'B', 'D', 'A', 'E', 'H', 'G', 'F'};
        insertionSort(a);
        System.out.println(Arrays.toString(a)); 
    }
        
    public static void insertionSort(char[] a){
        int n = a.length; 
        for (int j = 1; j < n; j++) {
            char cur = a[j]; 
            int i = j - 1; 
             while (i >= 0 && a[i] > cur) {
                a[i + 1] = a[i];
                i = i - 1;
            }
             a[i + 1] = cur;
        }
    }
}