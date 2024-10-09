import java.util.HashMap;
import java.util.Map;

/**
 * Classe que representa a árvore binária do código Morse.
 */
public class ArvoreBinariaMorse {
    private No raiz;
    private Map<Character, String> mapaCharParaMorse;
    private Map<String, Character> mapaMorseParaChar;

    /**
     * Construtor que inicializa a árvore do código Morse.
     */
    public ArvoreBinariaMorse() {
        raiz = new No();
        mapaCharParaMorse = new HashMap<>();
        mapaMorseParaChar = new HashMap<>();
        inicializarMapasMorse();
        construirArvore();
    }

    /**
     * Inicializa os mapeamentos entre caracteres e código Morse.
     */
    private void inicializarMapasMorse() {
        // Letras
        mapaCharParaMorse.put('A', ".-");
        mapaCharParaMorse.put('B', "-...");
        mapaCharParaMorse.put('C', "-.-.");
        mapaCharParaMorse.put('D', "-..");
        mapaCharParaMorse.put('E', ".");
        mapaCharParaMorse.put('F', "..-.");
        mapaCharParaMorse.put('G', "--.");
        mapaCharParaMorse.put('H', "....");
        mapaCharParaMorse.put('I', "..");
        mapaCharParaMorse.put('J', ".---");
        mapaCharParaMorse.put('K', "-.-");
        mapaCharParaMorse.put('L', ".-..");
        mapaCharParaMorse.put('M', "--");
        mapaCharParaMorse.put('N', "-.");
        mapaCharParaMorse.put('O', "---");
        mapaCharParaMorse.put('P', ".--.");
        mapaCharParaMorse.put('Q', "--.-");
        mapaCharParaMorse.put('R', ".-.");
        mapaCharParaMorse.put('S', "...");
        mapaCharParaMorse.put('T', "-");
        mapaCharParaMorse.put('U', "..-");
        mapaCharParaMorse.put('V', "...-");
        mapaCharParaMorse.put('W', ".--");
        mapaCharParaMorse.put('X', "-..-");
        mapaCharParaMorse.put('Y', "-.--");
        mapaCharParaMorse.put('Z', "--..");

        // Números
        mapaCharParaMorse.put('0', "-----");
        mapaCharParaMorse.put('1', ".----");
        mapaCharParaMorse.put('2', "..---");
        mapaCharParaMorse.put('3', "...--");
        mapaCharParaMorse.put('4', "....-");
        mapaCharParaMorse.put('5', ".....");
        mapaCharParaMorse.put('6', "-....");
        mapaCharParaMorse.put('7', "--...");
        mapaCharParaMorse.put('8', "---..");
        mapaCharParaMorse.put('9', "----.");

        // Mapeamento inverso
        for (Map.Entry<Character, String> entry : mapaCharParaMorse.entrySet()) {
            mapaMorseParaChar.put(entry.getValue(), entry.getKey());
        }
    }

    /**
     * Constrói a árvore inserindo todos os caracteres.
     */
    private void construirArvore() {
        for (Map.Entry<Character, String> entry : mapaCharParaMorse.entrySet()) {
            inserir(entry.getValue(), entry.getKey());
        }
    }

    /**
     * Insere um caractere na árvore com base em seu código Morse.
     * @param codigoMorse O código Morse do caractere.
     * @param caractere O caractere a ser inserido.
     */
    public void inserir(String codigoMorse, char caractere) {
        No nodoAtual = raiz;
        for (int i = 0; i < codigoMorse.length(); i++) {
            char simbolo = codigoMorse.charAt(i);
            if (simbolo == '.') {
                if (nodoAtual.filhoEsquerdo == null) {
                    nodoAtual.filhoEsquerdo = new No();
                }
                nodoAtual = nodoAtual.filhoEsquerdo;
            } else if (simbolo == '-') {
                if (nodoAtual.filhoDireito == null) {
                    nodoAtual.filhoDireito = new No();
                }
                nodoAtual = nodoAtual.filhoDireito;
            } else {
                throw new IllegalArgumentException("Símbolo inválido no código Morse: " + simbolo);
            }
        }
        nodoAtual.caractere = caractere;
    }

    /**
     * Busca um caractere na árvore com base em seu código Morse.
     * @param codigoMorse O código Morse a ser buscado.
     * @return O caractere correspondente ou '\0' se não encontrado.
     */
    public char buscar(String codigoMorse) {
        No nodoAtual = raiz;
        for (int i = 0; i < codigoMorse.length(); i++) {
            if (nodoAtual == null) {
                return '\0';
            }
            char simbolo = codigoMorse.charAt(i);
            if (simbolo == '.') {
                nodoAtual = nodoAtual.filhoEsquerdo;
            } else if (simbolo == '-') {
                nodoAtual = nodoAtual.filhoDireito;
            } else {
                throw new IllegalArgumentException("Símbolo inválido no código Morse: " + simbolo);
            }
        }
        if (nodoAtual != null && nodoAtual.caractere != '\0') {
            return nodoAtual.caractere;
        } else {
            return '\0';
        }
    }

    /**
     * Exibe a árvore de forma hierárquica.
     */
    public void exibirArvore() {
        exibirArvoreHelper(raiz, "");
    }

    /**
     * Método auxiliar para exibir a árvore recursivamente.
     * @param nodo O nodo atual.
     * @param indentacao A indentação para representar a hierarquia.
     */
    private void exibirArvoreHelper(No nodo, String indentacao) {
        if (nodo != null) {
            System.out.println(indentacao + (nodo.caractere != '\0' ? nodo.caractere : "*"));
            exibirArvoreHelper(nodo.filhoEsquerdo, indentacao + " .");
            exibirArvoreHelper(nodo.filhoDireito, indentacao + " -");
        }
    }

    /**
     * Decodifica uma mensagem em código Morse para texto.
     * @param mensagemMorse A mensagem em código Morse.
     * @return A mensagem decodificada em texto.
     */
    public String decodificar(String mensagemMorse) {
        String[] codigos = mensagemMorse.trim().split(" ");
        StringBuilder resultado = new StringBuilder();
        int contadorEspaco = 0;
        for (String codigo : codigos) {
            if (codigo.equals("")) {
                contadorEspaco++;
                if (contadorEspaco == 2) {
                    resultado.append(" "); // Adiciona espaço entre palavras
                    contadorEspaco = 0;
                }
            } else {
                contadorEspaco = 0;
                char caractere = buscar(codigo);
                if (caractere != '\0') {
                    resultado.append(caractere);
                } else {
                    resultado.append("?"); // Código desconhecido
                }
            }
        }
        return resultado.toString();
    }

    /**
     * Codifica uma mensagem de texto em código Morse.
     * @param mensagem A mensagem de texto.
     * @return A mensagem codificada em código Morse.
     */
    public String codificar(String mensagem) {
        StringBuilder mensagemMorse = new StringBuilder();
        for (char c : mensagem.toUpperCase().toCharArray()) {
            if (c == ' ') {
                mensagemMorse.append("  "); // Duplo espaço entre palavras
            } else if (mapaCharParaMorse.containsKey(c)) {
                mensagemMorse.append(mapaCharParaMorse.get(c)).append(" ");
            } else {
                mensagemMorse.append("? "); // Caractere desconhecido
            }
        }
        return mensagemMorse.toString().trim();
    }
}
