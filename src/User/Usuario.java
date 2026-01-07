package User;

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

    //funções de tarefas:

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

    public void criarTarefa() {
        System.out.println("Digite o:");

        String titulo = entradaTitulo();
        if(titulo == null) return;

        System.out.println("\n\n(A descrição não é obrigatória, então digite 'enter' caso não queira fazer esta parte)\n");
        String descricao = entradaDescricao();

        GerenciadorTarefas<Tarefa,Boolean> novaTarefa = new GerenciadorTarefas<>(new Tarefa(titulo,descricao),false);
        gerenciadorTarefas.add(novaTarefa);
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Alterar Tarefa ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void mostrarTarefaAtual(int qualTarefa) {
        String titulo = gerenciadorTarefas.get(qualTarefa).getTask().getTitulo();
        String descricao = gerenciadorTarefas.get(qualTarefa).getTask().getDescricao();
        String andamento = gerenciadorTarefas.get(qualTarefa).getCourse() ? "completa" : "pendente";

        System.out.println("/*******************************************************/");
        System.out.printf("%dª tarefa:\n\n",qualTarefa+1);
        System.out.printf("Título: \n%s\n",titulo);
        if(descricao != null) System.out.printf("Descrição: \n%s\n",descricao);
        System.out.printf("Andamento: \n%s\n\n",andamento);
    }

    public static void alterarTitulo(int qualTarefa) {
        mostrarTarefaAtual(qualTarefa);

        System.out.print("Digite o novo ");
        String titulo = entradaTitulo();
        if(titulo == null) return;

        gerenciadorTarefas.get(qualTarefa).getTask().setTitulo(titulo);
    }

    public static void alterarDescricao(int qualTarefa) {
        mostrarTarefaAtual(qualTarefa);

        System.out.print("Digite a nova ");
        String descricao = entradaDescricao();
        if(descricao == null) return;

        gerenciadorTarefas.get(qualTarefa).getTask().setDescricao(descricao);
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

    public void alterarTarefa() {
        int qualTarefa;

        while(true) {
            mostrarTarefasCondicional('C');
            System.out.println("Digite o número da tarefa que deseja alterar: ");
            qualTarefa = scanner.nextInt();
            if(qualTarefa < 0 || qualTarefa > gerenciadorTarefas.size())
                System.out.println("\n\n(Tarefa inexistente, tente novamente)\n");
            else break;
        }

        opcoesAlteracao(qualTarefa-1);
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Excluir Tarefa ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void excluirTarefa() {
        while(true) {
            mostrarTarefasCondicional('C');
            System.out.println("Digite o número da tarefa que deseja excluir: ");
            int qualTarefa = scanner.nextInt();
            if(qualTarefa < 0 || qualTarefa > gerenciadorTarefas.size())
                System.out.println("\n\n(Tarefa inexistente, tente novamente)\n");
            else {
                System.out.println("\n\n(Tarefa excluída)\n");
                gerenciadorTarefas.remove(qualTarefa);

                System.out.println("Deseja excluir mais alguma tarefa?(S/n): ");
                char escolha = scanner.next().toUpperCase().charAt(0);
                scanner.nextLine();
                if(escolha != 'S') break;
            }
        }
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
                    if(andamento) mostrarTarefaAtual(i);
                }

                case 'B' -> {
                    if(!andamento) mostrarTarefaAtual(i);
                }

                case 'C' -> mostrarTarefaAtual(i);

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
}
