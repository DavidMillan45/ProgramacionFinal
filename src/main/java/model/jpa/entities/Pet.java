package model.jpa.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Pet") // Optional
public class Pet {

    @Id
    @GeneratedValue
    @Column(name = "pet_id")
    private String pet_id;

    @Column(name = "microchip", unique = true)
    private String microchip;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "especies", nullable = false)
    private String especies;

    @Column(name = "race", nullable = false)
    private String race;

    @Column(name = "size", nullable = false)
    private String size;

    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "picture", nullable = false)
    private String picture;


    @OneToMany(mappedBy = "pet")
    private List<PetCase> Petscase = new ArrayList<>();


    @OneToMany(mappedBy = "pet")
    private List<Visit> visits = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "username")
    private Owner owner;


    public Pet() {
    }

    public Pet(String microchip, String name, String especies, String race, String size, String sex, String picture) {
        this.microchip = microchip;
        this.name = name;
        this.especies = especies;
        this.race = race;
        this.size = size;
        this.sex = sex;
        this.picture = picture;
    }

    public Pet(String pet_id, String microchip, String name, String especies, String race, String size, String sex, String picture, List<PetCase> petscase) {
        this.pet_id = pet_id;
        this.microchip = microchip;
        this.name = name;
        this.especies = especies;
        this.race = race;
        this.size = size;
        this.sex = sex;
        this.picture = picture;
        Petscase = petscase;
    }

    public String getPet_id() {
        return pet_id;
    }

    public void setPet_id(String pet_id) {
        this.pet_id = pet_id;
    }

    public String getMicrochip() {
        return microchip;
    }

    public void setMicrochip(String microchip) {
        this.microchip = microchip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEspecies() {
        return especies;
    }

    public void setEspecies(String especies) {
        this.especies = especies;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<PetCase> getPetscase() {
        return Petscase;
    }

    public void setPetscase(List<PetCase> petscase) {
        Petscase = petscase;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void addPetCase(PetCase petcase) {
        Petscase.add(petcase);
        petcase.setPet(this);
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
        visit.setPet(this);
    }

}