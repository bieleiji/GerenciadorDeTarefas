package ui;

import User.Usuario;
import service.UsuarioService;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static int qualUsuario;

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

    public static void opcoesMenu(boolean ehUsuario) {
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
        if(nome == null) return -1;
        int id = usuarios.size() + 1;
        System.out.printf("Seu ID é '%d'\n", id);
        System.out.println("(Digite enter para continuar)");
        scanner.nextLine();

        usuarios.add(new Usuario(nome,id));
        System.out.println("\n\n\n");

        return id - 1;
    }

    public static int logar() {
        String id;

        while(true) {
            System.out.println("Digite o seu ID: ");
            id = scanner.nextLine();
            if(id.isBlank()) return -1;

            if(Integer.parseInt(id) < 0 || Integer.parseInt(id) > usuarios.size())
                System.out.println("\n\n(Usuário não encontrado, tente novamente)\n");
            else break;
        }
        System.out.println("\n\n\n");

        return Integer.parseInt(id) - 1;
    }

    public static int entradaNaoUsuario() {
        while(true) {
            char escolha = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            if (escolha == 'X') return -2;
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

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Criar Tarefa ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static String entradaTitulo() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        if(titulo.isBlank()) return null;

        return titulo;
    }

    public static String entradaDescricao() {
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        if(descricao.isBlank()) return null;

        return descricao;
    }

    public static void criarTarefa() {
        while(true) {
            System.out.println("Digite o:");

            String titulo = entradaTitulo();
            if (titulo == null) break;

            System.out.println("\n\n(A descrição não é obrigatória, então digite 'enter' caso não queira fazer esta parte)\n");
            String descricao = entradaDescricao();

            String erro = UsuarioService.criarTarefa(usuarios.get(qualUsuario), titulo, descricao);
            System.out.println(erro);
            if(erro.equals("\n\n(tarefa criada com sucesso!!!)\n")) break;
        }
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Alterar Tarefa ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void mostrarTarefaAtual(int qualTarefa) {
        String titulo = usuarios.get(qualUsuario).getGerenciadorTarefas().get(qualTarefa).getTask().getTitulo();
        String descricao = usuarios.get(qualUsuario).getGerenciadorTarefas().get(qualTarefa).getTask().getDescricao();
        String andamento = usuarios.get(qualUsuario).getGerenciadorTarefas().get(qualTarefa).getCourse() ? "completa" : "pendente";

        System.out.println("/*******************************************************/");
        System.out.printf("%dª tarefa:\n\n",qualTarefa+1);
        System.out.printf("Título: %s\n",titulo);
        if(descricao != null) System.out.printf("Descrição: %s\n",descricao);
        System.out.printf("Andamento: %s\n\n",andamento);
    }

    public static void alterarTitulo(int qualTarefa) {
        while(true) {
            mostrarTarefaAtual(qualTarefa);

            System.out.print("Digite o novo ");
            String titulo = Menu.entradaTitulo();
            if (titulo == null) return;

            String erro = UsuarioService.alterarTitulo(usuarios.get(qualUsuario), titulo, qualTarefa);

            System.out.println(erro);
            if (erro.equals("\n\n(Título alterado com sucesso!!!)\n"))
                break;
        }
    }

    public static void alterarDescricao(int qualTarefa) {
        while(true) {
            mostrarTarefaAtual(qualTarefa);

            System.out.print("Digite a nova ");
            String descricao = Menu.entradaDescricao();
            if (descricao == null) return;

            String erro = UsuarioService.alterarDescricao(descricao, qualTarefa);

            System.out.println(erro);
            if (erro.equals("\n\n(Descrição alterada com sucesso!!!)\n"))
                break;
        }
    }

    public static void opcoesAlteracao(int qualTarefa) {
        while(true) {
            mostrarTarefaAtual(qualTarefa);
            System.out.println("Você quer mudar:");
            System.out.println("A) O título\nB) A descrição");
            char escolha = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            if(Character.isWhitespace(escolha)) return;
            else if(escolha < 'A' || escolha > 'B')
                System.out.println("\n\n(Opção inexistente, tente novamente)\n");

            else {
                System.out.println("\n\n\n");
                System.out.println("\n\n(Caso queira cancelar digite 'enter' em qualquer campo)\n");
                switch(escolha) {
                    case 'A' -> alterarTitulo(qualTarefa);
                    case 'B' -> alterarDescricao(qualTarefa);
                }
            }
            System.out.println("\n\n\n");
            mostrarTarefaAtual(qualTarefa);
            System.out.print("Deseja fazer mais alguma alteração?(S/n): ");
            escolha = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();
            if(escolha != 'S') break;
        }
    }

    public static void alterarTarefa() {
        String tarefaDesejada;
        int qualTarefa;

        while(true) {
            System.out.println("Digite o nome da tarefa que deseja alterar: ");
            tarefaDesejada = scanner.nextLine();
            if(tarefaDesejada.isBlank()) return;

            qualTarefa = UsuarioService.ehTituloRepetido(usuarios.get(qualUsuario), tarefaDesejada);
            if(qualTarefa == -1)
                System.out.println("\n\n(Tarefa inexistente, tente novamente)\n");
            else break;
        }

        opcoesAlteracao(qualTarefa);
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Excluir Tarefa ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void excluirTarefa() {
        String tarefaDesejada;

        while(true) {
            System.out.println("Digite o nome da tarefa que deseja alterar: ");
            tarefaDesejada = scanner.nextLine();
            if(tarefaDesejada.isBlank()) return;

            String erro = UsuarioService.excluirTarefa(usuarios.get(qualUsuario),tarefaDesejada);
            System.out.println(erro);

            if(erro.equals("\n\n(Tarefa excluída com êxito!)\n")) {
                System.out.println("Deseja excluir mais alguma tarefa?(S/n): ");
                char escolha = scanner.next().toUpperCase().charAt(0);
                scanner.nextLine();
                if (escolha != 'S') break;
            }
        }
    }

    public static void entradaOpcoesTarefas() {
        while(true) {
            opcoesTarefas();
            char escolha = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            if (escolha == 'X') {
                System.out.println("\n\n\n");
                return;
            }
            else if (escolha < 'A' || escolha > 'E')
                System.out.println("\n\n(Opção inexistente, tente novamente)\n");
            else {
                System.out.println("\n\n\n");
                System.out.println("\n\n(Caso queira cancelar digite 'enter' em qualquer campo)\n");
                switch(escolha) {
                    case 'A' -> criarTarefa();
                    case 'B' -> alterarTarefa();
                    case 'C' -> excluirTarefa();
                    case 'D' -> usuarios.get(qualUsuario).concluirTarefas();
                    case 'E' -> usuarios.get(qualUsuario).mostrarTarefas();
                }
                System.out.println("\n\n\n");
            }
        }
    }

    public static boolean entradaopcoesUsuario() {
        while(true) {
            opcoesUsuario();
            char escolha = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            if (escolha == 'X') {
                System.out.println("\n\n\n");
                return true;
            }
            else if (escolha < 'A' || escolha > 'C')
                System.out.println("\n\n(Opção inexistente, tente novamente)\n");
            else {
                System.out.println("\n\n\n");
                System.out.println("\n\n(Caso queira cancelar digite 'enter' em qualquer campo)\n");
                switch(escolha) {
                    case 'A' -> usuarios.get(qualUsuario).mostrarInfConta();
                    case 'B' -> usuarios.get(qualUsuario).alterarNome();
                    case 'C' -> {
                        char opcao = usuarios.get(qualUsuario).excluirConta();
                        if(opcao == 'S') {
                            usuarios.remove(qualUsuario);
                            System.out.println("Conta excluída com exito");
                            return false;
                        }
                    }
                }
                System.out.println("\n\n\n");
            }
        }
    }

    public static boolean entradaSimUsuario() {
        while(true) {
            char escolha = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            if (escolha == 'X') {
                System.out.println("\n\n\n");
                return false;
            }
            else if (escolha < 'A' || escolha > 'B')
                System.out.println("\n\n(Opção inexistente, tente novamente)\n");
            else {
                System.out.println("\n\n\n");
                System.out.println("\n\n(Caso queira cancelar digite 'enter' em qualquer campo)\n");
                switch(escolha) {
                    case 'A' -> {
                        return entradaopcoesUsuario();
                    }
                    case 'B' -> {
                        entradaOpcoesTarefas();
                        return true;
                    }
                }
                System.out.println("\n\n\n");
            }
        }
    }

    public static void menuPrincipal() {
        boolean ehUsuario = false;
        qualUsuario = -1;

        System.out.println("\n\n\n");
        while(true) {
            opcoesMenu(ehUsuario);
            if(!ehUsuario) {
                qualUsuario = entradaNaoUsuario();
                if(qualUsuario == -2) break;
                else if(qualUsuario == -1) continue;
                ehUsuario = true;
            }
            else {
                ehUsuario = entradaSimUsuario();
                if (!ehUsuario) qualUsuario = -1;
            }
        }
    }
}
