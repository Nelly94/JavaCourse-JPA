package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="MANAGER")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @OneToMany(mappedBy = "manager")
    private List<Artist> representedArtists;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Artist> getRepresentedArtists() {
        return representedArtists;
    }

    public void setRepresentedArtists(List<Artist> representedArtists) {
        this.representedArtists = representedArtists;
    }
}
