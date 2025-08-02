package service;

import dto.Book;
import dto.Profile;
import dto.Transaction;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private int userMenu(){
        System.out.println("1. Search book");
        System.out.println("2. Book list");
        System.out.println("3. Buy book");
        System.out.println("4. Sale list");
        System.out.println("5. Change password");
        System.out.println("6. Fill balance");
        System.out.println("0. Exit");
        System.out.println("Enter menu: ");
        int adminMenuNumber =  ScannerUtil.NUMBER.nextInt();
        ScannerUtil.STRING.nextLine();
        return adminMenuNumber;
    }
    public void userStart(Profile profile){
        while (true){
            switch (userMenu()){
                case 1 ->{
                    searchBook();
                }
                case 2 ->{
                    printBooks();
                }
                case 3 ->{
                    buyBook(profile);
                }
                case 4 ->{
                    ArrayList<Transaction> saleList = (ArrayList<Transaction>) saleList(profile);
                    System.out.println(saleList);
                }
                case 5 ->{
                    changePassword(profile);
                }
                case 6 ->{
                    System.out.print("Enter balance: ");
                    double balance = ScannerUtil.NUMBER.nextDouble();
                    fillBalance(profile,  balance);
                }
                case 0 ->{
                    return;
                }
                default -> {
                    System.out.println("Invalid menu");
                }
                
            }
        }
    }
    private Book searchBook(){
        System.out.print("Enter book title: ");
        String searchingBook = ScannerUtil.STRING.nextLine();
        for (Book book : BookStore.books) {
            if(book != null && book.getTitle().contains(searchingBook)){
                return book;
            }
        }
        return null;
    }
    private void printBooks(){
        if (BookStore.books.isEmpty()){
            System.out.println("Books not found!");
        }
        for (Book book : BookStore.books) {
            System.out.println(book);
        }
    }
    private void buyBook(Profile profile){
        Book book = searchBook();
        if (book == null){
            System.out.println("Book not found!");
            return;
        }
        if (book.getPrice() > profile.getBalance()){
            System.out.println("Insufficient funds!");
            return;
        }
        if (book.getAvailableCount() < 1){
            System.out.println("Book has been sold!");
        }
        Transaction transaction = new Transaction(book, profile);
        BookStore.transactions.add(transaction);
        setBalance(profile, book.getPrice());
        book.setAvailableCount(book.getAvailableCount() - 1);
        System.out.println("Book successfully bought !");
    }
    private boolean changePassword(Profile profile) {
        System.out.print("Enter current password: ");
        String checkPass = ScannerUtil.STRING.nextLine();
        System.out.print("Enter new password: ");
        String newPass1 = ScannerUtil.STRING.nextLine();
        System.out.print("Repeat new password: ");
        String newPass2 = ScannerUtil.STRING.nextLine();

        if (profile.getPassword().equals(checkPass) && newPass1.equals(newPass2)){
            profile.setPassword(newPass1);
            return true;
        }

        return false;
    }
    private void fillBalance(Profile profile, Double balance){
        if (balance <= 0){
            System.out.println("Invalid credit!!");
            return;
        }
        profile.setBalance(profile.getBalance() + balance);
        System.out.println("Balance changed");
    }
    private Collection<Transaction> saleList(Profile profile){
        return BookStore.transactions.stream().filter(t -> t.getUser().equals(profile)).collect(Collectors.toList());
    }
    private void setBalance(Profile profile,  Double balance){
        profile.setBalance(profile.getBalance() - balance);
    }

}
