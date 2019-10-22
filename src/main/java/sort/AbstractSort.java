package sort;

public class AbstractSort implements Sort {

    public Comparable[] arr;

    public AbstractSort(Comparable[] arr) {
        this.arr = arr;
        sort();
    }

    @Override
    public void sort() {

    }
}
