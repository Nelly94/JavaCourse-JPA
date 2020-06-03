package model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ARTIST")
@NamedQuery(name = "findByFavInstrumentType", query = "select a from Artist a where a.favouriteInstrument.type = :instrumentType")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Artist extends Person{

    @Column(name="band_name")
    private String bandName;

    @Column(name="size")
    private Integer size;

    @ManyToOne
    @JoinColumn(name="manager_id")
    @EqualsAndHashCode.Exclude
    private Manager manager;

    @ManyToOne
    @JoinColumn(name="fav_instrument_id")
    private Instrument favouriteInstrument;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name="ARTIST_INSTRUMENTS",
            joinColumns = @JoinColumn(name="instrument_id"),
            inverseJoinColumns = @JoinColumn(name="artist_id"))
    private List<Instrument> playableInstruments = new ArrayList<>();

    @OneToOne(cascade = CascadeType.REMOVE)
    private SacemRegistration sacemRegistration;

    @OneToMany(mappedBy = "artist")
    private List<Media> medias = new ArrayList<>();


    public Artist(String firstName, String lastName, String bandName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bandName = bandName;
    }

}
