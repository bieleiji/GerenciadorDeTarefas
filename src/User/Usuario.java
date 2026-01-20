package User;

import java.util.ArrayList;

public class Usuario {
    private static ArrayList<GerenciadorTarefas<Tarefa, Boolean>> gerenciadorTarefas = new ArrayList<>();
    private String nome;
    private final String id;

    public Usuario(String nome, String id) {
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

    public String getId() {
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

    public void criarTarefa(Tarefa tarefa) {
        gerenciadorTarefas.add(new GerenciadorTarefas<>(tarefa,false));
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Alterar Tarefa ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void alterarTitulo(String titulo, int qualTarefa) {
        gerenciadorTarefas.get(qualTarefa).getTask().setTitulo(titulo);
    }

    public static void alterarDescricao(String descricao, int qualTarefa) {
        gerenciadorTarefas.get(qualTarefa).getTask().setDescricao(descricao);
    }


    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Excluir Tarefa ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void excluirTarefa(int qualTarefa) {
        gerenciadorTarefas.remove(qualTarefa);
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Concluir Tarefa ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void concluirTarefa(int qualTarefa) {
        gerenciadorTarefas.get(qualTarefa).setComplete(true);
    }
}