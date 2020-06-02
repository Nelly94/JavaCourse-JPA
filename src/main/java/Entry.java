import model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Entry {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatraining");
        EntityManager em = emf.createEntityManager();

        Artist artist = new Artist("Dani", "Martin", "El canto del loco");
        Manager manager = new Manager();
        manager.setFirstName("Alejandro");
        manager.setLastName("Ramirez");
        artist.setManager(manager);

        Manager manager2 = new Manager();
        manager2.setFirstName("Luis");
        manager2.setLastName("Santos");

        Instrument piano = new Instrument();
        piano.setName("Piano");
        piano.setType(InstrumentType.STRING);
        artist.setFavouriteInstrument(piano);

        Media media = new Media(new MediaId("insoportable", MediaType.CD));
       media.setArtist(artist);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            em.persist(manager);
            em.persist(piano);
            em.persist(artist);
            em.persist(manager2);
            em.persist(media);


            Media mediaFounded = em.find(Media.class, new MediaId("insoportable", MediaType.CD));
            System.out.println("Media founded: " + mediaFounded.getName()+ " " + mediaFounded.getArtist().getBandName());

            Manager managerToDelete = em.find(Manager.class, manager2.id);
            em.remove(managerToDelete);

            transaction.commit();

        }catch(Exception e){
            transaction.rollback();
        }finally{
            em.close();
            emf.close();
        }

    }
}
