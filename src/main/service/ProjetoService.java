package main.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import main.Equipe;
import main.PerfilUsuario;
import main.Projeto;
import main.StatusProjeto;
import main.Usuario;
import main.util.EntradaUtil;


public class ProjetoService {

    public static void listarProjetos(List<Projeto> projetos) {
        System.out.println("\n=== PROJETOS ===");

        for (Projeto projeto : projetos) {
            System.out.println(projeto);
        }
    }

    public static Projeto buscarProjetoPorId(List<Projeto> projetos, int id) {
        for (Projeto projeto : projetos) {
            if (projeto.getId() == id) {
                return projeto;
            }
        }

        return null;
    }
    
    public static void listarEquipesProjeto(Scanner sc, List<Projeto> projetos) {
        System.out.println("\n=== LISTAR EQUIPES DE UM PROJETO ===");

        listarProjetos(projetos);

        int idProjetoEquipes = EntradaUtil.lerInteiro(sc, "Digite o ID do projeto: ");

        Projeto projetoEquipes = buscarProjetoPorId(projetos, idProjetoEquipes);

        if (projetoEquipes != null) {
            System.out.println("\n=== EQUIPES DO PROJETO ===");
            EquipeService.listarEquipes(projetoEquipes.getEquipes());
        } else {
            System.out.println("Projeto não encontrado.");
        }
    }
    
    public static StatusProjeto escolherStatusProjeto(int opcao) {
        switch (opcao) {
            case 1:
                return StatusProjeto.PLANEJADO;
            case 2:
                return StatusProjeto.EM_ANDAMENTO;
            case 3:
                return StatusProjeto.CONCLUIDO;
            case 4:
                return StatusProjeto.CANCELADO;
            default:
                return null;
        }
    }
    
    public static void alterarStatusProjeto(Scanner sc, List<Projeto> projetos) {
        System.out.println("\n=== ALTERAR STATUS DO PROJETO ===");

        listarProjetos(projetos);

        int idProjeto = EntradaUtil.lerInteiro(sc, "Digite o ID do projeto: ");

        Projeto projetoEscolhido = buscarProjetoPorId(projetos, idProjeto);

        if (projetoEscolhido == null) {
            System.out.println("Projeto não encontrado.");
            return;
        }

        System.out.println("\nEscolha o novo status do projeto:");
        System.out.println("1 - Planejado");
        System.out.println("2 - Em andamento");
        System.out.println("3 - Concluído");
        System.out.println("4 - Cancelado");

        int opcaoStatus = EntradaUtil.lerInteiro(sc, "Opção: ");

        StatusProjeto novoStatus = escolherStatusProjeto(opcaoStatus);

        if (novoStatus == null) {
            System.out.println("Status inválido. Alteração cancelada.");
            return;
        }

        projetoEscolhido.alterarStatus(novoStatus);

        System.out.println("Status do projeto alterado com sucesso!");
    }
    
    public static void vincularEquipeProjeto(Scanner sc, List<Projeto> projetos, List<Equipe> equipes) {
        System.out.println("\n=== VINCULAR EQUIPE A PROJETO ===");

        listarProjetos(projetos);

        int idProjeto = EntradaUtil.lerInteiro(sc, "Digite o ID do projeto: ");

        Projeto projetoEscolhido = buscarProjetoPorId(projetos, idProjeto);

        if (projetoEscolhido == null) {
            System.out.println("Projeto não encontrado.");
            return;
        }

        EquipeService.listarEquipes(equipes);

        int idEquipe = EntradaUtil.lerInteiro(sc, "Digite o ID da equipe: ");

        Equipe equipeEscolhida = EquipeService.buscarEquipePorId(equipes, idEquipe);

        if (equipeEscolhida == null) {
            System.out.println("Equipe não encontrada.");
            return;
        }

        boolean vinculou = projetoEscolhido.adicionarEquipe(equipeEscolhida);

        if (vinculou) {
            System.out.println("Equipe vinculada ao projeto com sucesso!");
        } else {
            System.out.println("Esta equipe já está vinculada a este projeto.");
        }
    }
    
    public static void listarGerentes(List<Usuario> usuarios) {
        System.out.println("\n=== GERENTES DISPONÍVEIS ===");

        boolean encontrou = false;

        for (Usuario usuario : usuarios) {
            if (usuario.getPerfil() == PerfilUsuario.GERENTE) {
                System.out.println(usuario);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum gerente cadastrado.");
        }
    }

    public static Usuario buscarGerentePorId(List<Usuario> usuarios, int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id && usuario.getPerfil() == PerfilUsuario.GERENTE) {
                return usuario;
            }
        }

        return null;
    }
    
    public static void cadastrarProjeto(Scanner sc, List<Projeto> projetos, List<Usuario> usuarios) {

        System.out.println("\n=== CADASTRAR NOVO PROJETO ===");

        String nome = EntradaUtil.lerTextoObrigatorio(sc, "Nome do projeto");
        String descricao = EntradaUtil.lerTextoObrigatorio(sc, "Descrição");

        LocalDate dataInicio;
        LocalDate dataTerminoPrevisto;

        while (true) {
        	dataInicio = EntradaUtil.lerData(sc, "Data de início (dd/MM/yyyy): ");
        	dataTerminoPrevisto = EntradaUtil.lerData(sc, "Data de término previsto (dd/MM/yyyy): ");

            if (dataInicio.isBefore(LocalDate.now())) {
                System.out.println("A data de início não pode ser anterior à data atual.");
                System.out.println("Digite as datas novamente.\n");
            } else if (dataTerminoPrevisto.isBefore(dataInicio)) {
                System.out.println("A data de término previsto não pode ser anterior à data de início.");
                System.out.println("Digite as datas novamente.\n");
            } else {
                break;
            }
        }

        StatusProjeto status = StatusProjeto.PLANEJADO;
        
        System.out.println("\nEscolha o gerente responsável:");
        listarGerentes(usuarios);

        int idGerente = EntradaUtil.lerInteiro(sc, "Digite o ID do gerente: ");

        Usuario gerente = buscarGerentePorId(usuarios, idGerente);

        if (gerente == null) {
            System.out.println("Gerente não encontrado ou usuário não possui perfil de gerente. Projeto não cadastrado.");
            return;
        }

        int novoId = projetos.size() + 1;

        Projeto novoProjeto = new Projeto(
                novoId,
                nome,
                descricao,
                dataInicio,
                dataTerminoPrevisto,
                status,
                gerente
        );

        projetos.add(novoProjeto);

        System.out.println("Projeto cadastrado com sucesso!");
    }
    
    
}