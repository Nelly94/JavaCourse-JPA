package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="MEDIA")
@IdClass(MediaId.class)
public class Media {

    @Id
    public String name;

    @Id
    @Enumerated(EnumType.STRING)
    public MediaType type;

    @Column(name="release_date")
    @Temporal(TemporalType.DATE)
    public Date release;

    @ManyToOne
    @JoinColumn(name="artist_id")
    private Artist artist;

    public Media(MediaId id) {
        this.name = id.name;
        this.type = id.type;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
