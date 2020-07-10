package com.example.petclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person {

    @Builder
    public Owner(Long id, String firstName, String lastName, String addres, String city, String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.addres = addres;
        this.city = city;
        this.telephone = telephone;
        this.pets = pets;
    }


    public Owner(String addres, String city, String telephone, Set<Pet> pets) {
        this.addres = addres;
        this.city = city;
        this.telephone = telephone;
        this.pets = pets;
    }

    @Column(name="addres")
    private String addres;
    @Column(name="city")
    private String city;
    @Column(name="telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();
}
