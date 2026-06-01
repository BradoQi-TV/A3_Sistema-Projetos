package main.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class EntradaUtil {

    private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static int lerInteiro(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);

            try {
                int numero = Integer.parseInt(sc.nextLine());
                return numero;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite apenas números.");
            }
        }
    }

    public static LocalDate lerData(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);

            try {
                String texto = sc.nextLine();
                return LocalDate.parse(texto, FORMATADOR_DATA);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
            }
        }
    }
    
    public static String lerTextoObrigatorio(Scanner sc, String campo) {
        String texto;

        while (true) {
            System.out.print(campo + ": ");
            texto = sc.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println(campo + " não pode ficar vazio.");
            } else {
                return texto;
            }
        }
    }
    
    public static String lerEmailValido(Scanner sc) {
        String email;

        while (true) {
            System.out.print("E-mail: ");
            email = sc.nextLine().trim();

            if (email.isEmpty()) {
                System.out.println("E-mail não pode ficar vazio.");
            } else if (!email.contains("@") || !email.contains(".")) {
                System.out.println("E-mail inválido. Digite um e-mail válido.");
            } else {
                return email;
            }
        }
    }
    
    public static String lerCpfValido(Scanner sc) {
        String cpf;

        while (true) {
            System.out.print("CPF: ");
            cpf = sc.nextLine().trim();

            String cpfApenasNumeros = cpf.replaceAll("[^0-9]", "");

            if (cpfApenasNumeros.length() != 11) {
                System.out.println("CPF inválido. Digite um CPF com 11 números.");
            } else {
                return cpf;
            }
        }
    }
    
    public static String limparCpf(String cpf) {
        return cpf.replaceAll("[^0-9]", "");
    }

    public static boolean cpfValido(String cpf) {
        String cpfLimpo = limparCpf(cpf);

        if (cpfLimpo.length() != 11) {
            return false;
        }

        if (cpfLimpo.matches("(\\d)\\1{10}")) {
            return false;
        }

        return true;
    }
    
}