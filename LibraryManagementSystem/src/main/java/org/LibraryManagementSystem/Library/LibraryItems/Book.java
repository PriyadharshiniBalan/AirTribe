package org.LibraryManagementSystem.Library.LibraryItems;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private String series;
    private int publicationYear;
    private boolean isBookBorrowed;

    public Book(String title, String author, String isbn, String series, int publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.series = series;
        this.publicationYear = publicationYear;
        this.isBookBorrowed = false;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public boolean isBookBorrowed() {
        return isBookBorrowed;
    }

    public void setBookBorrowed(boolean bookBorrowed) {
        isBookBorrowed = bookBorrowed;
    }

    @Override
    public String toString() {
        return title + " by " + author + " (ISBN: " + isbn + ", Series: " + series + ", Year: " + publicationYear + ")";
    }
}
