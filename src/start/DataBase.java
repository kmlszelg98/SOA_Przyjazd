package start;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import strefy.Place;

import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;


public class DataBase {

    private static SessionFactory sessionFactory;
    private static Session session;
    private static ArrayList<Place> list = new ArrayList<>();
    private static Transaction transaction;

    public DataBase(){
        generate();
    }

    public void generate(){

        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            sessionFactory = configuration.buildSessionFactory();
            session = getSession();

            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from Place ");
                for (Object o : query.list()) {
                    list.add((Place)o);
                }
            }


        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }



    public void finish(){
        if(session!=null && session.isOpen()) session.close();
    }

    public void add(Place klient){
        transaction = session.beginTransaction();
        session.save(klient);
        transaction.commit();
        list.add(klient);
    }

    public void remove(Place klient){
        transaction = session.beginTransaction();
        session.delete(klient);
        transaction.commit();
        list.remove(klient);
    }

    public void update(Place klient){
        transaction = session.beginTransaction();
        session.update(klient);
        transaction.commit();

    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    public static ArrayList<Place> getList() {
        return list;
    }
}
