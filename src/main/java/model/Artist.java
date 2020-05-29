package model;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name="manager_id")
    private Manager manager;

    @ManyToOne
    @JoinColumn(name="fav_instrument_id")
    private Instrument favouriteInstrument;

    @ManyToMany
    @JoinTable( name="ARTIST_INSTRUMENTS",
            joinColumns = @JoinColumn(name="instrument_id"),
            inverseJoinColumns = @JoinColumn(name="artist_id"))
    private List<Instrument> playableInstruments;

    @OneToOne(fetch = FetchType.LAZY)
    private SacemRegistration sacemRegistration;


    public Artist(String firstName, String lastName, String bandName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bandName = bandName;
    }

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

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Instrument getFavouriteInstrument() {
        return favouriteInstrument;
    }

    public void setFavouriteInstrument(Instrument favouriteInstrument) {
        this.favouriteInstrument = favouriteInstrument;
    }

    public List<Instrument> getPlayableInstruments() {
        return playableInstruments;
    }

    public void setPlayableInstruments(List<Instrument> playableInstruments) {
        this.playableInstruments = playableInstruments;
    }

    public SacemRegistration getSacemRegistration() {
        return sacemRegistration;
    }

    public void setSacemRegistration(SacemRegistration sacemRegistration) {
        this.sacemRegistration = sacemRegistration;
    }
}
