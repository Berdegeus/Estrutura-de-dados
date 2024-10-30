import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TesteOrdenacao {

    public static void main(String[] args) {
        int[] tamanhos = {1000, 10000, 100000, 500000, 1000000};

        for (int tamanho : tamanhos) {
            int[] dados = new int[tamanho];
            GeradorDados.gerarDados(dados, tamanho);

            testarCombSort(dados.clone(), tamanho);
            testarMergeSort(dados.clone(), tamanho);
            testarQuickSort(dados.clone(), tamanho);
            testarGnomeSort(dados.clone(), tamanho);
        }
    }

    private static void testarCombSort(int[] dados, int tamanho) {
        CombSort combSort = new CombSort();
        long inicio = System.nanoTime();
        combSort.ordenar(dados, tamanho);
        long fim = System.nanoTime();
        salvarCSV("CombSort", tamanho, (fim - inicio) / 1000000, combSort.getTrocas(), combSort.getIteracoes());
    }

    private static void testarMergeSort(int[] dados, int tamanho) {
        MergeSort mergeSort = new MergeSort();
        long inicio = System.nanoTime();
        mergeSort.ordenar(dados, tamanho);
        long fim = System.nanoTime();
        salvarCSV("MergeSort", tamanho, (fim - inicio) / 1000000, mergeSort.getTrocas(), mergeSort.getIteracoes());
    }

    private static void testarQuickSort(int[] dados, int tamanho) {
        QuickSort quickSort = new QuickSort();
        long inicio = System.nanoTime();
        quickSort.ordenar(dados, 0, tamanho - 1);
        long fim = System.nanoTime();
        salvarCSV("QuickSort", tamanho, (fim - inicio) / 1000000, quickSort.getTrocas(), quickSort.getIteracoes());
    }

    private static void testarGnomeSort(int[] dados, int tamanho) {
        GnomeSort gnomeSort = new GnomeSort();
        long inicio = System.nanoTime();
        gnomeSort.ordenar(dados, tamanho);
        long fim = System.nanoTime();
        salvarCSV("GnomeSort", tamanho, (fim - inicio) / 1000000, gnomeSort.getTrocas(), gnomeSort.getIteracoes());
    }

    private static void salvarCSV(String algoritmo, int tamanho, long tempo, int trocas, int iteracoes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(algoritmo + "_" + tamanho + ".csv", true))) {
            writer.write("Tamanho,Tempo(ms),Trocas,Iteracoes\n");
            writer.write(tamanho + "," + tempo + "," + trocas + "," + iteracoes + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}