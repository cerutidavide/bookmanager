package com.ceruti.bookmanager.model;

import java.util.Objects;

public class Author {
    private String name;
    private String surname;
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;
        return getName().equals(author.getName()) && getSurname().equals(author.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname());
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
