/**
 * Classe que representa um nó na árvore binária do código Morse.
 */
public class No {
    char caractere; // Caractere armazenado no nó
    No filhoEsquerdo; // Representa '.'
    No filhoDireito;  // Representa '-'

    /**
     * Construtor para criar um nó vazio.
     */
    public No() {
        this.caractere = '\0';
        this.filhoEsquerdo = null;
        this.filhoDireito = null;
    }

    /**
     * Construtor para criar um nó com um caractere.
     * @param caractere O caractere a ser armazenado no nó.
     */
    public No(char caractere) {
        this.caractere = caractere;
        this.filhoEsquerdo = null;
        this.filhoDireito = null;
    }
}
