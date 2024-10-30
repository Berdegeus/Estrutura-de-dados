public class MergeSort {

    public void ordenar(int[] array, int tamanho) {
        if (tamanho > 1) {
            int meio = tamanho / 2;
            int[] esquerda = new int[meio];
            int[] direita = new int[tamanho - meio];

            copiarArray(array, esquerda, 0, meio);
            copiarArray(array, direita, meio, tamanho - meio);

            ordenar(esquerda, meio);
            ordenar(direita, tamanho - meio);

            mesclar(array, esquerda, direita, meio, tamanho - meio);
        }
    }

    private void copiarArray(int[] origem, int[] destino, int inicio, int tamanho) {
        for (int i = 0; i < tamanho; i++) {
            destino[i] = origem[inicio + i];
        }
    }

    private void mesclar(int[] array, int[] esquerda, int[] direita, int tamanhoEsquerda, int tamanhoDireita) {
        int i = 0, j = 0, k = 0;
        while (i < tamanhoEsquerda && j < tamanhoDireita) {
            if (esquerda[i] <= direita[j]) {
                array[k++] = esquerda[i++];
            } else {
                array[k++] = direita[j++];
            }
        }
        while (i < tamanhoEsquerda) {
            array[k++] = esquerda[i++];
        }
        while (j < tamanhoDireita) {
            array[k++] = direita[j++];
        }
    }
}