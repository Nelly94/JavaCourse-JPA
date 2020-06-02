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

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }
}
