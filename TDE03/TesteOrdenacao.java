import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TesteOrdenacao {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) { // 5 tamanhos: 1000, 10000, 100000, 500000, 1000000
            int[] dados = GeradorDados.gerarDadosPorTamanho(i);

            testarBogoSort(dados.clone(), i);
            testarMergeSort(dados.clone(), i);
            testarQuickSort(dados.clone(), i);
            testarGnomeSort(dados.clone(), i);
        }
    }

    private static void testarBogoSort(int[] dados, int tamanhoIndex) {
        BogoSort bogoSort = new BogoSort();
        long inicio = System.nanoTime();
        bogoSort.ordenar(dados, dados.length); // Passando tamanho do array
        long fim = System.nanoTime();
        salvarCSV("BogoSort", tamanhoIndex, (fim - inicio) / 1000000);
    }

    private static void testarMergeSort(int[] dados, int tamanhoIndex) {
        MergeSort mergeSort = new MergeSort();
        long inicio = System.nanoTime();
        mergeSort.ordenar(dados, dados.length); // Passando tamanho do array
        long fim = System.nanoTime();
        salvarCSV("MergeSort", tamanhoIndex, (fim - inicio) / 1000000);
    }

    private static void testarQuickSort(int[] dados, int tamanhoIndex) {
        QuickSort quickSort = new QuickSort();
        long inicio = System.nanoTime();
        quickSort.ordenar(dados, 0, dados.length - 1);
        long fim = System.nanoTime();
        salvarCSV("QuickSort", tamanhoIndex, (fim - inicio) / 1000000);
    }

    private static void testarGnomeSort(int[] dados, int tamanhoIndex) {
        GnomeSort gnomeSort = new GnomeSort();
        long inicio = System.nanoTime();
        gnomeSort.ordenar(dados, dados.length); // Passando tamanho do array
        long fim = System.nanoTime();
        salvarCSV("GnomeSort", tamanhoIndex, (fim - inicio) / 1000000);
    }

    private static void salvarCSV(String algoritmo, int tamanhoIndex, long tempo) {
        String[] tamanhos = {"1000", "10000", "100000", "500000", "1000000"};
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(algoritmo + "_" + tamanhos[tamanhoIndex] + ".csv", true))) {
            writer.write("Tamanho,Tempo(ms)\n");
            writer.write(tamanhos[tamanhoIndex] + "," + tempo + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}