import java.util.Random;

public class GeradorDados {
    
    public static String[] gerarDados(int quantidade) {
        Random random = new Random(1234); // Seed fixa
        String[] dados = new String[quantidade];
        
        for (int i = 0; i < quantidade; i++) {
            String codigo = String.format("%09d", random.nextInt(1000000000)); // Gera códigos de 9 dígitos
            dados[i] = codigo;
        }
        
        return dados;
    }
}