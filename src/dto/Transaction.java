package dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction implements Comparable<Transaction>{
    Book book;
    Profile user;
    LocalDateTime date;
    UUID transactionId;

    public Transaction(Book book, Profile user) {
        this.book = book;
        this.user = user;
        this.date = LocalDateTime.now();
        this.transactionId = UUID.randomUUID();
    }

    public Book getBook() {
        return book;
    }

    public Profile getUser() {
        return user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    @Override
    public int compareTo(Transaction o) {
        return o.date.compareTo(this.date);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", transactionId=" + transactionId +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
