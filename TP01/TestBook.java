/*
 * Exercicio 02 - Teste da classe Book
 * Nome: Igor Flores
 */

public class TestBook {
    public static void main(String[] args) {

        Author[] authors = new Author[2];
        authors[0] = new Author("Autor 01", "autor01@somewhere.com.br", 'm');
        authors[1] = new Author("Autor 02", "autor02@nowhere.com.br", 'm');

        Book testeBook = new Book("Java for Dummy", authors, 19.99, 99);
        System.out.println(testeBook);

        // testando getters
        System.out.println("Nome: " + testeBook.getName());
        System.out.println("Preco: " + testeBook.getPrice());
        System.out.println("Quantidade: " + testeBook.getQty());
        System.out.println("Autores: " + testeBook.getAuthorNames());

        // testando setters
        testeBook.setPrice(24.99);
        testeBook.setQty(50);
        System.out.println("\nApos alterar preco e quantidade:");
        System.out.println(testeBook);

        // testando construtor sem qty
        Book livro2 = new Book("Clean Code", authors, 49.90);
        System.out.println("\n" + livro2);
    }
}
