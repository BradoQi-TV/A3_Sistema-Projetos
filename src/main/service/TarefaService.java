package main.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import main.Projeto;
import main.StatusTarefa;
import main.Tarefa;
import main.Usuario;
import main.util.EntradaUtil;

public class TarefaService {

    public static void listarTarefas(List<Tarefa> tarefas) {
        System.out.println("\n=== TAREFAS ===");

        for (Tarefa tarefa : tarefas) {
            System.out.println(tarefa);
        }
    }

    public static Tarefa buscarTarefaPorId(List<Tarefa> tarefas, int id) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == id) {
                return tarefa;
            }
        }

        return null;
    }
    
    public static StatusTarefa escolherStatus(int opcaoStatus) {
        if (opcaoStatus == 1) {
            return StatusTarefa.PENDENTE;
        } else if (opcaoStatus == 2) {
            return StatusTarefa.EM_ANDAMENTO;
        } else if (opcaoStatus == 3) {
            return StatusTarefa.CONCLUIDA;
        } else if (opcaoStatus == 4) {
            return StatusTarefa.CANCELADA;
        } else {
            return null;
        }
    }
    
    public static void listarTarefasPorStatus(List<Tarefa> tarefas, StatusTarefa status) {
        System.out.println("\n=== " + obterTituloStatus(status) + " ===");

        boolean encontrou = false;

        for (Tarefa tarefa : tarefas) {
            if (tarefa.getStatus() == status) {
                System.out.println(tarefa);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma tarefa encontrada com o status: " + status);
        }
    }

    public static String obterTituloStatus(StatusTarefa status) {
        if (status == StatusTarefa.EM_ANDAMENTO) {
            return "TAREFAS EM ANDAMENTO";
        } else if (status == StatusTarefa.CONCLUIDA) {
            return "TAREFAS CONCLUÍDAS";
        } else if (status == StatusTarefa.PENDENTE) {
            return "TAREFAS PENDENTES";
        } else if (status == StatusTarefa.CANCELADA) {
            return "TAREFAS CANCELADAS";
        } else {
            return "TAREFAS";
        }
    }
    
    public static void alterarStatusTarefa(Scanner sc, List<Tarefa> tarefas) {
        System.out.println("\n=== ALTERAR STATUS DA TAREFA ===");

        listarTarefas(tarefas);

        int idTarefaStatus = EntradaUtil.lerInteiro(sc, "Digite o ID da tarefa: ");

        Tarefa tarefaParaAlterar = buscarTarefaPorId(tarefas, idTarefaStatus);

        if (tarefaParaAlterar != null) {
            System.out.println("\nStatus atual: " + tarefaParaAlterar.getStatus());
            System.out.println("Escolha o novo status:");
            System.out.println("1 - Pendente");
            System.out.println("2 - Em andamento");
            System.out.println("3 - Concluída");
            System.out.println("4 - Cancelada");

            int opcaoStatus = EntradaUtil.lerInteiro(sc, "Opção: ");

            StatusTarefa novoStatus = escolherStatus(opcaoStatus);

            if (novoStatus != null) {
                tarefaParaAlterar.alterarStatus(novoStatus);
                System.out.println("Status da tarefa alterado com sucesso!");
            } else {
                System.out.println("Opção de status inválida.");
            }

        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }
    
    public static void cadastrarTarefa(Scanner sc, List<Tarefa> tarefas, List<Usuario> usuarios, List<Projeto> projetos) {
        System.out.println("\n=== CADASTRAR NOVA TAREFA ===");

        String titulo = EntradaUtil.lerTextoObrigatorio(sc, "Título");
        String descricao = EntradaUtil.lerTextoObrigatorio(sc, "Descrição");

        LocalDate prazo;

        while (true) {
            prazo = EntradaUtil.lerData(sc, "Prazo final da tarefa (dd/MM/yyyy): ");

            if (prazo.isBefore(LocalDate.now())) {
                System.out.println("O prazo da tarefa não pode ser anterior à data atual.");
                System.out.println("Digite o prazo novamente.\n");
            } else {
                break;
            }
        }

        System.out.println("\nEscolha o projeto da tarefa:");
        ProjetoService.listarProjetos(projetos);

        int idProjeto = EntradaUtil.lerInteiro(sc, "Digite o ID do projeto: ");

        Projeto projetoEscolhido = ProjetoService.buscarProjetoPorId(projetos, idProjeto);

        if (projetoEscolhido == null) {
            System.out.println("Projeto não encontrado. Tarefa não cadastrada.");
            return;
        }

        System.out.println("\nEscolha o responsável pela tarefa:");
        UsuarioService.listarUsuarios(usuarios);

        int idResponsavel = EntradaUtil.lerInteiro(sc, "Digite o ID do responsável: ");

        Usuario responsavel = UsuarioService.buscarUsuarioPorId(usuarios, idResponsavel);

        if (responsavel == null) {
            System.out.println("Responsável não encontrado. Tarefa não cadastrada.");
            return;
        }

        int novoId = tarefas.size() + 1;

        Tarefa novaTarefa = new Tarefa(
                novoId,
                titulo,
                descricao,
                prazo,
                responsavel
        );

        tarefas.add(novaTarefa);
        projetoEscolhido.adicionarTarefa(novaTarefa);

        System.out.println("Tarefa cadastrada com sucesso no projeto: " + projetoEscolhido.getNome());
    }
    
    public static void listarTarefasProjeto(Scanner sc, List<Projeto> projetos) {
        System.out.println("\n=== LISTAR TAREFAS DE UM PROJETO ===");

        ProjetoService.listarProjetos(projetos);

        int idProjetoTarefas = EntradaUtil.lerInteiro(sc, "Digite o ID do projeto: ");

        Projeto projetoTarefas = ProjetoService.buscarProjetoPorId(projetos, idProjetoTarefas);

        if (projetoTarefas != null) {
            System.out.println("\n=== TAREFAS DO PROJETO: " + projetoTarefas.getNome() + " ===");
            projetoTarefas.listarTarefas();
        } else {
            System.out.println("Projeto não encontrado.");
        }
    }
    
    public static void exibirDetalhesTarefa(Scanner sc, List<Tarefa> tarefas) {
        System.out.println("\n=== EXIBIR DETALHES DE UMA TAREFA ===");

        listarTarefas(tarefas);

        int idTarefa = EntradaUtil.lerInteiro(sc, "Digite o ID da tarefa: ");

        Tarefa tarefaEncontrada = buscarTarefaPorId(tarefas, idTarefa);

        if (tarefaEncontrada != null) {
            System.out.println("\n=== DETALHES DA TAREFA ===");
            tarefaEncontrada.exibirDetalhes();
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }
    
}