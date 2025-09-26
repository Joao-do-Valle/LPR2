public class TestAuthor {
    public static void main(String[] args) {
        Author a1 = new Author("Wellington Tuler", "tulermoraes@yahoo.com", 'm');

        System.out.println("Nome: " + a1.getName());
        System.out.println("Email: " + a1.getEmail());
        System.out.println("Gênero: " + a1.getGender());

        a1.setEmail("novoemail@gmail.com");
        System.out.println("Email atualizado: " + a1.getEmail());

        System.out.println(a1);
    }
}
