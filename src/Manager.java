import dto.Profile;
import service.AdminService;
import service.BookStore;
import service.ProfileService;
import service.UserService;
import util.ScannerUtil;

import java.time.LocalDateTime;
import java.util.UUID;

public class Manager {
    ProfileService profileService = new ProfileService();
    public Manager() {
        Profile admin = new Profile();
        admin.setId(UUID.randomUUID());
        admin.setUsername("Admin");
        admin.setName("Admin");
        admin.setSurname("Admin");
        admin.setAge((short) 23);
        admin.setPhone("8888");
        admin.setPassword("8888");
        admin.setRole("ADMIN");
        admin.setCreatedDate(LocalDateTime.now());
        BookStore.admins.add(admin);
    }

    public void start(){
        while (true){
            switch (mainMenu()){
                case 1 -> login();
                case 2 -> register();
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void register() {
        System.out.print("Enter username: ");
        String username = ScannerUtil.STRING.next();
        System.out.print("Enter name: ");
        String name = ScannerUtil.STRING.next();
        System.out.print("Enter surname: ");
        String surname = ScannerUtil.STRING.next();
        System.out.print("Enter age: ");
        Short age = ScannerUtil.NUMBER.nextShort();
        System.out.print("Enter phone: ");
        String phone = ScannerUtil.STRING.next();
        ScannerUtil.STRING.nextLine();
        System.out.print("Enter password: ");
        String password = ScannerUtil.STRING.nextLine();
        String response = profileService.register(username, name, surname, age, phone, password);
        System.out.println(response);

    }

    private void login() {
        System.out.print("Enter username or phone number: ");
        String entity = ScannerUtil.STRING.next();
        System.out.print("Enter password: ");
        String password = ScannerUtil.STRING.next();
        Profile profile = profileService.login(entity, password);
        System.out.println(profile);
        if (profile == null){
            System.out.println("Password incorrect");
        } else if (profile.getRole().equals("ADMIN")){
            admin(profile);
        } else if (profile.getRole().equals("USER")) {
           user(profile);
        }
    }

    private void user(Profile profile) {
        UserService  userService = new UserService();
        userService.userStart(profile);
    }

    private void admin(Profile profile) {
        AdminService adminService = new AdminService();
        adminService.admin(profile);
    }

    private int mainMenu(){
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("0. Exit");
        System.out.println("Enter menu: ");
        return ScannerUtil.NUMBER.nextInt();
    }


}
