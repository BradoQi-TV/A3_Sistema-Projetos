package main;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import main.util.EntradaUtil;
import main.service.UsuarioService;
import main.service.EquipeService;
import main.service.ProjetoService;
import main.service.TarefaService;
import main.service.RelatorioService;


public class Main {

    public static void main(String[] args) {

    	Usuario admin = new Usuario(
    	        1,
    	        "André Carvalho",
    	        "123.456.789-00",
    	        "andre@email.com",
    	        "Administrador do Sistema",
    	        "andre.admin",
    	        "123456",
    	        PerfilUsuario.ADMINISTRADOR
    	);

    	Usuario gerente = new Usuario(
    	        2,
    	        "Usuário Gerente",
    	        "987.654.321-00",
    	        "gerente@email.com",
    	        "Gerente de Projetos",
    	        "gerente",
    	        "123456",
    	        PerfilUsuario.GERENTE
    	);

    	Usuario colaborador = new Usuario(
    	        3,
    	        "Usuário Colaborador",
    	        "111.222.333-44",
    	        "colaborador@email.com",
    	        "Desenvolvedor",
    	        "colaborador",
    	        "123456",
    	        PerfilUsuario.COLABORADOR
    	);
    	

        Equipe equipeDesenvolvimento = new Equipe(
                1,
                "Equipe de Desenvolvimento",
                "Equipe responsável pelo desenvolvimento do sistema."
        );

        equipeDesenvolvimento.adicionarMembro(admin);
        equipeDesenvolvimento.adicionarMembro(gerente);
        equipeDesenvolvimento.adicionarMembro(colaborador);

        Projeto projeto = new Projeto(
                1,
                "Sistema de Gerenciamento de Projetos",
                "Sistema para organizar usuários, projetos e tarefas.",
                LocalDate.of(2026, 5, 19),
                LocalDate.of(2026, 6, 5),
                StatusProjeto.EM_ANDAMENTO,
                gerente
        );

        projeto.adicionarEquipe(equipeDesenvolvimento);

        Tarefa tarefa1 = new Tarefa(
                1,
                "Criar tela de cadastro",
                "Desenvolver a estrutura inicial do cadastro de usuários.",
                LocalDate.of(2026, 6, 5),
                admin
        );

        Tarefa tarefa2 = new Tarefa(
                2,
                "Criar banco de dados",
                "Montar as tabelas principais no MySQL.",
                LocalDate.of(2026, 6, 5),
                colaborador
        );

        // Teste de alteracao controle de andamento
        tarefa1.alterarStatus(StatusTarefa.EM_ANDAMENTO);
        tarefa2.alterarStatus(StatusTarefa.CONCLUIDA);

        projeto.adicionarTarefa(tarefa1);
        projeto.adicionarTarefa(tarefa2);
        
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(admin);
        usuarios.add(gerente);
        usuarios.add(colaborador);

        List<Equipe> equipes = new ArrayList<>();
        equipes.add(equipeDesenvolvimento);

        List<Projeto> projetos = new ArrayList<>();
        projetos.add(projeto);

        List<Tarefa> tarefas = new ArrayList<>();
        tarefas.add(tarefa1);
        tarefas.add(tarefa2);
        
        
        
// Listagens principais
        
        Scanner sc = new Scanner(System.in);

        int opcao;

        do {
        	exibirMenu();
        	opcao = EntradaUtil.lerInteiro(sc, "Escolha uma opção: ");

            switch (opcao) {
                case 1:
                	UsuarioService.listarUsuarios(usuarios);
                    break;

                case 2:
                	EquipeService.listarEquipes(equipes);
                    break;

                case 3:
                	ProjetoService.listarProjetos(projetos);
                    break;

                case 4:
                	TarefaService.listarTarefas(tarefas);
                    break;

                case 5:
                    TarefaService.listarTarefasPorStatus(tarefas, StatusTarefa.EM_ANDAMENTO);
                    TarefaService.listarTarefasPorStatus(tarefas, StatusTarefa.CONCLUIDA);
                    TarefaService.listarTarefasPorStatus(tarefas, StatusTarefa.PENDENTE);
                    TarefaService.listarTarefasPorStatus(tarefas, StatusTarefa.CANCELADA);
                    break;

                case 6:
                    EquipeService.listarMembrosEquipe(sc, equipes);
                    break;

                case 7:
                    ProjetoService.listarEquipesProjeto(sc, projetos);
                    break;

                case 8:
                    EquipeService.listarProjetosEquipe(sc, equipes);
                    break;
                    
                case 9:
                    TarefaService.exibirDetalhesTarefa(sc, tarefas);
                    break;
                    
                case 10:
                    TarefaService.alterarStatusTarefa(sc, tarefas);
                    break;

                case 11:
                    ProjetoService.alterarStatusProjeto(sc, projetos);
                    break;

                case 12:
                    TarefaService.cadastrarTarefa(sc, tarefas, usuarios, projetos);
                    break;

                case 13:
                    ProjetoService.cadastrarProjeto(sc, projetos, usuarios);
                    break;

                case 14:
                    TarefaService.listarTarefasProjeto(sc, projetos);
                    break;

                case 15:
                    UsuarioService.cadastrarUsuario(sc, usuarios);
                    break;

                case 16:
                    EquipeService.cadastrarEquipe(sc, equipes);
                    break;

                case 17:
                    EquipeService.adicionarMembroEquipe(sc, equipes, usuarios);
                    break;

                case 18:
                    ProjetoService.vincularEquipeProjeto(sc, projetos, equipes);
                    break;

                case 19:
                    RelatorioService.gerarRelatorioGeral(usuarios, equipes, projetos, tarefas);
                    break;
                    
                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (opcao != 0);

        sc.close();
    }
    
    public static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE GERENCIAMENTO DE PROJETOS ===");
        System.out.println("1 - Listar usuários");
        System.out.println("2 - Listar equipes");
        System.out.println("3 - Listar projetos");
        System.out.println("4 - Listar tarefas");
        System.out.println("5 - Listar tarefas por status");
        System.out.println("6 - Listar membros de uma equipe");
        System.out.println("7 - Listar equipes de um projeto");
        System.out.println("8 - Listar projetos de uma equipe");
        System.out.println("9 - Exibir detalhes de uma tarefa");
        System.out.println("10 - Alterar status da tarefa");
        System.out.println("11 - Alterar status do projeto");
        System.out.println("12 - Cadastrar nova tarefa");
        System.out.println("13 - Cadastrar novo projeto");
        System.out.println("14 - Listar tarefas de um projeto");
        System.out.println("15 - Cadastrar novo usuário");
        System.out.println("16 - Cadastrar nova equipe");
        System.out.println("17 - Adicionar membro à equipe");
        System.out.println("18 - Vincular equipe a projeto");
        System.out.println("19 - Relatório geral de acompanhamento");
        System.out.println("0 - Sair");
    }
}