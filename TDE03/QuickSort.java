public class QuickSort {

    public void ordenar(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int pivoIndex = particionar(array, inicio, fim);
            ordenar(array, inicio, pivoIndex - 1);
            ordenar(array, pivoIndex + 1, fim);
        }
    }

    private int particionar(int[] array, int inicio, int fim) {
        int pivo = array[fim];
        int i = (inicio - 1);
        for (int j = inicio; j < fim; j++) {
            if (array[j] <= pivo) {
                i++;
                trocar(array, i, j);
            }
        }
        trocar(array, i + 1, fim);
        return i + 1;
    }

    private void trocar(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}