package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryS {

    private static EntityManagerFactory emf = null;

    private EntityManagerFactoryS(){

    }

    public static EntityManagerFactory getInstance(){
        if (emf == null){
            emf = Persistence.createEntityManagerFactory("jpatraining");
        }
        return emf;
    }
}
