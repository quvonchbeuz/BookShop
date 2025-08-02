package service;

import dto.Profile;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.UUID;

public class ProfileService {
    public void register(){

    }

    public ProfileService() {
    }

    public String register(String username,
                           String name,
                           String surname,
                           Short age,
                           String phone,
                           String password) {
        for (Profile temp : BookStore.users) {
            if (temp != null && temp.getPhone().equals(phone)){
                return "This account already exist";
            }
        }
        Profile profile = new Profile(username,
                name,
                surname,
                age,
                phone,
                password
        );
        System.out.println(profile);
        BookStore.users.add(profile);
        return "Successfully registered";
    }
    public Profile login(String entity, String password){
        for (Profile profile : BookStore.users) {
            if ((profile.getPhone().equals(entity) || profile.getUsername().equals(entity))
                    && profile.getPassword().equals(password)){
                return profile;
            }
        }
        for (Profile profile : BookStore.admins) {
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
        if (BookStore.users.isEmpty()){
            System.out.println("Users not found!");
        }
        for (Profile profile : BookStore.users) {
            System.out.println(profile);
        }
    }
}
