package main;

public enum StatusProjeto {
    PLANEJADO("Planejado"),
    EM_ANDAMENTO("Em andamento"),
    CONCLUIDO("Concluído"),
    CANCELADO("Cancelado");

    private String descricao;

    StatusProjeto(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}