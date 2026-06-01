package main;

import java.util.ArrayList;
import java.util.List;

public class Equipe {

    private int id;
    private String nome;
    private String descricao;
    private List<Usuario> membros;
    private List<Projeto> projetos;

    public Equipe(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.membros = new ArrayList<>();
        this.projetos = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }

    public boolean adicionarMembro(Usuario usuario) {
        if (usuarioJaEstaNaEquipe(usuario)) {
            return false;
        }

        membros.add(usuario);
        return true;
    }
    public boolean usuarioJaEstaNaEquipe(Usuario usuario) {
        for (Usuario membro : membros) {
            if (membro.getId() == usuario.getId()) {
                return true;
            }
        }

        return false;
    }

    public void adicionarProjeto(Projeto projeto) {
        if (!projetos.contains(projeto)) {
            projetos.add(projeto);
        }
    }

    public void listarMembros() {
        if (membros.isEmpty()) {
            System.out.println("Nenhum membro cadastrado nesta equipe.");
            return;
        }

        for (Usuario usuario : membros) {
            System.out.println(usuario);
        }
    }

    public void listarProjetos() {
        if (projetos.isEmpty()) {
            System.out.println("Nenhum projeto vinculado a esta equipe.");
            return;
        }

        for (Projeto projeto : projetos) {
            System.out.println(projeto);
        }
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return id + " - " + nome +
                " | " + descricao +
                " | Quantidade de membros: " + membros.size() +
                " | Projetos vinculados: " + projetos.size();
    }
}