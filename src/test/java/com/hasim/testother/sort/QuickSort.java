package com.hasim.testother.sort;

import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[20];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后:");
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void quickSort(int[] arr, int low, int high) {

        if (low < high) {
            int l = low;
            int h = high;
            int temp = arr[low];

            while (low < high) {
                while (low < high && arr[high] > temp) {
                    high--;
                }

                if (low < high) {
                    arr[low++] = arr[high];
                }

                while (low < high && arr[low] < temp) {
                    low++;
                }

                if (low < high) {
                    arr[high--] = arr[low];
                }
            }
            arr[low] = temp;

            quickSort(arr, l, low - 1);
            quickSort(arr, low + 1, h);
        }

    }

}
