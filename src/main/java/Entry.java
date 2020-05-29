import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Entry {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatraining");

        //Artist artist = new Artist("Dani", "Martin", "El canto del loco");

        emf.close();
    }
}
