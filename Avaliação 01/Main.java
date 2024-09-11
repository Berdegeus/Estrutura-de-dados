import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Pilha historico = new Pilha();
        Fila filaAtendimento = new Fila();

        // Adicionando elementos ao histórico (pilha)
        Elemento[] historicoElementos = new Elemento[]{
                new Elemento("REQ001", "Instalação de software", "2024-08-20 10:30"),
                new Elemento("REQ002", "Manutenção preventiva", "2024-08-20 11:00"),
                new Elemento("REQ003", "Atualização de sistema", "2024-08-20 11:30"),
                new Elemento("REQ004", "Suporte técnico", "2024-08-20 12:00"),
                new Elemento("REQ005", "Troca de equipamento", "2024-08-20 12:30"),
                new Elemento("REQ006", "Consulta de garantia", "2024-08-20 13:00"),
                new Elemento("REQ007", "Reparo de impressora", "2024-08-20 13:30"),
                new Elemento("REQ008", "Configuração de rede", "2024-08-20 14:00"),
                new Elemento("REQ009", "Restauração de dados", "2024-08-20 14:30"),
                new Elemento("REQ010", "Consulta técnica", "2024-08-20 15:00")
        };

        for (Elemento e : historicoElementos) {
            historico.empilhar(e);
        }

        // Adicionando elementos à fila de atendimento
        Elemento[] filaElementos = new Elemento[]{
                new Elemento("CLI001", "Maria Silva", "Dúvida sobre produto"),
                new Elemento("CLI002", "João Souza", "Reclamação de serviço"),
                new Elemento("CLI003", "Ana Costa", "Solicitação de reembolso"),
                new Elemento("CLI004", "Pedro Alves", "Informações de entrega"),
                new Elemento("CLI005", "Carla Dias", "Agendamento de visita"),
                new Elemento("CLI006", "Lucas Martins", "Alteração de pedido"),
                new Elemento("CLI007", "Patrícia Rocha", "Cancelamento de contrato"),
                new Elemento("CLI008", "Rafael Lima", "Renovação de assinatura"),
                new Elemento("CLI009", "Fernanda Gomes", "Suporte para instalação"),
                new Elemento("CLI010", "Carlos Eduardo", "Pedido de orçamento")
        };

        for (Elemento e : filaElementos) {
            filaAtendimento.enfileirar(e);
        }

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Menu:");
            System.out.println("1. Exibir histórico de atendimento");
            System.out.println("2. Exibir fila de atendimento");
            System.out.println("3. Adicionar novo atendimento ao histórico");
            System.out.println("4. Remover o último atendimento do histórico");
            System.out.println("5. Atender próximo cliente da fila");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    System.out.println("Histórico de Atendimentos:");
                    historico.exibirPilha();
                    break;
                case 2:
                    System.out.println("Fila de Atendimento:");
                    filaAtendimento.exibirFila();
                    break;
                case 3:
                    System.out.print("Digite o ID da solicitação: ");
                    String reqId = scanner.nextLine();
                    System.out.print("Digite a descrição da solicitação: ");
                    String reqDescricao = scanner.nextLine();
                    System.out.print("Digite a data e hora da solicitação: ");
                    String reqDataHora = scanner.nextLine();
                    historico.empilhar(new Elemento(reqId, reqDescricao, reqDataHora));
                    System.out.println("Solicitação adicionada ao histórico.");
                    break;
                case 4:
                    Elemento solicitacaoRemovida = historico.desempilhar();
                    if (solicitacaoRemovida != null) {
                        System.out.println("Solicitação removida: " + solicitacaoRemovida);
                    }
                    break;
                case 5:
                    Elemento clienteAtendido = filaAtendimento.atender();
                    if (clienteAtendido != null) {
                        System.out.println("Cliente atendido: " + clienteAtendido);
                    } else {
                        System.out.println("Nenhum cliente na fila.");
                    }
                    break;
                case 6:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 6);

        scanner.close();
    }
}
