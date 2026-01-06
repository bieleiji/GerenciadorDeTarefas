package ui;

import User.Usuario;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static void opcoesMenu(boolean ehUsuario, int qualUsuario) {
        if(ehUsuario) {
            int atePrimeiroNome = usuarios.get(qualUsuario).getNome().indexOf(" ");
            String primeiroNome = usuarios.get(qualUsuario).getNome().substring(0,atePrimeiroNome);

            System.out.printf("Bem vindo %s! Você quer:\n", primeiroNome);
            System.out.println("A) Criar uma tarefa");
            System.out.println("B) Alterar dados de uma tarefa");
            System.out.println("C) Excluir uma tarefa");
            System.out.println("D) Concluir uma tarefa");
            System.out.println("E) Mostrar tarefas");
            System.out.println("\nX) Deslogar");
        }

        else {
            System.out.println("Bom dia! Gostaria de:");
            System.out.println("A) Cadastrar-se");
            System.out.println("B) Logar");
            System.out.println("\nX) Sair");
        }
    }

    public static void menuPrincipal() {
        boolean ehUsuario = false;
        int qualUsuario = -1;

        //Incompleto, porém em produção
        /*
        while(true) {
            opcoesMenu(ehUsuario,qualUsuario);
            if(!ehUsuario) {
                qualUsuario = entradaNaoUsuario();
                ehUsuario = true;
            }
            else {
                ehUsuario = entradaSimUsuario();
                qualUsuario = -1;
            }
        }

         */
    }
}
