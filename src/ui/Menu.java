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
            String titulo = entradaTitulo();
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
            String descricao = entradaDescricao();
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
            System.out.println("\nX) Voltar");
            char escolha = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            if(escolha == 'X') {
                System.out.println("\n\n\n");
                return;
            }
            else if(escolha < 'A' || escolha > 'B')
                System.out.println("\n\n(Opção inexistente, tente novamente)\n");

            else {
                System.out.println("\n\n\n");
                System.out.println("\n\n(Caso queira cancelar digite 'enter' em qualquer campo)\n");
                switch (escolha) {
                    case 'A' -> alterarTitulo(qualTarefa);
                    case 'B' -> alterarDescricao(qualTarefa);
                }

                System.out.println("\n\n\n");
                mostrarTarefaAtual(qualTarefa);
                System.out.print("Deseja fazer mais alguma alteração?(S/n): ");
                escolha = scanner.next().toUpperCase().charAt(0);
                scanner.nextLine();
                if (escolha != 'S') break;
            }
        }
    }

    public static void alterarTarefa() {
        String tarefaDesejada;
        int qualTarefa;

        while(true) {
            System.out.println("Digite o título da tarefa que deseja alterar: ");
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
            System.out.println("Digite o título da tarefa que deseja excluir: ");
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

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Concluir Tarefa ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void concluirTarefas() {
        String tarefaDesejada;

        while(true) {
            System.out.println("Digite o título da tarefa que deseja concluir: ");
            tarefaDesejada = scanner.nextLine();
            if(tarefaDesejada.isBlank()) return;

            String erro = UsuarioService.concluirTarefa(usuarios.get(qualUsuario),tarefaDesejada);
            System.out.println(erro);

            if(erro.equals("\n\n(Tarefa concluída com êxito!)\n")) {
                System.out.println("Deseja concluir mais alguma tarefa?(S/n): ");
                char escolha = scanner.next().toUpperCase().charAt(0);
                scanner.nextLine();
                if (escolha != 'S') break;
            }
        }
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Mostrar Tarefa ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void mostrarTarefasCondicional(char escolha) {
        boolean existeTarefa = false;

        for(int i = 0; i < usuarios.get(qualUsuario).getGerenciadorTarefas().size(); i++) {
            boolean andamento = usuarios.get(qualUsuario).getGerenciadorTarefas().get(i).getCourse();
            switch(escolha) {
                case 'A' -> {
                    if(andamento) {
                        Menu.mostrarTarefaAtual(i);
                        existeTarefa = true;
                    }
                }

                case 'B' -> {
                    if(!andamento) {
                        Menu.mostrarTarefaAtual(i);
                        existeTarefa = true;
                    }
                }

                case 'C' -> {
                    Menu.mostrarTarefaAtual(i);
                    existeTarefa = true;
                }
            }
        }

        if(!existeTarefa) System.out.println("\n\n(Nenhuma tarefa foi encontrada)\n");
    }

    public static void mostrarTarefas() {
        while(true) {
            System.out.println("Você quer ver: ");
            System.out.println("A) As tarefas concluídas");
            System.out.println("B) As tarefas pendentes");
            System.out.println("C) Ambas");
            System.out.println("\nX) Voltar");
            char escolha = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            if(escolha == 'X') {
                System.out.println("\n\n\n");
                return;
            }
            else if (escolha < 'A' || escolha > 'C')
                System.out.println("\n\n(Opção inexistente, tente novamente)\n");
            else {
                System.out.println("\n\n\n");
                mostrarTarefasCondicional(escolha);
                System.out.println("\n\n(Digite 'enter' para continuar)\n");
                scanner.nextLine();
                System.out.println("\n\n\n");
                System.out.print("Deseja utilizar outra das opções apresentadas?(S/n): ");
                escolha = scanner.next().toUpperCase().charAt(0);
                scanner.nextLine();

                System.out.println("\n\n\n");
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
                    case 'D' -> concluirTarefas();
                    case 'E' -> mostrarTarefas();
                }
                System.out.println("\n\n\n");
            }
        }
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Mostrar Informações da Conta ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void mostrarInfConta() {
        System.out.println(usuarios.get(qualUsuario));
        System.out.println("\n\n(Digite 'enter' para continuar)\n");
        scanner.nextLine();
        System.out.println("\n\n\n");
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Alterar Nome ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void alterarNome() {
        System.out.print("Digite o novo ");
        String nome = Menu.entradaNome();
        if(nome == null) return;

        usuarios.get(qualUsuario).setNome(nome);
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// excluir Conta ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static boolean excluirConta() {
        System.out.println("Tem certeza?(S/n): ");
        char escolha = scanner.next().toUpperCase().charAt(0);

        if(escolha == 'S') {
            usuarios.remove(qualUsuario);
            return false;
        }

        return true;
    }

    public static boolean entradaOpcoesUsuario() {
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
                    case 'A' -> mostrarInfConta();
                    case 'B' -> alterarNome();
                    case 'C' -> {
                        System.out.println("\n\n\n");
                        return excluirConta();
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
                        return entradaOpcoesUsuario();
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
