package main;

public enum PerfilUsuario {
    ADMINISTRADOR("Administrador"),
    GERENTE("Gerente"),
    COLABORADOR("Colaborador");

    private String descricao;

    PerfilUsuario(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}