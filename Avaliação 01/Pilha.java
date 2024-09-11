class Pilha {
    private Node topo;

    public Pilha() {
        this.topo = null;
    }

    public void empilhar(Elemento elemento) {
        Node novoNode = new Node(elemento);
        novoNode.proximo = topo;
        topo = novoNode;
    }

    public Elemento desempilhar() {
        if (topo == null) {
            System.out.println("Pilha vazia.");
            return null;
        }
        Elemento desempilhado = topo.elemento;
        topo = topo.proximo;
        return desempilhado;
    }

    public void exibirPilha() {
        Node atual = topo;
        while (atual != null) {
            System.out.println(atual.elemento);
            atual = atual.proximo;
        }
    }
}
