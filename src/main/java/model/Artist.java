package model;

import javax.persistence.*;

@Entity
@Table(name="ARTIST")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="band_name")
    private String bandName;

    @Column(name="size")
    private Integer size;

    public Artist(Long id, String firstName, String lastName, String bandName, Integer size) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bandName = bandName;
        this.size = size;
    }

    public Artist(String firstName, String lastName, String bandName, Integer size) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bandName = bandName;
        this.size = size;
    }
}
