package service;

import dto.Book;
import dto.Profile;
import dto.Sales;
import enums.UserRole;
import enums.UserStatus;
import exp.PasswordException;
import exp.UserAlreadyExistException;
import exp.UserException;
import exp.UserNotFoundException;
import util.ScannerUtil;

import java.io.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ProfileService {
    private final File file = new File("users.txt");
    private final File sales =  new File("sales.txt");
    public ProfileService() {
    }
//    public void initAdmin(){
//        Profile admin = new Profile();
//        admin.setId(UUID.randomUUID());
//        admin.setUsername("Admin");
//        admin.setName("Admin");
//        admin.setSurname("Admin");
//        admin.setAge((short) 23);
//        admin.setPhone("8888");
//        admin.setPassword("8888");
//        admin.setRole(UserRole.ADMIN);
//        admin.setStatus(UserStatus.ACTIVE);
//        admin.setBalance(35000d);
//        admin.setDate(LocalDateTime.now());
//        try {
//            check(admin);
//            writer(admin);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
    public String register(String username,
                           String name,
                           String surname,
                           Short age,
                           String phone,
                           String password) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        for (Profile temp : reader()) {
            if (temp != null && temp.getPhone().equals(phone)){
                throw new UserAlreadyExistException("User already exists!");
            }
        }
        Profile profile = new Profile(username,
                name,
                surname,
                age,
                phone,
                password
        );
//        System.out.println(profile);
        writer(profile);
        return "Successfully registered";
    }
    private void writer(Profile user){
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))){
            check(user);
            writer.println(user.getUsername() + "#" + user.getName() + "#" + user.getSurname() + "#" +
                    user.getAge() + "#" + user.getPhone() + "#" + user.getPassword() + "#" +
                    user.getRole() + "#" + user.getStatus() + "#" + user.getBalance() + "#" +
                    user.getId() + "#" + user.getDate());
        } catch (IOException | UserException e) {
            System.out.println(e.getMessage());
        }
    }
    private void check(Profile user) {
        if (user.getUsername() == null ||  user.getUsername().isBlank()) {
            throw new UserException("Username is empty!");
        }
        if (user.getSurname() == null ||  user.getSurname().isBlank()) {
            throw new UserException("Surname is empty!");
        }
        if (user.getName() == null ||  user.getName().isBlank()) {
            throw new UserException("Name is empty!");
        }
        if (user.getPassword() == null ||  user.getPassword().isBlank()) {
            throw new UserException("Password is empty!");
        }
        if (user.getPhone() == null ||  user.getPhone().isBlank()) {
            throw new UserException("Phone is empty!");
        }
        for (Profile temp : reader()) {
            if (temp != null && temp.getPhone().equals(user.getPhone())) {
                throw new  UserAlreadyExistException("User already exists!");
            }
        }
    }
    public List<Profile> reader(){
        List<Profile> users = new LinkedList<>();
        String str = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            while ((str = reader.readLine()) != null){
                String[] temp = str.split("#");
                Profile user = new Profile(temp[0]
                        , temp[1]
                        , temp[2]
                        , Short.parseShort(temp[3])
                        , temp[4]
                        , temp[5]);
                user.setRole(UserRole.valueOf(temp[6]));
                user.setStatus(UserStatus.valueOf(temp[7]));
                user.setBalance(Double.valueOf(temp[8]));
                user.setId(UUID.fromString(temp[9]));
                user.setDate(LocalDateTime.parse(temp[10]));
                users.add(user);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
    private void rewriter(List<Profile> users) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, false))) {
            for (Profile user : users) {
                writer.println(user.getUsername() + "#" + user.getName() + "#" + user.getSurname() + "#" +
                        user.getAge() + "#" + user.getPhone() + "#" + user.getPassword() + "#" +
                        user.getRole() + "#" + user.getStatus() + "#" + user.getBalance() + "#" +
                        user.getId() + "#" + user.getDate());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Profile login(String entity, String password){
        for (Profile profile : reader()) {
            if ((profile.getPhone().equals(entity) || profile.getUsername().equals(entity))
                    && profile.getPassword().equals(password)){
                return profile;
            }
        }
        return null;
    }
    private boolean isValidAccount(Profile profile){
        boolean isDigit = false;
        boolean isUpper = false;
        boolean isLower = false;
        if (profile.getUsername().length() < 5){
            return false;
        }
        if (profile.getName().length() < 3 && profile.getSurname().length() < 4 ){
            return false;
        }
        for (int i = 0; i < profile.getPassword().length(); i++) {
            char c = profile.getPassword().charAt(i);
            if (Character.isDigit(c)){
                isDigit = true;
                continue;
            }
            if (Character.isUpperCase(c)){
                isUpper = true;
                continue;
            }
            if (Character.isLowerCase(c)){
                isLower = true;
            }
        }
        if (!(isDigit && isUpper && isLower)){
            return false;
        }
        if (profile.getPhone().trim().length() != 12 || !profile.getPhone().startsWith("998")){
            System.out.println("Invalid phone number");
            return false;
        }

        char[] phoneNumber = profile.getPhone().toCharArray();
        for (char c : phoneNumber) {
            if (!Character.isDigit(c)){
                System.out.println("Invalid number");
                return false;
            }
        }
        return true;
    }
    public void printProfiles(){
        if (reader().isEmpty()){
            System.out.println("Users not found!");
        }
        for (Profile profile : reader()) {
            System.out.println(profile);
        }
    }
    public void changePassword(Profile profile){
        List<Profile> users = reader();
        Profile user = users.stream().filter(x -> x.getUsername().equals(profile.getUsername())).findFirst().orElse(null);
        if (user == null){
            throw new UserNotFoundException("User not found!");
        }
        System.out.print("Enter current password: ");
        String checkPass = ScannerUtil.STRING.nextLine();
        System.out.print("Enter new password: ");
        String newPass1 = ScannerUtil.STRING.nextLine();
        System.out.print("Repeat new password: ");
        String newPass2 = ScannerUtil.STRING.nextLine();

        if (profile.getPassword().equals(checkPass) && newPass1.equals(newPass2)) {
            user.setPassword(newPass1);
        } else {
            throw new PasswordException("Passwords don't match!");
        }
        rewriter(users);
    }
    public Profile getProfileById(UUID id){
        for (Profile profile : reader()) {
            if (profile.getId().equals(id)){
                return profile;
            }
        }
        return null;
    }
    public void buyBook(Profile profile, Book book) {
        if (!sales.exists()){
            try {
                sales.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Sales sale = new Sales(book, profile);
        writeSale(sale);
        profile.setBalance(-book.getPrice());
        book.setQuantity(book.getQuantity() - 1);
        System.out.println("Book successfully bought !");
    }
    private void writeSale(Sales sale){
        try (PrintWriter writer = new PrintWriter(new FileWriter(sales, true))){
            writer.println(sale.getUser().getUsername() + "#" + sale.getBook().getTitle() + "#" +
                    sale.getBook().getAuthor() + "#" + sale.getTransactionId() + "#" +
                    sale.getDate());
        } catch (IOException | UserException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Sales> readSale(){
        List<Sales> salesList = new LinkedList<>();
        String str = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(sales))){
            while ((str = reader.readLine()) != null){
                String[] temp = str.split("#");
                Sales sale = new Sales();
                sale.getUser().setUsername(temp[0]);
                sale.getBook().setTitle(temp[1]);
                sale.getBook().setAuthor(temp[2]);
                sale.setId(UUID.fromString(temp[3]));
                sale.setDate(LocalDateTime.parse(temp[4]));
                salesList.add(sale);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return salesList;
    }

}
