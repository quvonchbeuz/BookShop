package dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class Book extends BaseEntity{
    private String title;
    private String author;
    private String genre;
    private Integer year;
    private Integer availableCount;
    private Double price;

    public Book(String title, String author, String genre,Integer year, Integer availableCount, Double price) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availableCount = availableCount;
        this.price = price;
        id = UUID.randomUUID();
        createdDate = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public UUID getid() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book: " +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                ", availableCount=" + availableCount +
                ", price=" + price;
    }
}
