package com.ceruti.bookmanager.model;


import jakarta.persistence.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;


@Entity
@Table(name = "book")
public class Book {
    private String title;
    private Integer pages;
    private String review;
    private String language;
    private Date publicationyear;
    @OneToMany
    private Author[] authors;
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Publisher publisher;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return getTitle().equals(book.getTitle()) && Arrays.equals(getAuthors(), book.getAuthors()) && getPublisher().equals(book.getPublisher());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getTitle(), getPublisher());
        result = 31 * result + Arrays.hashCode(getAuthors());
        return result;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getPublicationyear() {
        return publicationyear;
    }

    public void setPublicationyear(Date publicationyear) {
        this.publicationyear = publicationyear;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }
}
