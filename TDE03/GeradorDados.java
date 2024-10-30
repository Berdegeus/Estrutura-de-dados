import java.util.Random;

public class GeradorDados {

    private static final long SEED = 12345; // Seed fixa para garantir repetibilidade
    private static Random random = new Random(SEED);

    public static void gerarDados(int[] dados, int tamanho) {
        for (int i = 0; i < tamanho; i++) {
            dados[i] = random.nextInt(10000); // Gera números entre 0 e 9999
        }
    }
}