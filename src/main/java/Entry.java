import model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class Entry {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatraining");
        EntityManager em = emf.createEntityManager();

        Artist artist = new Artist("Dani", "Martin", "El canto del loco");
        Manager manager = new Manager("Alejandro", "Perez");
        manager.getRepresentedArtists().add(artist);
        artist.setManager(manager);

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
            em.persist(artist);
            em.persist(media);
            em.persist(sRegistration);

            Media mediaFounded = em.find(Media.class, new MediaId("insoportable", MediaType.CD));
            System.out.println("Media founded: " + mediaFounded.getName()+ " " + mediaFounded.getArtist().getBandName());

            Media media2 = new Media(new MediaId("besos", MediaType.DVD));
            media2.setArtist(artist);
            em.merge(media2);


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
