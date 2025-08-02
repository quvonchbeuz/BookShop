package service;

import dto.Book;
import util.ScannerUtil;

public class BookService {
    public void addBook() {
        System.out.print("Enter title: ");
        String title = ScannerUtil.STRING.nextLine();
        System.out.print("Enter author: ");
        String author = ScannerUtil.STRING.nextLine();

        if (getBook(title) != null){
            addBook(title);
            return;
        }

        System.out.print("Enter genre: ");
        String genre = ScannerUtil.STRING.next();
        System.out.print("Enter year: ");
        Integer year = ScannerUtil.NUMBER.nextInt();
        System.out.print("Enter the number of books: ");
        Integer availableCount = ScannerUtil.NUMBER.nextInt();
        System.out.print("Enter price: ");
        Double price = ScannerUtil.NUMBER.nextDouble();

        Book book = new Book(title, author, genre, year, availableCount, price);
        BookStore.books.add(book);
    }
    public void addBook(String title){
        System.out.println("Enter book number: ");
        Integer numberOfBook = ScannerUtil.NUMBER.nextInt();
        Book book = getBook(title);
        book.setAvailableCount(book.getAvailableCount() + numberOfBook);
        System.out.println("Books added!");
    }
    public Book getBook(String title){
        for (Book book : BookStore.books) {
            if (book.getTitle().equals(title)){
                return book;
            }
        }
        return null;
    }
    public boolean deleteBook(String title){
        for (int i = 0; i < BookStore.books.size(); i++) {
            if (BookStore.books.get(i).getTitle().equals(title)){
                BookStore.books.remove(i);
                return true;
            }
        }
        return false;
    }
    public void printBooks(){
        if (BookStore.books.isEmpty()){
            System.out.println("Books not found!");
        }
        for (Book book : BookStore.books) {
            System.out.println(book);
        }
    }
    public Book editBook(Book book) {
        System.out.print("Enter book title: ");
        book.setTitle(ScannerUtil.STRING.next());
        System.out.print("Enter book author: ");
        book.setAuthor(ScannerUtil.STRING.nextLine());
        System.out.print("Enter book genre: ");
        book.setAuthor(ScannerUtil.STRING.next());
        System.out.print("Enter the number of book: ");
        book.setAvailableCount(ScannerUtil.NUMBER.nextInt());
        System.out.println("Enter new price: ");
        book.setPrice(ScannerUtil.NUMBER.nextDouble());
        return book;
    }
}
