package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickSort {
	private ArrayList<Node> data;
	private long comparisons;
	private long swaps;
	private int method;
	 
	public QuickSort(ArrayList<Node> listToSort) {
        this.data = listToSort;
        this.comparisons = 0;
        this.swaps = 0;
    }
	
	public void start(int method) {
        if (data == null || data.size() < 2) {
            return; // Already sorted
        }
        this.method=method;
        // Call the 3-parameter helper method
        quickSort(data, 0, data.size() - 1);
    }
	 
	public long getComparisons() {
		return comparisons;
	}

	public long getSwaps() {
		return swaps;
	}

	public void quickSort(ArrayList<Node> list, int start, int end) {
        // Base case: if start < end, the segment has at least 2 elements
        if (start < end) {
            
            // 1. Get Median (Pivot)
            int medianKey = getMedian(list,start,end,method);
            
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
        
        public int getMedian(ArrayList<Node> list, int start, int end,int method) {
        	switch(method) {
        	case 1:
        		//middle index median
        		return list.get(start + (end - start) / 2).getKey();
			case 2:
				//return random key median
        		Random rand = new Random();
        		int randomIndex = start + rand.nextInt(end - start + 1);
        		return list.get(randomIndex).getKey();
			case 3:
				//return start key median
        		return list.get(start).getKey();
			case 4:
				//return mid median between three keys
				return mid(list.get(start).getKey(),list.get(end).getKey(),list.get(start + (end - start) / 2).getKey());
        	default:
        		return list.get(start + (end - start) / 2).getKey();
        	}
        }
       
        public int mid(int start,int end,int mid) {
        	int a=start;
        	int b=end;
        	int c=mid;
        	if ((a > b) != (a > c)) {
        	    return a; // a is between b and c
        	} else if ((b > a) != (b > c)) {
        	    return b; // b is between a and c
        	} else {
        	    return c; // c is the median
        	}
        }
}

