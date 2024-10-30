import java.util.Random;

public class GeradorDados {

    private static final long SEED = 12345; // Seed fixa para todos os conjuntos
    private static Random random = new Random(SEED);

    public static int[] gerarDados(int tamanho) {
        int[] dados = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            dados[i] = random.nextInt(10000); // Gera números entre 0 e 9999
        }
        return dados;
    }

    // Método para diferentes tamanhos de conjuntos
    public static int[] gerarDadosPorTamanho(int escolha) {
        int[] tamanhos = {1000, 10000, 100000, 500000, 1000000};
        return gerarDados(tamanhos[escolha]);
    }
}