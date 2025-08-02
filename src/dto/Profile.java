package dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class Profile extends BaseEntity{
    private String username;
    private String name;
    private String surname;
    private short age;
    private String phone;
    private String password;
    private String role;
    private String status;
    private Double balance;

    public Profile(String username, String name, String surname, short age, String phone, String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = phone;
        this.password = password;
        this.role = "USER";
        id = UUID.randomUUID();
        createdDate = LocalDateTime.now();
        status = "ACTIVE";
        balance = 0d;
    }

    public Profile() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public void setId(UUID id){
        this.id = id;
    }
    public UUID getId(){
        return id;
    }
    public void setCreatedDate(LocalDateTime now) {
        this.createdDate = now;
    }
    public LocalDateTime getCreatedDate(){
        return createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Profile: " +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", balance=" + balance +
                ", id=" + super.id +
                ", createdDate=" + super.createdDate;
    }


}
