package sort;

import java.util.Arrays;

public class Selection extends AbstractSort implements Sort {
    /**
     * 一种最简单的排序算法是这样的：首先，找到数组中最小的那个元素，其次，将它和数组的第
     * 一个元素交换位置（如果第一个元素就是最小元素那么它就和自己交换）。再次，在剩下的元素中
     * 找到最小的元素，将它与数组的第二个元素交换位置。如此往复，直到将整个数组排序。这种方法
     * 叫做选择排序，因为它在不断地选择剩余元素之中的最小者
     * 选择排序：
     */

    public Selection(Comparable[] arr) {
        super(arr);
    }

    /**
     * 找出最小的数字放在第一个位置
     */
    @Override
    public void sort() {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (!Utils.less(arr[i], arr[j])) {
                    Utils.swap(arr, i, j);
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[10000];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 1000);
        }
        new Selection(a).sort();
        System.out.println(Arrays.toString(a));
    }
}
