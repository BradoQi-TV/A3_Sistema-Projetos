package main;

public enum StatusTarefa {
    PENDENTE("Pendente"),
    EM_ANDAMENTO("Em andamento"),
    CONCLUIDA("Concluída"),
    CANCELADA("Cancelada");

    private String descricao;

    StatusTarefa(String descricao) {
        this.descricao = descricao;
        
    }
  
    @Override
    public String toString() {
        return descricao;
    }
}