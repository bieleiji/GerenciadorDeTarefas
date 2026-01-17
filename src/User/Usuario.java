package User;

import java.util.ArrayList;

public class Usuario {
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

    public static void concluirTarefa(int qualTarefa) {
        gerenciadorTarefas.get(qualTarefa).setComplete(true);
    }
}
