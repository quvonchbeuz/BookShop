package dto;

import enums.UserRole;
import enums.UserStatus;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Profile extends BaseEntity{
    private String username;
    private String name;
    private String surname;
    private short age;
    private String phone;
    private String password;
    private UserRole role;
    private UserStatus status;
    private Double balance = 0d;

    public Profile(String username, String name, String surname, short age, String phone, String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = phone;
        this.password = password;
        this.role = UserRole.USER;
        id = UUID.randomUUID();
        date = LocalDateTime.now();
        status = UserStatus.ACTIVE;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance += balance;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(username, profile.username) && Objects.equals(name, profile.name) && Objects.equals(surname, profile.surname) && Objects.equals(phone, profile.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, name, surname, phone);
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
                ", createdDate=" + super.date;
    }


}
