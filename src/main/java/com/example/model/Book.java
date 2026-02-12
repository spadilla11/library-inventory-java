package com.example.model;

public class Book {
    private int id;
    private String title;
    private String author;

    public Book(int id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author) {
        this(0, title, author);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String gettitle() { return title; }
    public void settitle(String title) { this.title = title; }

    public String getauthor() { return author; }
    public void setauthor(String author) { this.author = author; }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

}