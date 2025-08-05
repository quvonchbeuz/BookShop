package service;

import dto.Book;
import exp.BookException;
import util.ScannerUtil;

import java.io.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class BookService {
    private final File file = new File("books.txt");

    public void addBook(String title, String author, String genre, Integer year, Integer quantity, Double price) {
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            check(title, author, genre, year, price);
            Book book = new Book(title, author, genre, year, quantity, price);
            writer(book);
            System.out.println("Book added successfully");
        } catch (BookException e) {
            System.out.println(e.getMessage());
        }

    }
    private void check(String title, String author, String genre, Integer year, Double price) {
        if (title == null || title.isBlank()){
            throw new BookException("Title is null or blank");
        }
        if (author == null || author.isBlank()){
            throw new BookException("Author is null or blank");
        }
        if (genre == null || genre.isBlank()){
            throw new BookException("Genre is null or blank");
        }
        if (year < 0){
            throw new BookException("Year is negative");
        }
        if (price < 0){
            throw new BookException("Price is negative");
        }
    }
    private void writer(Book book){
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))){
            writer.println(book.getTitle()+ "#" + book.getAuthor() + "#" + book.getGenre() + "#" +
                    book.getYear() + "#" + book.getQuantity() + "#" + book.getPrice() + "#" +
                    book.getId() + "#" + book.getDate());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private List<Book> reader(){
        List<Book> books = new LinkedList<>();
        String str = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            while ((str = reader.readLine()) != null){
                String[] temp = str.split("#");
                Book book = new Book(temp[0]
                        , temp[1]
                        , temp[2]
                        , Integer.parseInt(temp[3])
                        , Integer.parseInt(temp[4])
                        , Double.parseDouble(temp[5]));
                book.setId(UUID.fromString(temp[6]));
                book.setDate(LocalDateTime.parse(temp[7]));
                books.add(book);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
    private void rewriter(List<Book> books){
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                for (Book book : books){
                writer.println(book.getTitle() + "#" + book.getAuthor() + "#" + book.getGenre() + "#" +
                        book.getYear() + "#" + book.getQuantity() + "#" + book.getPrice() + "#" +
                        book.getId() + "#" + book.getDate());
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
    }
    public boolean deleteBook(String id){
        boolean result = false;
        result = reader().removeIf(book -> book.getId().equals(UUID.fromString(id)));
        rewriter(reader());
        return result;
    }
    public void printBooks(){
        if (reader().isEmpty()){
            System.out.println("Books not found!");
        }
        for (Book book : reader()) {
            System.out.println(book);
        }
    }
    public Book getBookById(String id){
        return reader().stream().filter(book -> book.getId().equals(UUID.fromString(id))).findFirst().orElse(null);
    }
    public void editBook(UUID bookId) {
        List<Book> books = reader();
        Book book = books.stream().filter(b -> b.getId().equals(bookId)).findFirst().orElse(null);
        if (book == null){
            throw new BookException("Book not found");
        }
        System.out.print("Enter title: ");
        book.setTitle(ScannerUtil.STRING.nextLine());
        System.out.print("Enter author: ");
        book.setAuthor(ScannerUtil.STRING.nextLine());
        System.out.print("Enter genre: ");
        book.setGenre(ScannerUtil.STRING.nextLine());
        System.out.print("Enter year: ");
        book.setYear(ScannerUtil.NUMBER.nextInt());
        System.out.print("Enter quantity: ");
        book.setQuantity(ScannerUtil.NUMBER.nextInt());
        System.out.print("Enter price: ");
        book.setPrice(ScannerUtil.NUMBER.nextDouble());
        rewriter(books);
    }

    public Book searchBook(String title) {
        return reader()
                .stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .findFirst()
                .orElse(null);
    }
}
