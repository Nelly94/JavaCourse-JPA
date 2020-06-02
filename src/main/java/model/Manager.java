package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="MANAGER")
public class Manager extends Person{

    @OneToMany(mappedBy = "manager")
    private List<Artist> representedArtists;

    @Column(name="budget")
    private Integer budget;

    public Manager(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public List<Artist> getRepresentedArtists() {
        return representedArtists;
    }

    public void setRepresentedArtists(List<Artist> representedArtists) {
        this.representedArtists = representedArtists;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }
}
