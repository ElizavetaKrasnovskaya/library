package com.epam.library.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ReaderTicket {

    private Long id;
    private User reader;
    private LocalDate date;

    public ReaderTicket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getReader() {
        return reader;
    }

    public void setReader(User reader) {
        this.reader = reader;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ReaderTicket{" +
                "id=" + id +
                ", reader=" + reader +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReaderTicket)) return false;
        ReaderTicket that = (ReaderTicket) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getReader(), that.getReader()) && Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getReader(), getDate());
    }
}
