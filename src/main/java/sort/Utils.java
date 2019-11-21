package sort;

import com.sun.istack.internal.NotNull;

public final class Utils {
    /**
     * @param a 第一个数
     * @param b 第二个数
     * @return 比较两个数的大小, 如果a<b 返回true
     * 否则返回false
     */
    public static boolean less(@NotNull Comparable a,@NotNull Comparable b) {
        return a.compareTo(b) < 0;
    }

    /**
     * @param a 交换的数组
     * @param i 索引1
     * @param j 索引2
     *          功能：交换数组中两个值
     */
    public static void swap(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
