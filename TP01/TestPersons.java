/*
 * Exercicio 03 - Teste das classes Student e Staff
 * Nome: Igor Flores
 */

public class TestPersons {
    public static void main(String[] args) {

        // testando Student
        Student s1 = new Student("Carlos Silva", "Rua A, 100", "ADS", 2, 1500.00);
        System.out.println(s1);
        System.out.println("Nome: " + s1.getName());
        System.out.println("Endereco: " + s1.getAddress());
        System.out.println("Curso: " + s1.getProgram());
        System.out.println("Ano: " + s1.getYear());
        System.out.println("Taxa: " + s1.getFee());

        s1.setAddress("Rua B, 200");
        s1.setProgram("Ciencia da Computacao");
        s1.setYear(3);
        s1.setFee(1800.00);
        System.out.println("\nStudent apos alteracoes:");
        System.out.println(s1);

        // testando Staff
        Staff st1 = new Staff("Ana Oliveira", "Av. Principal, 500", "IFSP Cubatao", 4500.00);
        System.out.println("\n" + st1);
        System.out.println("Nome: " + st1.getName());
        System.out.println("Endereco: " + st1.getAddress());
        System.out.println("Escola: " + st1.getSchool());
        System.out.println("Salario: " + st1.getPay());

        st1.setAddress("Rua Nova, 10");
        st1.setSchool("IFSP Santos");
        st1.setPay(5000.00);
        System.out.println("\nStaff apos alteracoes:");
        System.out.println(st1);
    }
}
