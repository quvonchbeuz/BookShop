package service;

import dto.Book;
import dto.Profile;
import dto.Transaction;

import java.util.LinkedList;

public class BookStore {
    public static LinkedList<Book> books = new LinkedList<>();
    public static LinkedList<Profile> users = new LinkedList<>();
    public static LinkedList<Profile> admins = new LinkedList<>();
    public static LinkedList<Transaction> transactions = new LinkedList<>();
}
