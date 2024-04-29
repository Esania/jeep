package com.jeep.jeepney.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "code")
public class Code {
    @Id
    private String code;

    @ManyToMany
    @JoinTable(
        name = "code_place",
        joinColumns = @JoinColumn(name = "code"),
        inverseJoinColumns = @JoinColumn(name = "place")
    )
    private List<Place> places;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}