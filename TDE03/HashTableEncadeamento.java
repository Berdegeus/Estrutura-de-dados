class Node {
  String codigo;
  Node proximo;

  public Node(String codigo) {
      this.codigo = codigo;
      this.proximo = null;
  }
}

public class HashTableEncadeamento {
  private Node[] tabela;

  public HashTableEncadeamento(int tamanho) {
      tabela = new Node[tamanho];
  }

  public void inserir(String codigo, int hash) {
      Node novo = new Node(codigo);
      if (tabela[hash] == null) {
          tabela[hash] = novo;
      } else {
          Node atual = tabela[hash];
          while (atual.proximo != null) {
              atual = atual.proximo;
          }
          atual.proximo = novo;
      }
  }

  public boolean buscar(String codigo, int hash) {
      Node atual = tabela[hash];
      while (atual != null) {
          if (atual.codigo.equals(codigo)) {
              return true;
          }
          atual = atual.proximo;
      }
      return false;
  }
}