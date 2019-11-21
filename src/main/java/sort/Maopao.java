package sort;

public class Maopao extends AbstractSort implements Sort {
    public Maopao(Comparable[] arr) {
        super(arr);
    }

    public void sort() {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (Utils.less(arr[j+1], arr
                        [j])) {
                    Utils.swap(arr, j+1, j );
                }

            }
        }

    }
}
