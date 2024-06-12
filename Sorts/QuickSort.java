package Visualizer.Sorts;

import Visualizer.*;

public class QuickSort implements Runnable {

    private Integer[] toBeSorted;
    private VisualizerFrame frame;
    private boolean fast;

    public QuickSort(Integer[] toBeSorted, VisualizerFrame frame, boolean fast) {
        this.toBeSorted = toBeSorted;
        this.frame = frame;
        this.fast = fast;
    }

    public void run() {
        if (fast) {
            sortFast(0, toBeSorted.length - 1);
        } else {
            sortSlow(0, toBeSorted.length - 1);
        }
        SortingVisualizer.isSorting = false;
    }

    private void sortFast(int low, int high) {
        if (low < high) {
            int pi = partitionFast(low, high);
            sortFast(low, pi - 1);
            sortFast(pi + 1, high);
        }
    }

    private int partitionFast(int low, int high) {
        int pivot = toBeSorted[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (toBeSorted[j] <= pivot) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        frame.reDrawArray(toBeSorted);
        try {
            Thread.sleep(SortingVisualizer.sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i + 1;
    }

    private void sortSlow(int low, int high) {
        if (low < high) {
            int pi = partitionSlow(low, high);
            sortSlow(low, pi - 1);
            sortSlow(pi + 1, high);
        }
    }

    private int partitionSlow(int low, int high) {
        int pivot = toBeSorted[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (toBeSorted[j] <= pivot) {
                i++;
                swap(i, j);
                frame.reDrawArray(toBeSorted, i, j);
                try {
                    Thread.sleep(SortingVisualizer.sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        swap(i + 1, high);
        frame.reDrawArray(toBeSorted, i + 1, high);
        try {
            Thread.sleep(SortingVisualizer.sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i + 1;
    }

    private void swap(int i, int j) {
        int temp = toBeSorted[i];
        toBeSorted[i] = toBeSorted[j];
        toBeSorted[j] = temp;
    }
}
