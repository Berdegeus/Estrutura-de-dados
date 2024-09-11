class Elemento {
    String id;
    String descricao;
    String dataHora; // Para o histórico de solicitações

    Elemento(String id, String descricao, String dataHora) {
        this.id = id;
        this.descricao = descricao;
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Descrição: " + descricao + ", Data/Hora: " + dataHora;
    }
}
