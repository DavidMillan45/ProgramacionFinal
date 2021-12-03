package model.jpa.entities;


import javax.persistence.*;

@Entity
@Table(name = "Official")
@PrimaryKeyJoinColumn
public class Official extends UserApp {

    @Column(name = "name", nullable = false)
    private String name;

    public Official () {}

    public Official (String username, String password, String email, String name) {
        super(username, password, email, "official");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}