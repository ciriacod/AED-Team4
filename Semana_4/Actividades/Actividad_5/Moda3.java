package Semana_4.Actividades.Actividad_5;

import java.util.*;

class Limits {
    int start, end;

    public Limits(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int length() {
        return end - start + 1;
    }
}

// Metodo 3: O(n log n) 

public class Moda3 {

    public static int moda3(int[] arr, int start, int end) {

        List<Limits> heterogeneos = new ArrayList<>();
        List<Limits> homogeneos = new ArrayList<>();

        heterogeneos.add(new Limits(start, end));

        while (maxLength(heterogeneos) > maxLength(homogeneos)) {

            Limits p = extractMax(heterogeneos);

            int mid = (p.start + p.end) / 2;
            int pivot = arr[mid]; // mediana

            int[] bounds = partition(arr, p.start, p.end, pivot);
            int izq = bounds[0];
            int der = bounds[1];

            Limits p1 = new Limits(p.start, izq - 1);
            Limits p2 = new Limits(izq, der - 1);
            Limits p3 = new Limits(der, p.end);

            if (p1.start <= p1.end)
                heterogeneos.add(p1);

            if (p3.start <= p3.end)
                heterogeneos.add(p3);

            if (p2.start <= p2.end)
                homogeneos.add(p2);
        }

        if (homogeneos.isEmpty())
            return arr[start];

        Limits max = extractMax(homogeneos);
        return arr[max.start];
    }

    private static int[] partition(int[] arr, int low, int high, int pivot) {
        int i = low, j = low, k = high;

        while (j <= k) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++; j++;
            } else if (arr[j] > pivot) {
                swap(arr, j, k);
                k--;
            } else {
                j++;
            }
        }
        return new int[]{i, k + 1};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int maxLength(List<Limits> list) {
        int max = 0;
        for (Limits l : list)
            if (l.length() > max)
                max = l.length();
        return max;
    }

    private static Limits extractMax(List<Limits> list) {
        Limits max = list.get(0);
        for (Limits l : list)
            if (l.length() > max.length())
                max = l;
        list.remove(max);
        return max;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 5, 5, 6, 7, 8};
        System.out.println("Moda usando método O(n log n): " + moda3(array, 0, array.length - 1));
    }

}