public class GnomeSort {

    public void ordenar(int[] array, int tamanho) {
        int index = 0;
        while (index < tamanho) {
            if (index == 0 || array[index] >= array[index - 1]) {
                index++;
            } else {
                trocar(array, index, index - 1);
                index--;
            }
        }
    }

    private void trocar(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}