package main;

public class Usuario {

    private int id;
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String cargo;
    private String login;
    private String senha;
    private PerfilUsuario perfil;

    public Usuario(int id, String nomeCompleto, String cpf, String email, String cargo, String login, String senha,
            PerfilUsuario perfil) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public int getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getNome() {
        return nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getCargo() {
        return cargo;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    @Override
    public String toString() {
        return id + " - " + nomeCompleto +
                " | CPF: " + cpf +
                " | E-mail: " + email +
                " | Cargo: " + cargo +
                " | Login: " + login +
                " | Perfil: " + perfil;
    }
}