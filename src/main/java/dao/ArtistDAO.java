package dao;

import model.Artist;
import model.Person;

import java.util.List;

public interface ArtistDAO extends GenericDAO<Artist,Long>{

    public List<Artist> findByLastName(String lastName);
}
