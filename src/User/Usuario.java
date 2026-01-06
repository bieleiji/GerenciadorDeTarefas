package User;

public class Usuario {
    private String nome;
    private final String id;

    Usuario(String nome, String id) {
        this.nome = nome;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + '\n' +
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
}
