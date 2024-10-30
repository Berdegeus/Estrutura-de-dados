public class HashFunctions {
    
  public static int hashDivisao(String codigo, int tamanhoVetor) {
      return Integer.parseInt(codigo) % tamanhoVetor;
  }

  public static int hashMultiplicacao(String codigo, int tamanhoVetor) {
      double A = 0.6180339887; // Constante entre 0 e 1
      int valor = Integer.parseInt(codigo);
      return (int) (tamanhoVetor * (valor * A % 1));
  }

  public static int hashDobramento(String codigo, int tamanhoVetor) {
      int parte1 = Integer.parseInt(codigo.substring(0, 4));
      int parte2 = Integer.parseInt(codigo.substring(4, 9));
      return (parte1 + parte2) % tamanhoVetor;
  }
}