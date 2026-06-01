package main.service;

import java.util.List;
import java.util.Scanner;
import main.Usuario;
import main.util.EntradaUtil;
import main.PerfilUsuario;

public class UsuarioService {

    public static void listarUsuarios(List<Usuario> usuarios) {
        System.out.println("\n=== USUÁRIOS ===");

        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }
    
    public static Usuario buscarUsuarioPorId(List<Usuario> usuarios, int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }

        return null;
    }
    
    public static boolean cpfJaCadastrado(List<Usuario> usuarios, String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return true;
            }
        }

        return false;
    }

    public static boolean loginJaCadastrado(List<Usuario> usuarios, String login) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login)) {
                return true;
            }
        }

        return false;
    }
 
    public static PerfilUsuario escolherPerfilUsuario(int opcao) {
        switch (opcao) {
            case 1:
                return PerfilUsuario.ADMINISTRADOR;
            case 2:
                return PerfilUsuario.GERENTE;
            case 3:
                return PerfilUsuario.COLABORADOR;
            default:
                return null;
        }
    }
    
    public static void cadastrarUsuario(Scanner sc, List<Usuario> usuarios) {
        System.out.println("\n=== CADASTRAR NOVO USUÁRIO ===");

        String nome = EntradaUtil.lerTextoObrigatorio(sc, "Nome");

        String cpf;
        while (true) {
            cpf = EntradaUtil.lerTextoObrigatorio(sc, "CPF");

            if (!EntradaUtil.cpfValido(cpf)) {
                System.out.println("CPF inválido. Digite um CPF com 11 números.");
            } else if (UsuarioService.cpfJaCadastrado(usuarios, cpf)) {
                System.out.println("CPF já cadastrado. Digite outro CPF.");
            } else {
                break;
            }
        }

        String email = EntradaUtil.lerEmailValido(sc);

        String cargo = EntradaUtil.lerTextoObrigatorio(sc, "Cargo");

        String login;
        while (true) {
            login = EntradaUtil.lerTextoObrigatorio(sc, "Login");

            if (UsuarioService.loginJaCadastrado(usuarios, login)) {
                System.out.println("Login já cadastrado. Digite outro login.");
            } else {
                break;
            }
        }

        String senha = EntradaUtil.lerTextoObrigatorio(sc, "Senha");

        System.out.println("\nEscolha o perfil do usuário:");
        System.out.println("1 - Administrador");
        System.out.println("2 - Gerente");
        System.out.println("3 - Colaborador");

        int opcaoPerfil = main.util.EntradaUtil.lerInteiro(sc, "Opção: ");

        PerfilUsuario perfil = UsuarioService.escolherPerfilUsuario(opcaoPerfil);

        if (perfil == null) {
            System.out.println("Perfil inválido. Usuário não cadastrado.");
            return;
        }

        int novoId = usuarios.size() + 1;

        Usuario novoUsuario = new Usuario(
                novoId,
                nome,
                cpf,
                email,
                cargo,
                login,
                senha,
                perfil
        );

        usuarios.add(novoUsuario);

        System.out.println("Usuário cadastrado com sucesso!");
    }
    
}