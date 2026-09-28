import java.util.UUID;

/*
 * Exercicio - Classe Aluno com UUID
 * Nome: Igor Flores
 */

public class Aluno {

    private String endereco;
    private int idade;
    private String nome;
    private UUID uuid;

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public UUID getUuid() { return uuid; }
    public void setUuid(UUID uuid) { this.uuid = uuid; }
}
