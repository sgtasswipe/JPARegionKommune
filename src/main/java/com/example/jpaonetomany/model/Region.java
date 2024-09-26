package com.example.jpaonetomany.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Region {
    @Id
    @Column ( length =  4)
    private String kode;
    // Json alias can be used to map a json field in danish to a field in english
    @JsonAlias("navn")  // navn is the json value
    private String name;  // name will be the field in the database
    private String href;
    @OneToMany( cascade =  CascadeType.ALL,mappedBy = "region")
    @JsonBackReference
    private Set<Kommune> kommuner = new HashSet<>();

    public Set<Kommune> getKommuner() {
        return kommuner;
    }

    public void setKommuner(Set<Kommune> kommuner) {
        this.kommuner = kommuner;
    }

    public Region() {
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
