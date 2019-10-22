package sort;


public class InsertSort extends AbstractSort implements Sort {
    public InsertSort(Comparable[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0 && Utils.less(arr[j], arr[j - 1]); j--) {
                Utils.swap(arr, j, j - 1);
            }
        }
    }
}
