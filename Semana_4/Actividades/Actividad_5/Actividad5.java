package Semana_4.Actividades.Actividad_5;

public class Actividad5 {
    // Metodo 1: O(n^2)
    public static int moda1(int array[]) {
        int first = 0;
        int end = array.length-1;

        if (first == end)
            return array[first]; // caso base

        int moda = array[first];
        int maxfrec = frecuencia(array, first, end, moda);

        for (int i = first+1; i<end; i++) {

            int frec = frecuencia(array, i, end, array[i]); // calcula frecuencia

            if (frec > maxfrec) {
                maxfrec = frec;
                moda = array[i];
            }
        }

        return moda;
    }
    private static int frecuencia(int []array, int first, int end, int ele) {
        if (first > end) return 0;

        int suma = 0;

        for (int i = first; i<=end; i++)
            if (array[i] == ele)
                suma ++;

        return suma;
    }

    // Metodo 2: O(n)
    public static int moda2(int array[]) {
        int first = 1;
        int p = 0;
        int end = array.length-1;

        int moda = array[0];
        int frec = 1;
        int maxfrec = 0;

        while (first <= end) {

            if (array[p] == array[first]) {
                frec ++;
                first++;
            }
            else {
                if (frec > maxfrec) {
                    maxfrec = frec;
                    moda = array[p];
                }

                p = first;
                first = p+1;
                frec = 1;
            }
        }

        return moda;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 5, 5, 6, 7, 8};

        System.out.println("Moda usando método O(n^2): " + moda1(array));
        System.out.println("Moda usando método O(n): " + moda2(array));
    }
}
