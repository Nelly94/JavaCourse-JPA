package dao;

import model.Artist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class ArtistImplDAO implements ArtistDAO {

    EntityManagerFactory emf = EntityManagerFactoryS.getInstance();

    @Override
    public List<Artist> findByLastName(String lastName) {
        List<Artist> artists = null;
        EntityManager em = null;
        try{
            em = emf.createEntityManager();
            Query query =  em.createQuery("select a from Artist a where a.lastName = :lastName");
            query.setParameter("lastName", lastName);
            artists = query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(em != null && em.isOpen()){
                em.close();
            }
        }
        return artists;
    }

    @Override
    public List<Artist> findAll() {
        List<Artist> artists = null;
        EntityManager em = null;
        try{
            em = emf.createEntityManager();
            artists =  em.createQuery("select a from Artist a").getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(em != null && em.isOpen()){
                em.close();
            }
        }
        return artists;
    }

    @Override
    public Artist findById(Long id) {
        Artist artist = null;
        EntityManager em = null;
        try{
            em = emf.createEntityManager();
            artist = em.find(Artist.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(em != null && em.isOpen()){
                em.close();
            }
        }
        return artist;
    }

    @Override
    public void update(Artist entity) {
        //TODO
    }

    @Override
    public void delete(Artist entity) {
        EntityTransaction tx = null;
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.remove(entity);
            tx.commit();
        }catch(Exception e){
            e.printStackTrace();
            if(tx != null){
                tx.rollback();
            }
        }finally{
            if(em != null && em.isOpen()){
                em.close();
            }
        }
    }

    @Override
    public void deleteByKey(Long id) {
        //TODO
    }

    @Override
    public void create(Artist entity) {
        EntityTransaction tx = null;
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(entity);
            tx.commit();
        }catch(Exception e){
            e.printStackTrace();
            if(tx != null) {
                tx.rollback();
            }
        }finally{
            if(em != null && em.isOpen()){
                em.close();
            }
        }
    }
}
