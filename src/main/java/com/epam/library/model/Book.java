package com.epam.library.model;

public class Book {

    private Long id;
    private String title;
    private String genre;
    private Integer yearOfPublishing;
    private boolean isInReadingRoom;
    private boolean isTaken;
    private String specification;
    private State state;
    private ReaderTicket readerTicket;
    private Author author;

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getYearOfPublishing() {
        return yearOfPublishing;
    }

    public boolean isInReadingRoom() {
        return isInReadingRoom;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYearOfPublishing(Integer yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public void setInReadingRoom(boolean inReadingRoom) {
        this.isInReadingRoom = inReadingRoom;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public ReaderTicket getReaderTicket() {
        return readerTicket;
    }

    public void setReaderTicket(ReaderTicket readerTicket) {
        this.readerTicket = readerTicket;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
