import java.util.Random;

public class TabelaHash {

    // Classe Registro com código de 9 dígitos
    static class Registro {
        long codigo;

        public Registro(long codigo) {
            this.codigo = codigo;
        }
    }

    // Interface para as funções hash
    interface FuncaoHash {
        int hash(long chave);
    }

    // Função hash por divisão
    static class HashDivisao implements FuncaoHash {
        int tamanho;

        public HashDivisao(int tamanho) {
            this.tamanho = tamanho;
        }

        @Override
        public int hash(long chave) {
            return (int) (chave % tamanho);
        }
    }

    // Função hash por multiplicação
    static class HashMultiplicacao implements FuncaoHash {
        int tamanho;
        final double A = 0.6180339887; // Constante (raiz de 5 - 1)/2

        public HashMultiplicacao(int tamanho) {
            this.tamanho = tamanho;
        }

        @Override
        public int hash(long chave) {
            double frac = (chave * A) % 1;
            return (int) (tamanho * frac);
        }
    }

    // Função hash por dobramento
    static class HashDobramento implements FuncaoHash {
        int tamanho;

        public HashDobramento(int tamanho) {
            this.tamanho = tamanho;
        }

        @Override
        public int hash(long chave) {
            String strChave = Long.toString(chave);
            int soma = 0;
            int n = 2; // Número de dígitos por grupo

            for (int i = 0; i < strChave.length(); i += n) {
                String parte = strChave.substring(i, Math.min(i + n, strChave.length()));
                soma += Integer.parseInt(parte);
            }
            return soma % tamanho;
        }
    }

    // Classe Nó para encadeamento
    static class No {
        Registro registro;
        No proximo;

        public No(Registro registro) {
            this.registro = registro;
            this.proximo = null;
        }
    }

    // Classe da Tabela Hash com encadeamento usando nós
    static class HashTable {
        No[] tabela;
        FuncaoHash funcaoHash;
        int colisoes;

        public HashTable(int tamanho, FuncaoHash funcaoHash) {
            this.tabela = new No[tamanho];
            this.funcaoHash = funcaoHash;
            this.colisoes = 0;
        }

        public void inserir(Registro reg) {
            int indice = funcaoHash.hash(reg.codigo);
            No novoNo = new No(reg);

            if (tabela[indice] == null) {
                tabela[indice] = novoNo;
            } else {
                // Colisão ocorreu
                colisoes++;
                No atual = tabela[indice];
                while (atual.proximo != null) {
                    atual = atual.proximo;
                }
                atual.proximo = novoNo;
            }
        }

        public Registro buscar(long codigo) {
            int indice = funcaoHash.hash(codigo);
            No atual = tabela[indice];
            while (atual != null) {
                if (atual.registro.codigo == codigo) {
                    return atual.registro;
                }
                atual = atual.proximo;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        // Tamanhos da tabela hash
        int[] tamanhosTabela = {1000, 10000, 100000};

        // Tamanhos dos conjuntos de dados
        int[] tamanhosDados = {1000000, 5000000, 20000000};

        // Geração dos conjuntos de dados com seed fixa
        Registro[][] conjuntosDados = gerarConjuntosDados(tamanhosDados);

        // Para cada tamanho de tabela
        for (int tamanhoTabela : tamanhosTabela) {
            System.out.println("Tamanho da Tabela: " + tamanhoTabela);

            // Para cada função hash
            FuncaoHash[] funcoesHash = {
                new HashDivisao(tamanhoTabela),
                new HashMultiplicacao(tamanhoTabela),
                new HashDobramento(tamanhoTabela)
            };

            String[] nomesFuncoesHash = {"Divisão", "Multiplicação", "Dobramento"};

            for (int f = 0; f < funcoesHash.length; f++) {
                FuncaoHash funcaoHash = funcoesHash[f];
                String nomeFuncaoHash = nomesFuncoesHash[f];
                System.out.println("Função Hash: " + nomeFuncaoHash);

                // Para cada conjunto de dados
                for (int i = 0; i < tamanhosDados.length; i++) {
                    int tamanhoDados = tamanhosDados[i];
                    Registro[] dados = conjuntosDados[i];
                    System.out.println("Tamanho do Conjunto de Dados: " + tamanhoDados);

                    // Cria a tabela hash
                    HashTable tabelaHash = new HashTable(tamanhoTabela, funcaoHash);

                    // Inserção dos elementos
                    long inicioInsercao = System.currentTimeMillis();
                    for (Registro reg : dados) {
                        tabelaHash.inserir(reg);
                    }
                    long fimInsercao = System.currentTimeMillis();
                    long tempoInsercao = fimInsercao - inicioInsercao;

                    System.out.println("Tempo de Inserção: " + tempoInsercao + " ms");
                    System.out.println("Número de Colisões: " + tabelaHash.colisoes);

                    // Busca de elementos (5 buscas)
                    long inicioBusca = System.currentTimeMillis();
                    int comparacoesTotais = 0;
                    for (int j = 0; j < 5; j++) {
                        // Escolhe um índice específico para busca consistente (usando seed)
                        Random randBusca = new Random(j);
                        int indiceBusca = randBusca.nextInt(tamanhoDados);
                        Registro regBusca = dados[indiceBusca];

                        // Realiza a busca e conta comparações
                        int comparacoes = 0;
                        int indiceTabela = tabelaHash.funcaoHash.hash(regBusca.codigo);
                        No atual = tabelaHash.tabela[indiceTabela];
                        while (atual != null) {
                            comparacoes++;
                            if (atual.registro.codigo == regBusca.codigo) {
                                break;
                            }
                            atual = atual.proximo;
                        }
                        comparacoesTotais += comparacoes;
                    }
                    long fimBusca = System.currentTimeMillis();
                    long tempoBusca = fimBusca - inicioBusca;

                    System.out.println("Tempo de Busca (5 buscas): " + tempoBusca + " ms");
                    System.out.println("Número de Comparações (total): " + comparacoesTotais);
                    System.out.println("---------------------------------------");
                }
            }
        }
    }

    // Método para gerar conjuntos de dados com seed fixa
    static Registro[][] gerarConjuntosDados(int[] tamanhosDados) {
        Registro[][] conjuntos = new Registro[tamanhosDados.length][];
        for (int i = 0; i < tamanhosDados.length; i++) {
            int tamanho = tamanhosDados[i];
            conjuntos[i] = new Registro[tamanho];
            Random rand = new Random(42); // Seed fixa para reprodutibilidade
            for (int j = 0; j < tamanho; j++) {
                long codigo = 100000000L + Math.abs(rand.nextLong()) % 900000000L;
                conjuntos[i][j] = new Registro(codigo);
            }
        }
        return conjuntos;
    }
}