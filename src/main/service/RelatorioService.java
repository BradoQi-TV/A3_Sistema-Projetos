package main.service;

import java.util.List;

import main.Equipe;
import main.Projeto;
import main.StatusTarefa;
import main.Tarefa;
import main.Usuario;

public class RelatorioService {

    public static void gerarRelatorioGeral(List<Usuario> usuarios, List<Equipe> equipes, List<Projeto> projetos, List<Tarefa> tarefas) {
        System.out.println("\n=== RELATÓRIO GERAL DE ACOMPANHAMENTO ===");

        System.out.println("Total de usuários cadastrados: " + usuarios.size());
        System.out.println("Total de equipes cadastradas: " + equipes.size());
        System.out.println("Total de projetos cadastrados: " + projetos.size());
        System.out.println("Total de tarefas cadastradas: " + tarefas.size());

        System.out.println("\n=== RESUMO DAS TAREFAS POR STATUS ===");
        System.out.println("Pendentes: " + contarTarefasPorStatus(tarefas, StatusTarefa.PENDENTE));
        System.out.println("Em andamento: " + contarTarefasPorStatus(tarefas, StatusTarefa.EM_ANDAMENTO));
        System.out.println("Concluídas: " + contarTarefasPorStatus(tarefas, StatusTarefa.CONCLUIDA));
        System.out.println("Canceladas: " + contarTarefasPorStatus(tarefas, StatusTarefa.CANCELADA));
    }

    public static int contarTarefasPorStatus(List<Tarefa> tarefas, StatusTarefa status) {
        int contador = 0;

        for (Tarefa tarefa : tarefas) {
            if (tarefa.getStatus() == status) {
                contador++;
            }
        }

        return contador;
    }
}