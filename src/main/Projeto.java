package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Projeto {

    private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTerminoPrevista;
    private StatusProjeto status;
    private Usuario gerenteResponsavel;
    private List<Equipe> equipes;
    private List<Tarefa> tarefas;

    public Projeto(int id, String nome, String descricao, LocalDate dataInicio,
            LocalDate dataTerminoPrevista, StatusProjeto status, Usuario gerenteResponsavel) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTerminoPrevista = dataTerminoPrevista;
        this.status = status;
        this.gerenteResponsavel = gerenteResponsavel;
        this.equipes = new ArrayList<>();
        this.tarefas = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    public List<Equipe> getEquipes() {
        return equipes;
    }
    
    public boolean adicionarEquipe(Equipe equipe) {
        if (equipes.contains(equipe)) {
            return false;
        }

        equipes.add(equipe);
        equipe.adicionarProjeto(this);
        return true;
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public void alterarStatus(StatusProjeto novoStatus) {
        this.status = novoStatus;
    }

    public void listarEquipes() {
        if (equipes.isEmpty()) {
            System.out.println("Nenhuma equipe vinculada a este projeto.");
            return;
        }

        for (Equipe equipe : equipes) {
            System.out.println(equipe);
        }
    }

    public void listarTarefas() {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada neste projeto.");
            return;
        }

        for (Tarefa tarefa : tarefas) {
            System.out.println(tarefa);
        }
    }
    
    public void listarTarefasPorStatus(StatusTarefa statusBuscado) {
        boolean encontrou = false;

        for (Tarefa tarefa : tarefas) {
            if (tarefa.getStatus() == statusBuscado) {
                System.out.println(tarefa);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma tarefa encontrada com o status: " + statusBuscado);
        }
    }

    @Override
    public String toString() {
        return id + " - " + nome +
                " | " + descricao +
                " | Início: " + dataInicio.format(FORMATADOR_DATA) +
                " | Término previsto: " + dataTerminoPrevista.format(FORMATADOR_DATA) +
                " | Status: " + status +
                " | Gerente: " + gerenteResponsavel.getNome();
    }
}