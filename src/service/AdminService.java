package service;

import dto.Profile;
import dto.Sales;
import exp.PasswordException;
import util.ScannerUtil;

import java.util.UUID;

public class AdminService {
    public final ProfileService profileService = new ProfileService();
    public final BookService bookService = new BookService();
//    private File file = new File("admin.txt");

    private int adminMenu(){
        System.out.println("1. Add book");
        System.out.println("2. Edit book");
        System.out.println("3. Delete book");
        System.out.println("4. Book list");
        System.out.println("5. User list");
        System.out.println("6. Sale list");
        System.out.println("7. Change password");
//        System.out.println("8. Add admin");
//        System.out.println("9. Delete admin");
        System.out.println("0. Exit");
        System.out.println("Enter menu: ");

        return ScannerUtil.NUMBER.nextInt();
    }

    public void admin(Profile profile){
        while (true){
            switch (adminMenu()){
                case 1 -> addBook();
                case 2 -> editBook();
                case 3 -> deleteBook();
                case 4 -> bookList();
                case 5 -> userList();
                case 6 -> saleList();
                case 7 -> changePassword(profile);
//                case 8 ->{
//                    addAdmin();
//                }
//                case 9 ->{
//                    removeAdmin();
//                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid number!");
            }
        }
    }

    private void userList() {
        profileService.printProfiles();
    }

    private void bookList() {
        bookService.printBooks();
    }

    private void deleteBook() {
        System.out.print("Enter book id: ");
        String bookId = ScannerUtil.STRING.nextLine();
        if (bookService.deleteBook(bookId)) {
            System.out.println("Book deleted");
        } else {
            System.out.println("Book not found");
        }
    }

    private void editBook() {
        System.out.print("Book id: ");
        String bookId = ScannerUtil.STRING.next();
        ScannerUtil.STRING.nextLine();
        bookService.editBook(UUID.fromString(bookId));
    }

    private void addBook() {
        System.out.print("Enter book title: ");
        String title = ScannerUtil.STRING.nextLine();
        System.out.print("Enter book author: ");
        String author = ScannerUtil.STRING.nextLine();
        System.out.print("Enter book genre: ");
        String genre = ScannerUtil.STRING.nextLine();
        System.out.print("Enter book year: ");
        Integer year = ScannerUtil.STRING.nextInt();
        System.out.print("Enter book quantity: ");
        Integer quantity = ScannerUtil.STRING.nextInt();
        System.out.print("Enter book price: ");
        Double price = ScannerUtil.STRING.nextDouble();
        bookService.addBook(title, author, genre, year, quantity, price);
    }

    private void changePassword(Profile profile) {
        try {
            profileService.changePassword(profile);
            System.out.println("Password changed");
        } catch (PasswordException e){
            System.out.println(e.getMessage());
        }
    }
//    private void addAdmin(){
//        System.out.println("Enter username: ");
//        String username = ScannerUtil.STRING.next();
//        for (Profile user : profileService.) {
//            if (user.getUsername().equals(username)){
//                user.setRole("ADMIN");
//                admins.add(user);
//                users.remove(user);
//                return;
//            }
//        }
//        System.out.println("User not found!!");
//    }
    private void saleList(){
        for (Sales sale : profileService.readSale()) {
            System.out.println(sale);
        }
    }
//    private void removeAdmin(){
//        System.out.println("Enter admin username: ");
//        String username = ScannerUtil.STRING.next();
//        for (Profile user : BookStore.admins) {
//            if (user.getUsername().equals(username)){
//                user.setRole("USER");
//                BookStore.users.add(user);
//                BookStore.admins.remove(user);
//            }
//        }
//    }
}
