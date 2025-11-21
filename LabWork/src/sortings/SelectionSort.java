package sortings;

import data.Node;
import java.util.List;

public class SelectionSort extends AbstractSort{

    public SelectionSort(List<Node> listToSort) {
        super(listToSort);
    }

    @Override
    protected void sortAlgorithm() {
        Node smallest;
        int smallestAt;
        int length = super.data.size();

        for (int i = 0; i < length - 1; i++) {
            smallest = super.data.get(i);
            smallestAt = i;
            for (int j = i + 1; j < length; j++) {
                Node next = super.data.get(j);
                if (next.getKey() < smallest.getKey()) {
                    smallest = next;
                    smallestAt = j;
                }
                super.comparisons += 1;
            }
            swap(super.data, i, smallestAt);
        }
    }
}
