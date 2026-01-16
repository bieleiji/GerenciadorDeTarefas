package User;

import ui.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Usuario {
    static Scanner scanner = new Scanner(System.in);

    private static ArrayList<GerenciadorTarefas<Tarefa, Boolean>> gerenciadorTarefas = new ArrayList<>();
    private String nome;
    private final int id;

    public Usuario(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    @Override
    public String toString() {
        return  "Informações da conta:\n" +
                "Nome: " + this.nome + '\n' +
                "ID: " + this.id;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<GerenciadorTarefas<Tarefa, Boolean>> getGerenciadorTarefas() {
        return gerenciadorTarefas;
    }

    public static void setGerenciadorTarefas(ArrayList<GerenciadorTarefas<Tarefa, Boolean>> gerenciadorTarefas) {
        Usuario.gerenciadorTarefas = gerenciadorTarefas;
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Opções de Tarefa ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Criar Tarefa ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void criarTarefa(Tarefa tarefa) {
        gerenciadorTarefas.add(new GerenciadorTarefas<>(tarefa,false));
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Alterar Tarefa ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void alterarTitulo(String titulo, int qualTarefa) {
        gerenciadorTarefas.get(qualTarefa).getTask().setTitulo(titulo);
    }

    public static void alterarDescricao(String descricao, int qualTarefa) {
        gerenciadorTarefas.get(qualTarefa).getTask().setDescricao(descricao);
    }


    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Excluir Tarefa ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void excluirTarefa(int qualTarefa) {
        gerenciadorTarefas.remove(qualTarefa);
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Concluir Tarefa ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void concluirTarefas() {
        int qualTarefa;

        while(true) {
            mostrarTarefasCondicional('B');
            System.out.println("Digite o número da tarefa que deseja concluir: ");
            qualTarefa = scanner.nextInt();
            if(qualTarefa < 0 || qualTarefa > gerenciadorTarefas.size())
                System.out.println("\n\n(Tarefa inexistente, tente novamente)\n");
            else {
                System.out.println("\n\n(Tarefa concluída)\n");
                gerenciadorTarefas.get(--qualTarefa).setComplete(true);

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

    public void mostrarTarefasCondicional(char escolha) {
        for(int i = 0; i < gerenciadorTarefas.size(); i++) {
            boolean andamento = gerenciadorTarefas.get(i).getCourse();
            switch(escolha) {
                case 'A' -> {
                    if(andamento) Menu.mostrarTarefaAtual(i);
                }

                case 'B' -> {
                    if(!andamento) Menu.mostrarTarefaAtual(i);
                }

                case 'C' -> Menu.mostrarTarefaAtual(i);

            }
        }
    }

    public void mostrarTarefas() {
        while(true) {
            System.out.println("Você quer ver: ");
            System.out.println("A) As tarefas concluídas");
            System.out.println("B) As tarefas pendentes");
            System.out.println("C) Ambas");
            char escolha = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();
            if (Character.isWhitespace(escolha)) return;
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
                if (escolha != 'S') break;
            }
        }
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Opções de Usuário ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Mostrar Informações da Conta ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void mostrarInfConta() {
        System.out.println(this);
        System.out.println("\n\n(Digite 'enter' para continuar)\n");
        scanner.nextLine();
        System.out.println("\n\n\n");
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Alterar Nome ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void alterarNome() {
        System.out.print("Digite o novo ");
        String nome = Menu.entradaNome();

        this.setNome(nome);
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// excluir Conta ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public char excluirConta() {
        System.out.println("Tem certeza?(S/n): ");
        return scanner.next().toUpperCase().charAt(0);
    }
}
