package dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Book extends BaseEntity{
    private String title;
    private String author;
    private String genre;
    private Integer year;
    private Integer quantity;
    private Double price;

    public Book(String title, String author, String genre,Integer year, Integer quantity, Double price) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.quantity = quantity;
        this.price = price;
        id = UUID.randomUUID();
        date = LocalDateTime.now();
        this.quantity = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book: " +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                ", quantity=" + quantity +
                ", price=" + price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(genre, book.genre) && Objects.equals(year, book.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, genre, year);
    }
}
