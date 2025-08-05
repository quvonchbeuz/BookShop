package service;

import dto.Book;
import dto.Profile;
import dto.Sales;
import exp.BookException;
import exp.PasswordException;
import exp.UserException;
import util.ScannerUtil;

import java.util.List;

public class UserService {
    BookService bookService = new BookService();
    ProfileService profileService = new ProfileService();

    private int userMenu(){
        System.out.println("1. Search book");
        System.out.println("2. Book list");
        System.out.println("3. Buy book");
        System.out.println("4. Sale list");
        System.out.println("5. Change password");
        System.out.println("6. Fill balance");
        System.out.println("0. Exit");
        System.out.println("Enter menu: ");
        return ScannerUtil.NUMBER.nextInt();
    }
    public void userStart(Profile profile){
        while (true){
            switch (userMenu()){
                case 1 ->searchBook();

                case 2 ->printBooks();

                case 3 ->buyBook(profile);

                case 4 ->saleList(profile);

                case 5 ->changePassword(profile);

                case 6 ->{
                    System.out.print("Enter balance: ");
                    double balance = ScannerUtil.NUMBER.nextDouble();
                    fillBalance(profile,  balance);
                }
                case 0 ->{
                    return;
                }
                default -> System.out.println("Invalid menu");
            }
        }
    }
    private void searchBook(){
        System.out.print("Enter title: ");
        String title = ScannerUtil.STRING.nextLine();
        System.out.println(bookService.searchBook(title));
    }
    private void printBooks(){
        bookService.printBooks();
    }
    private void buyBook(Profile profile){
        System.out.print("Enter book ID: ");
        String bookID = ScannerUtil.STRING.nextLine();
        Book book = bookService.getBookById(bookID);
        if (book == null){
            throw new BookException("Book not found!");
        }
        if (book.getPrice() > profile.getBalance()){
            throw new UserException("Insufficient balance!");
        }
        if (book.getQuantity() < 1){
            throw new BookException("No book left!");
        }
        profileService.buyBook(profile, book);
    }
    private void changePassword(Profile profile) {
        try {
            profileService.changePassword(profile);
            System.out.println("Password changed");
        } catch (PasswordException e){
            System.out.println(e.getMessage());
        }
    }
    private void fillBalance(Profile profile, Double balance){
        if (balance <= 0){
            System.out.println("Invalid credit!!");
            return;
        }
        profile.setBalance(balance);
        System.out.println("Balance changed");
    }
    private void saleList(Profile profile){
        List<Sales> sales = profileService.readSale();
        sales.stream().filter(sale -> sale.getUser().getUsername().equals(profile.getUsername())).forEach(System.out::println);
    }
}
