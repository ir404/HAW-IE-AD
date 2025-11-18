package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickSort {
	private ArrayList<Node> data;
	private long comparisons;
	private long swaps;
	private int medianCalculationMethod;
	 
	public QuickSort(ArrayList<Node> listToSort) {
        this.data = listToSort;
        this.comparisons = 0;
        this.swaps = 0;
    }
	 
	public long getComparisons() {
		return comparisons;
	}

	public long getSwaps() {
		return swaps;
	}

    public void start(int medianCalcMethod) {
        if (data != null && data.size() > 2) {
            this.medianCalculationMethod = medianCalcMethod;
            quickSort(data, 0, data.size() - 1);
        }
    }

	private void quickSort(ArrayList<Node> list, int start, int end) {
        if (start < end) {
            int medianKey = getMedian(list,start,end);
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
                    swap(list, r, l);
                    this.swaps++;
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
	 
    private void swap(List<Node> list, int i, int j) {
        Node temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private int getMedian(ArrayList<Node> list, int start, int end) {
        int median ;
        switch (medianCalculationMethod) {
            case 1:
                // middle index median
                median = list.get(start + (end - start) / 2).getKey();
                break;
            case 2:
                // return random key median
                Random rand = new Random();
                int randomIndex = start + rand.nextInt(end - start + 1);
                median = list.get(randomIndex).getKey();
                break;
            case 3:
                // return start key median
                median = list.get(start).getKey();
                break;
            case 4:
                // return mid-median between three keys
                median = mid(list.get(start).getKey(), list.get(end).getKey(), list.get(start + (end - start) / 2).getKey());
                break;
            default:
                median = list.get(start + (end - start) / 2).getKey();
                break;
        }
        return median;
    }

    private int mid(int start,int end,int mid) {
        int a = start;
        int b = end;
        int c = mid;
        if ((a > b) != (a > c)) {
            return a;                       // a is between b and c
        } else if ((b > a) != (b > c)) {
            return b;                       // b is between a and c
        } else {
            return c;                       // c is the median
        }
    }
}

