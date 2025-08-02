package service;

import dto.Profile;
import dto.Transaction;
import util.ScannerUtil;

public class AdminService {
    public final ProfileService profileService = new ProfileService();
    public final BookService bookService = new BookService();
    private int adminMenu(){
        System.out.println("1. Add book");
        System.out.println("2. Edit book");
        System.out.println("3. Delete book");
        System.out.println("4. Book list");
        System.out.println("5. User list");
        System.out.println("6. Sale list");
        System.out.println("7. Change password");
        System.out.println("8. Add admin");
        System.out.println("9. Delete admin");
        System.out.println("0. Exit");
        System.out.println("Enter menu: ");
        int adminMenuNumber =  ScannerUtil.NUMBER.nextInt();
        ScannerUtil.STRING.nextLine();
        return adminMenuNumber;
    }

    public void admin(Profile profile){
        while (true){
            switch (adminMenu()){
                case 1 ->{
                    bookService.addBook();
                }
                case 2 ->{
                    System.out.print("Enter book title: ");
                    String title = ScannerUtil.STRING.nextLine();
                    bookService.editBook(bookService.getBook(title));
                }
                case 3 ->{
                    System.out.print("Enter book title: ");
                    String title = ScannerUtil.STRING.nextLine();
                    if (bookService.deleteBook(title))
                    {
                        System.out.println("Book successfully deleted");
                    } else System.out.println("Book not found");
                }
                case 4 ->{
                    bookService.printBooks();
                }
                case 5 ->{
                    profileService.printProfiles();
                }
                case 6 ->{
                    saleList();
                }
                case 7 ->{
                    if (changePassword(profile)){
                        System.out.println("Password changed");
                    } else System.out.println("Invalid password!!");
                }
                case 8 ->{
                    addAdmin();
                }
                case 9 ->{

                }
                case 0 ->{
                    return;
                }
                default -> System.out.println("Invalid number!");
            }
        }
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
    private void addAdmin(){
        System.out.println("Enter username: ");
        String username = ScannerUtil.STRING.next();
        for (Profile user : BookStore.users) {
            if (user.getUsername().equals(username)){
                user.setRole("ADMIN");
                BookStore.admins.add(user);
                BookStore.users.remove(user);
                return;
            }
        }
        System.out.println("User not found!!");
    }
    private void saleList(){
        for (Transaction transaction : BookStore.transactions) {
            System.out.println(transaction);
        }
    }

}
