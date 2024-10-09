import java.util.Scanner;

/**
 * Classe principal para interação com o usuário.
 */
public class MorseCodeApp {
    public static void main(String[] args) {
        ArvoreBinariaMorse arvoreMorse = new ArvoreBinariaMorse();

        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        System.out.println("Bem-vindo ao Tradutor de Código Morse!");

        while (!sair) {
            System.out.println("Menu:");
            System.out.println("1. Exibir Árvore do Código Morse");
            System.out.println("2. Codificar Texto para Código Morse");
            System.out.println("3. Decodificar Código Morse para Texto");
            System.out.println("4. Sair");
            System.out.print("Digite sua escolha (1-4): ");
            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    arvoreMorse.exibirArvore();
                    break;
                case "2":
                    System.out.print("Digite o texto a ser codificado: ");
                    String textoParaCodificar = scanner.nextLine();
                    String codificado = arvoreMorse.codificar(textoParaCodificar);
                    System.out.println("Código Morse: " + codificado);
                    break;
                case "3":
                    System.out.print("Digite o código Morse a ser decodificado (separe letras por espaço e palavras por duplo espaço): ");
                    String morseParaDecodificar = scanner.nextLine();
                    String decodificado = arvoreMorse.decodificar(morseParaDecodificar);
                    System.out.println("Texto Decodificado: " + decodificado);
                    break;
                case "4":
                    sair = true;
                    break;
                default:
                    System.out.println("Escolha inválida! Por favor, selecione de 1 a 4.");
            }

            System.out.println();
        }

        scanner.close();
        System.out.println("Até mais!");
    }
}
