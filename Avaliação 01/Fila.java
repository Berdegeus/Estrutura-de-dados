class Fila {
    private Node frente;
    private Node tras;

    public Fila() {
        this.frente = null;
        this.tras = null;
    }

    public void enfileirar(Elemento elemento) {
        Node novoNode = new Node(elemento);
        if (tras != null) {
            tras.proximo = novoNode;
        }
        tras = novoNode;
        if (frente == null) {
            frente = novoNode;
        }
    }

    public Elemento atender() {
        if (frente == null) {
            System.out.println("Fila vazia.");
            return null;
        }
        Elemento atendido = frente.elemento;
        frente = frente.proximo;
        if (frente == null) {
            tras = null;
        }
        return atendido;
    }

    public void exibirFila() {
        Node atual = frente;
        while (atual != null) {
            System.out.println(atual.elemento);
            atual = atual.proximo;
        }
    }
}
