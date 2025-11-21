package sortings;

import data.Node;
import java.util.List;

public abstract class AbstractSort {
    protected List<Node> data;
    protected long comparisons;
    private long swaps;
    
    private SortListener listener;
    private final int DELAY = 50;		// milliseconds

    protected AbstractSort(List<Node> listToSort) {
        data = listToSort;
    }

    public void start() {
        if (data != null) {
            comparisons = 0;
            swaps = 0;
            sortAlgorithm();
        }
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }
    
    public void setListener(SortListener listener) {
        this.listener = listener;
    }

    protected void swap(List<Node> list, int i, int j) {
        Node temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
        swaps++;
        
        if (listener != null) {
        	listener.onDataChanged();
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void sortAlgorithm();
}
