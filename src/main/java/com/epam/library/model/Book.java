package com.epam.library.model;

public class Book {

    private Long id;
    private String title;
    private Integer yearOfPublishing;

    public Book(Long id, String title, Integer yearOfPublishing) {
        this.id = id;
        this.title = title;
        this.yearOfPublishing = yearOfPublishing;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYearOfPublishing() {
        return yearOfPublishing;
    }


}
