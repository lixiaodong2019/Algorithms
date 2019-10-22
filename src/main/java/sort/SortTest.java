package sort;

import java.util.Arrays;

public class SortTest {
    public static void main(String[] args) {
        int N = 10000;
        Comparable[] arr = new Integer[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * N);
        }
        new Maopao(arr);
        System.out.println(Arrays.toString(arr));
    }
}
