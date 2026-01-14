package service;

import User.Tarefa;
import User.Usuario;

public class UsuarioService {

    public static boolean ehTituloRepetido(Usuario usuario, String titulo) {
        for(int i = 0; i < usuario.getGerenciadorTarefas().size(); i++) {
            if(usuario.getGerenciadorTarefas().get(i).getTask().getTitulo().equals(titulo))
                return true;
        }

        return false;
    }

    public static String checkCriarTarefa(Usuario usuario, String titulo, String descricao) {
        if(titulo == null || titulo.isBlank())
            return "\n\n(titulo inválido, nenhuma tarefa foi criada)\n";

        if(ehTituloRepetido(usuario,titulo))
            return "\n\n(titulo repetido, nenhuma tarefa foi criada)\n";

        else {
            Tarefa tarefa = new Tarefa(titulo,descricao);
            Usuario.criarTarefa(tarefa);
            return "\n\n(tarefa criada com sucesso!!!)\n";
        }
    }
}
