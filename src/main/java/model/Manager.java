package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="MANAGER")
public class Manager extends Person{

    @OneToMany(mappedBy = "manager")
    private List<Artist> representedArtists;

    public List<Artist> getRepresentedArtists() {
        return representedArtists;
    }

    public void setRepresentedArtists(List<Artist> representedArtists) {
        this.representedArtists = representedArtists;
    }
}
