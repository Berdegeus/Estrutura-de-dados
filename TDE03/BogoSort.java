import java.util.Random;

public class BogoSort {

    public void ordenar(int[] array, int tamanho) {
        while (!estaOrdenado(array, tamanho)) {
            embaralhar(array, tamanho);
        }
    }

    private boolean estaOrdenado(int[] array, int tamanho) {
        for (int i = 1; i < tamanho; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }

    private void embaralhar(int[] array, int tamanho) {
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            int index = random.nextInt(tamanho);
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
    }
}