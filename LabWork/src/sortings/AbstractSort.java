package sortings;

import data.Node;
import java.util.List;

public abstract class AbstractSort {
    private final int DELAY = 25;		// milliseconds
    
    private SwapListener listener;
    protected List<Node> data;
    protected long comparisons;
    private long swaps;

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
    
    public void setSwapListener(SwapListener listener) {
        this.listener = listener;
    }

    protected void swap(List<Node> list, int i, int j) {
        Node temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
        swaps++;
        
        if (listener != null) {
        	listener.onNodeSwap();
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void sortAlgorithm();
}
