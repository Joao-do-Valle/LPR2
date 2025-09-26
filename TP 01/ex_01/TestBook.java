public class TestBook {
    public static void main(String[] args) {
        Author[] authors = new Author[2];
        authors[0] = new Author("Autor 01", "autor01@somewhere.com.br", 'm');
        authors[1] = new Author("Autor 02", "autor02@nowhere.com.br", 'm');

        Book book = new Book("Programação em Java", authors, 79.90, 10);

        System.out.println(book);
        System.out.println("Autores: " + book.getAuthorNames());

        book.setPrice(99.90);
        book.setQty(15);
        System.out.println("Preço atualizado: " + book.getPrice());
        System.out.println("Quantidade atualizada: " + book.getQty());
    }
}
