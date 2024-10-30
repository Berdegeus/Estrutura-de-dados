import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TesteHashTable {

    public static void main(String[] args) {
        int[] tamanhosTabelas = {1000, 10000, 100000};  // Tamanhos diferentes para a tabela hash
        int[] tamanhosDados = {1000000, 5000000, 20000000}; // Conjuntos de dados

        for (int tamanhoTabela : tamanhosTabelas) {
            for (int tamanhoDados : tamanhosDados) {
                String[] dados = GeradorDados.gerarDados(tamanhoDados);

                String nomeArquivo = "resultados_" + tamanhoTabela + "_" + tamanhoDados + ".csv";

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
                    writer.write("Tamanho Tabela,Conjunto de Dados,Função Hash,Tempo Inserção (ms),Colisões,Tempo Busca (ms),Comparações\n");

                    // Testar para cada função hash
                    testarTabela(tamanhoTabela, tamanhoDados, dados, writer, "Divisao", HashFunctions::hashDivisao);
                    testarTabela(tamanhoTabela, tamanhoDados, dados, writer, "Multiplicacao", HashFunctions::hashMultiplicacao);
                    testarTabela(tamanhoTabela, tamanhoDados, dados, writer, "Dobramento", HashFunctions::hashDobramento);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("CSV salvo: " + nomeArquivo);
            }
        }
    }

    private static void testarTabela(int tamanhoTabela, int tamanhoDados, String[] dados, BufferedWriter writer, String funcaoHash, HashFunctionInterface funcao) throws IOException {
        HashTableEncadeamento tabelaEncadeamento = new HashTableEncadeamento(tamanhoTabela);

        // Teste de Inserção
        long inicioInsercao = System.nanoTime();
        int colisaoInsercao = 0;
        for (String codigo : dados) {
            int hash = funcao.apply(codigo, tamanhoTabela);
            if (tabelaEncadeamento.buscar(codigo, hash)) {
                colisaoInsercao++;
            }
            tabelaEncadeamento.inserir(codigo, hash);
        }
        long fimInsercao = System.nanoTime();
        long tempoInsercao = (fimInsercao - inicioInsercao) / 1000000; // em ms

        // Teste de Busca
        long inicioBusca = System.nanoTime();
        int comparacoesBusca = 0;
        for (String codigo : dados) {
            int hash = funcao.apply(codigo, tamanhoTabela);
            if (tabelaEncadeamento.buscar(codigo, hash)) {
                comparacoesBusca++;
            }
        }
        long fimBusca = System.nanoTime();
        long tempoBusca = (fimBusca - inicioBusca) / 1000000; // em ms

        // Escrever os resultados no arquivo CSV
        writer.write(tamanhoTabela + "," + tamanhoDados + "," + funcaoHash + "," + tempoInsercao + "," + colisaoInsercao + "," + tempoBusca + "," + comparacoesBusca + "\n");
    }

    interface HashFunctionInterface {
        int apply(String codigo, int tamanhoVetor);
    }
}