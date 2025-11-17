package util;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {
	private List<Node> data;
	private long comparisons;
	private long swaps;
	 
	public QuickSort(List<Node> listToSort) {
        this.data = listToSort;
        this.comparisons = 0;
        this.swaps = 0;
    }
	
	public void start() {
        if (data == null || data.size() < 2) {
            return; // Already sorted
        }
        // Call the 3-parameter helper method
        quickSort(data, 0, data.size() - 1);
    }
	 
	public long getComparisons() {
		return comparisons;
	}

	public long getSwaps() {
		return swaps;
	}

	public void quickSort(List<Node> list, int start, int end) {
        // Base case: if start < end, the segment has at least 2 elements
        if (start < end) {
            
            // 1. Get Median (Pivot)
            int medianKey = list.get(start + (end - start) / 2).getKey();
            
            int r = start;
            int l = end;
            while (r <= l) {

                while (list.get(r).getKey() < medianKey) {
                    r++;
                    this.comparisons++;
                }

                while (list.get(l).getKey() > medianKey) {
                    l--;
                    this.comparisons++;
                }

                if (r <= l) {
                    swap(list, r, l); // "swap(a[l], a[r])"
                    r++;
                    l--; 
                }
            }
            if (start < l) {
                quickSort(list, start, l);
            }
            if (r < end) {
                quickSort(list, r, end);
            }
        }
    }
	 
        public void swap(List<Node> list, int i, int j) {
            Node temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
            this.swaps++;
        }
}

