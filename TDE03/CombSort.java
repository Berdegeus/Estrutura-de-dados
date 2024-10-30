public class CombSort {

  private int trocas;
  private int iteracoes;

  public CombSort() {
      this.trocas = 0;
      this.iteracoes = 0;
  }

  public void ordenar(int[] array, int tamanho) {
      int intervalo = tamanho;
      boolean trocou = true;

      while (intervalo != 1 || trocou) {
          intervalo = calcularNovoIntervalo(intervalo);
          trocou = false;

          for (int i = 0; i < tamanho - intervalo; i++) {
              if (array[i] > array[i + intervalo]) {
                  trocar(array, i, i + intervalo);
                  trocou = true;
              }
              iteracoes++;
          }
      }
  }

  private int calcularNovoIntervalo(int intervalo) {
      intervalo = (int) (intervalo / 1.3);
      if (intervalo < 1) {
          return 1;
      }
      return intervalo;
  }

  private void trocar(int[] array, int i, int j) {
      int temp = array[i];
      array[i] = array[j];
      array[j] = temp;
      trocas++;
  }

  public int getTrocas() {
      return trocas;
  }

  public int getIteracoes() {
      return iteracoes;
  }
}