package main.service;

import java.util.List;
import java.util.Scanner;
import main.Equipe;
import main.Usuario;
import main.util.EntradaUtil;



public class EquipeService {

	public static void listarEquipes(List<Equipe> equipes) {
	    System.out.println("\n=== EQUIPES ===");

	    for (Equipe equipe : equipes) {
	        System.out.println(equipe);
	    }
	}
	
	public static Equipe buscarEquipePorId(List<Equipe> equipes, int id) {
	    for (Equipe equipe : equipes) {
	        if (equipe.getId() == id) {
	            return equipe;
	        }
	    }

	    return null;
	}
	
	public static void cadastrarEquipe(Scanner sc, List<Equipe> equipes) {
	    System.out.println("\n=== CADASTRAR NOVA EQUIPE ===");

	    System.out.print("Nome da equipe: ");
	    String nome = sc.nextLine();

	    System.out.print("Descrição da equipe: ");
	    String descricao = sc.nextLine();

	    int novoId = equipes.size() + 1;

	    Equipe novaEquipe = new Equipe(
	            novoId,
	            nome,
	            descricao
	    );

	    equipes.add(novaEquipe);

	    System.out.println("Equipe cadastrada com sucesso!");
	}

	public static void adicionarMembroEquipe(Scanner sc, List<Equipe> equipes, List<Usuario> usuarios) {
	    System.out.println("\n=== ADICIONAR MEMBRO À EQUIPE ===");

	    System.out.println("\nEquipes disponíveis:");
	    EquipeService.listarEquipes(equipes);

	    int idEquipe = EntradaUtil.lerInteiro(sc, "Digite o ID da equipe: ");

	    Equipe equipeEscolhida = EquipeService.buscarEquipePorId(equipes, idEquipe);

	    if (equipeEscolhida == null) {
	        System.out.println("Equipe não encontrada.");
	        return;
	    }

	    System.out.println("\nUsuários disponíveis:");
	    UsuarioService.listarUsuarios(usuarios);

	    int idUsuario = EntradaUtil.lerInteiro(sc, "Digite o ID do usuário que será adicionado: ");

	    Usuario usuarioEscolhido = UsuarioService.buscarUsuarioPorId(usuarios, idUsuario);

	    if (usuarioEscolhido == null) {
	        System.out.println("Usuário não encontrado.");
	        return;
	    }

	    boolean adicionou = equipeEscolhida.adicionarMembro(usuarioEscolhido);

	    if (adicionou) {
	        System.out.println("Membro adicionado à equipe com sucesso!");
	    } else {
	        System.out.println("Este usuário já é membro desta equipe.");
	    }
	}
	
	public static void listarMembrosEquipe(Scanner sc, List<Equipe> equipes) {
	    System.out.println("\n=== LISTAR MEMBROS DE UMA EQUIPE ===");

	    listarEquipes(equipes);

	    int idEquipeMembros = EntradaUtil.lerInteiro(sc, "Digite o ID da equipe: ");

	    Equipe equipeMembros = buscarEquipePorId(equipes, idEquipeMembros);

	    if (equipeMembros != null) {
	        System.out.println("\n=== MEMBROS DA EQUIPE ===");
	        equipeMembros.listarMembros();
	    } else {
	        System.out.println("Equipe não encontrada.");
	    }
	}
	
	public static void listarProjetosEquipe(Scanner sc, List<Equipe> equipes) {
	    System.out.println("\n=== LISTAR PROJETOS DE UMA EQUIPE ===");

	    listarEquipes(equipes);

	    int idEquipeProjetos = EntradaUtil.lerInteiro(sc, "Digite o ID da equipe: ");

	    Equipe equipeProjetos = buscarEquipePorId(equipes, idEquipeProjetos);

	    if (equipeProjetos != null) {
	        System.out.println("\n=== PROJETOS DA EQUIPE ===");
	        equipeProjetos.listarProjetos();
	    } else {
	        System.out.println("Equipe não encontrada.");
	    }
	}
	
	
}