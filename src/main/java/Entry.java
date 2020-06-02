import model.Artist;
import model.Manager;

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

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            em.persist(manager);
            em.persist(artist);
            transaction.commit();
        }catch(Exception e){
            transaction.rollback();
        }finally{
            em.close();
            emf.close();
        }

    }
}
