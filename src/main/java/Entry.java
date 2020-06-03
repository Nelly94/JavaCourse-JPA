import model.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class Entry {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatraining");
        EntityManager em = emf.createEntityManager();

        Artist artist = new Artist("Dani", "Martin", "El canto del loco");
        Manager manager = new Manager("Alejandro", "Perez");
        manager.getRepresentedArtists().add(artist);
        artist.setManager(manager);

        Artist ladyGaga = new Artist("Lady", "Gaga", "");
        manager.getRepresentedArtists().add(ladyGaga);
        ladyGaga.setManager(manager);

        Instrument piano = new Instrument();
        piano.setName("Piano");
        piano.setType(InstrumentType.STRING);
        artist.setFavouriteInstrument(piano);
        artist.getPlayableInstruments().add(piano);
        piano.getArtists().add(artist);

        Instrument guitar = new Instrument();
        guitar.setName("Guitar");
        guitar.setType(InstrumentType.STRING);
        guitar.getArtists().add(artist);
        artist.getPlayableInstruments().add(guitar);

        Instrument drums = new Instrument();
        drums.setName("Drums");
        drums.setType(InstrumentType.PERCUSSION);
        drums.getArtists().add(ladyGaga);
        ladyGaga.getPlayableInstruments().add(drums);
        ladyGaga.setFavouriteInstrument(drums);

        Media media = new Media(new MediaId("insoportable", MediaType.CD));
        media.setArtist(artist);

        SacemRegistration sRegistration = new SacemRegistration();
        sRegistration.setArtist(artist);
        sRegistration.setCode("123");
        sRegistration.setDate(new Date());
        artist.setSacemRegistration(sRegistration);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            em.persist(manager);
            em.persist(piano);
            em.persist(guitar);
            em.persist(drums);
            em.persist(ladyGaga);
            em.persist(artist);
            em.persist(media);
            em.persist(sRegistration);

            Media mediaFounded = em.find(Media.class, new MediaId("insoportable", MediaType.CD));
            System.out.println("Media founded: " + mediaFounded.getName()+ " " + mediaFounded.getArtist().getBandName());

            Media media2 = new Media(new MediaId("besos", MediaType.DVD));
            media2.setArtist(artist);
            em.merge(media2);

            Query query = em.createQuery("select a from Artist a where a.favouriteInstrument.type = :instrumentType");
            query.setParameter("instrumentType", InstrumentType.PERCUSSION);
            List<Artist> foundArtists = query.getResultList();
            for(Artist a : foundArtists){
                System.out.println(a.getFirstName() + " " + a.getLastName());
            }

            Query namedQuery = em.createNamedQuery("findByFavInstrumentType", Artist.class);
            namedQuery.setParameter("instrumentType", InstrumentType.PERCUSSION);
            List<Artist> foundArtists2 = query.getResultList();
            for(Artist a : foundArtists2){
                System.out.println(a.getFirstName() + " " + a.getLastName());
            }

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Artist> cq = cb.createQuery(Artist.class);
            Root<Artist> root = cq.from(Artist.class);
            cq.select(root);
            List<Artist> artists = em.createQuery(cq).getResultList();
            for(Artist a : artists){
                System.out.println(a.getFirstName() + " " + a.getLastName());
            }


            transaction.commit();

        }catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally{
            em.close();
            emf.close();
        }

    }
}
