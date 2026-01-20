package service;

import User.Tarefa;
import User.Usuario;

import java.io.*;
import java.util.ArrayList;

public class UsuarioService {

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Salvamento/Carregamento dos Dados ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Salvamento de Dados ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Salvamento de login ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static String caminho = "src/service/dadosDeLogin.txt";

    public static void salvarLogins(ArrayList<Usuario> usuarios) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            for(Usuario usuario : usuarios) {
                writer.write(usuario.getNome() + ";" + usuario.getId());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("\n\n(Operação de entrada e saída interrompida)\n");
        }
    }

    public static void carregarLogins(ArrayList<Usuario> usuarios) {
        try(BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                usuarios.add(new Usuario(dados[0], dados[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("\n\n(Arquivo não encontrado)\n");
        } catch (IOException e) {
            System.out.println("\n\n(Operação de entrada e saída interrompida)\n");
        }
    }


    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Criar Tarefa ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static int ehTituloRepetido(Usuario usuario, String titulo) {
        for(int i = 0; i < usuario.getGerenciadorTarefas().size(); i++) {
            if(usuario.getGerenciadorTarefas().get(i).getTask().getTitulo().equalsIgnoreCase(titulo))
                return i;
        }

        return -1;
    }

    public static String criarTarefa(Usuario usuario, String titulo, String descricao) {
        if(titulo == null || titulo.isBlank())
            return "\n\n(titulo inválido, nenhuma tarefa foi criada)\n";

        else if(ehTituloRepetido(usuario,titulo) != -1)
            return "\n\n(titulo já utilizado, nenhuma tarefa foi criada)\n";

        else {
            Tarefa tarefa = new Tarefa(titulo,descricao);
            usuario.criarTarefa(tarefa);
            return "\n\n(tarefa criada com sucesso!!!)\n";
        }
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Alterar Tarefa ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static String alterarTitulo(Usuario usuario, String titulo, int qualTarefa) {
        if(titulo == null || titulo.isBlank())
            return "\n\n(titulo inválido. Título não foi alterado)\n";

        else if(ehTituloRepetido(usuario,titulo) != -1)
            return "\n\n(titulo repetido ou já utilizado. Título não foi alterado)\n";

        else {
            usuario.alterarTitulo(titulo, qualTarefa);
            return "\n\n(Título alterado com sucesso!!!)\n";
        }
    }

    public static String alterarDescricao(String descricao, int qualTarefa) {
        if(descricao == null || descricao.isBlank())
            return "\n\n(descrição inválida. Descrição não foi alterada)\n";

        else {
            Usuario.alterarDescricao(descricao, qualTarefa);
            return "\n\n(Descrição alterada com sucesso!!!)\n";
        }
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Excluir Tarefa ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static String excluirTarefa(Usuario usuario, String tarefaDesejada) {
        int qualTarefa = ehTituloRepetido(usuario,tarefaDesejada);

        if(qualTarefa == -1)
            return "\n\n(Tarefa não encontrada, tente novamente)\n";

        else {
            usuario.excluirTarefa(qualTarefa);
            return "\n\n(Tarefa excluída com êxito!)\n";
        }
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Concluir Tarefa ↓
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static String concluirTarefa(Usuario usuario, String tarefaDesejada) {
        int qualTarefa = ehTituloRepetido(usuario,tarefaDesejada);

        if(qualTarefa == -1)
            return "\n\n(Tarefa não encontrada, tente novamente)\n";

        else {
            usuario.concluirTarefa(qualTarefa);
            return "\n\n(Tarefa concluída com êxito!)\n";
        }
    }
}
