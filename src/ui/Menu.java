package ui;

import User.Usuario;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static void opcoesUsuario() {
        System.out.println("A) Mostrar informações da conta");
        System.out.println("B) Alterar o nome");
        System.out.println("C) Excluir a conta");
        System.out.println("\nX) Voltar");
    }

    public static void opcoesTarefas() {
        System.out.println("A) Criar uma tarefa");
        System.out.println("B) Alterar dados de uma tarefa");
        System.out.println("C) Excluir uma tarefa");
        System.out.println("D) Concluir tarefas");
        System.out.println("E) Mostrar tarefas");
        System.out.println("\nX) Voltar");
    }

    public static void opcoesMenu(boolean ehUsuario, int qualUsuario) {
        String primeiroNome;

        if(ehUsuario) {
            int atePrimeiroNome = usuarios.get(qualUsuario).getNome().indexOf(" ");
            if(atePrimeiroNome == -1)
                primeiroNome = usuarios.get(qualUsuario).getNome();
            else
                primeiroNome = usuarios.get(qualUsuario).getNome().substring(0,atePrimeiroNome);

            System.out.printf("Bem vindo %s! Você deseja acessar as:\n", primeiroNome);
            System.out.println("A) configurações da conta");
            System.out.println("B) opções de tarefas");
            System.out.println("\nX) Deslogar");
        }

        else {
            System.out.println("Bom dia! Gostaria de:");
            System.out.println("A) Cadastrar");
            System.out.println("B) Logar");
            System.out.println("\nX) Sair");
        }
    }

    public static String entradaNome() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if(nome.isBlank()) return null;

        return nome;
    }

    public static int cadastrar() {
        System.out.print("Digite o seu ");
        String nome = entradaNome();
        int id = usuarios.size() + 1;
        System.out.printf("Seu ID é '%d'\n", id);
        System.out.println("(Digite enter para continuar)");
        scanner.nextLine();

        usuarios.add(new Usuario(nome,id));
        System.out.println("\n\n\n");

        return id - 1;
    }

    public static int logar() {
        System.out.println("Digite o seu ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n\n\n");

        return id - 1;
    }

    public static int entradaNaoUsuario() {
        while(true) {
            char escolha = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            if (escolha == 'X') return -1;
            else if (escolha < 'A' || escolha > 'B')
                System.out.println("\n\n(Opção inexistente, tente novamente)\n");
            else {
                System.out.println("\n\n\n");
                System.out.println("\n\n(Caso queira cancelar digite 'enter' em qualquer campo)\n");
                switch(escolha) {
                    case 'A': return cadastrar();
                    case 'B': return logar();
                }
                System.out.println("\n\n\n");
            }
        }
    }

    public static void entradaOpcoesTarefas(int qualUsuario) {
        while(true) {
            opcoesTarefas();
            char escolha = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            if (escolha == 'X') return;
            else if (escolha < 'A' || escolha > 'E')
                System.out.println("\n\n(Opção inexistente, tente novamente)\n");
            else {
                System.out.println("\n\n\n");
                System.out.println("\n\n(Caso queira cancelar digite 'enter' em qualquer campo)\n");
                switch(escolha) {
                    case 'A' -> usuarios.get(qualUsuario).criarTarefa();
                    case 'B' -> usuarios.get(qualUsuario).alterarTarefa();
                    case 'C' -> usuarios.get(qualUsuario).excluirTarefa();
                    case 'D' -> usuarios.get(qualUsuario).concluirTarefas();
                    case 'E' -> usuarios.get(qualUsuario).mostrarTarefas();
                }
                System.out.println("\n\n\n");
            }
        }
    }

    public static void mostrarInfConta(int qualUsuario) {
        System.out.println(usuarios.get(qualUsuario));
        System.out.println("\n\n(Digite 'enter' para continuar)\n");
        scanner.nextLine();
        System.out.println("\n\n\n");
    }

    public static void alterarNome(int qualUsuario){
        System.out.print("Digite o novo ");
        String nome = entradaNome();

        usuarios.get(qualUsuario).setNome(nome);
    }

    public static boolean excluirConta(int qualUsuario) {
        System.out.println("Tem certeza?(S/n): ");
        char escolha = scanner.next().toUpperCase().charAt(0);
        if(escolha == 'S') {
            usuarios.remove(qualUsuario);
            System.out.println("Conta excluída com exito");
            return false;
        }
        return true;
    }

    public static boolean entradaopcoesUsuario(int qualUsuario) {
        while(true) {
            opcoesUsuario();
            char escolha = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            if (escolha == 'X') return true;
            else if (escolha < 'A' || escolha > 'C')
                System.out.println("\n\n(Opção inexistente, tente novamente)\n");
            else {
                System.out.println("\n\n\n");
                System.out.println("\n\n(Caso queira cancelar digite 'enter' em qualquer campo)\n");
                switch(escolha) {
                    case 'A' -> mostrarInfConta(qualUsuario);
                    case 'B' -> alterarNome(qualUsuario);
                    case 'C' -> {
                        return excluirConta(qualUsuario);
                    }
                }
                System.out.println("\n\n\n");
            }
        }
    }

    public static boolean entradaSimUsuario(int qualUsuario) {
        while(true) {
            char escolha = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            if (escolha == 'X') return false;
            else if (escolha < 'A' || escolha > 'B')
                System.out.println("\n\n(Opção inexistente, tente novamente)\n");
            else {
                System.out.println("\n\n\n");
                System.out.println("\n\n(Caso queira cancelar digite 'enter' em qualquer campo)\n");
                switch(escolha) {
                    case 'A' -> {
                        return entradaopcoesUsuario(qualUsuario);
                    }
                    case 'B' -> entradaOpcoesTarefas(qualUsuario);
                }
                System.out.println("\n\n\n");
            }
        }
    }

    public static void menuPrincipal() {
        boolean ehUsuario = false;
        int qualUsuario = -1;

        System.out.println("\n\n\n");
        while(true) {
            opcoesMenu(ehUsuario,qualUsuario);
            if(!ehUsuario) {
                qualUsuario = entradaNaoUsuario();
                if(qualUsuario == -1) break;
                ehUsuario = true;
            }
            else {
                ehUsuario = entradaSimUsuario(qualUsuario);
                if (!ehUsuario) qualUsuario = -1;
            }
        }
    }
}
