/**
 * Classe que representa um nó na árvore binária do código Morse.
 */
public class Nodo {
    char caractere; // Caractere armazenado no nó
    Nodo filhoEsquerdo; // Representa '.'
    Nodo filhoDireito;  // Representa '-'

    /**
     * Construtor para criar um nó vazio.
     */
    public Nodo() {
        this.caractere = '\0';
        this.filhoEsquerdo = null;
        this.filhoDireito = null;
    }

    /**
     * Construtor para criar um nó com um caractere.
     * @param caractere O caractere a ser armazenado no nó.
     */
    public Nodo(char caractere) {
        this.caractere = caractere;
        this.filhoEsquerdo = null;
        this.filhoDireito = null;
    }
}
