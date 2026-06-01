package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tarefa {

    private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int id;
    private String titulo;
    private String descricao;
    private LocalDate prazo;
    private StatusTarefa status;
    private Usuario responsavel;

    public Tarefa(int id, String titulo, String descricao, LocalDate prazo, Usuario responsavel) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.responsavel = responsavel;
        this.status = StatusTarefa.PENDENTE;
    }

    public void alterarStatus(StatusTarefa novoStatus) {
        this.status = novoStatus;
    }

    public void alterarResponsavel(Usuario novoResponsavel) {
        this.responsavel = novoResponsavel;
    }

    public boolean estaAtrasada() {
        return LocalDate.now().isAfter(prazo) && status != StatusTarefa.CONCLUIDA;
    }

    public void exibirDetalhes() {
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Descrição: " + descricao);
        System.out.println("Prazo: " + prazo.format(FORMATADOR_DATA));
        System.out.println("Status: " + status);
        System.out.println("Responsável: " + responsavel.getNome());

        if (estaAtrasada()) {
            System.out.println("Situação do prazo: Atrasada");
        } else if (status == StatusTarefa.CONCLUIDA) {
            System.out.println("Situação do prazo: Concluída");
        } else {
            System.out.println("Situação do prazo: Dentro do prazo");
        }
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }
    
    
    @Override
    public String toString() {
        return id + " - " + titulo +
                " | Prazo: " + prazo.format(FORMATADOR_DATA) +
                " | Status: " + status +
                " | Responsável: " + responsavel.getNome();
    }
}