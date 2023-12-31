package com.ceruti.bookmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Publisher {
    private String name;
    private String city;
    @Id
    @GeneratedValue
    private Long Id;


    public Publisher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publisher publisher)) return false;
        return getName().equals(publisher.getName()) && Objects.equals(getCity(), publisher.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCity());
    }
}
