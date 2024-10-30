public class QuickSort {

    private int trocas;
    private int iteracoes;

    public QuickSort() {
        this.trocas = 0;
        this.iteracoes = 0;
    }

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
            iteracoes++;
        }
        trocar(array, i + 1, fim);
        return i + 1;
    }

    private void trocar(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        trocas++;
    }

    public int getTrocas() {
        return trocas;
    }

    public int getIteracoes() {
        return iteracoes;
    }
}