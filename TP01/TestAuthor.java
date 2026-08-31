/*
 * Exercicio 01 - Teste da classe Author
 * Nome: Igor Flores
 */

public class TestAuthor {
    public static void main(String[] args) {

        // testando construtor e toString
        Author a1 = new Author("Wellington Tuler", "tulermoraes@yahoo.com", 'm');
        System.out.println(a1);

        // testando getters
        System.out.println("Nome: " + a1.getName());
        System.out.println("Email: " + a1.getEmail());
        System.out.println("Genero: " + a1.getGender());

        // testando setEmail (unico setter disponivel)
        a1.setEmail("novoemail@gmail.com");
        System.out.println("Email atualizado: " + a1.getEmail());
        System.out.println(a1);
    }
}
