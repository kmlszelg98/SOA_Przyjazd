import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import start.DataBase;

/**
 * Created by Kamil on 19.05.2017.
 */
public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        DataBase dataBase = new DataBase();
        /*final Session session = getSession();


        System.out.println("querying all the managed entities...");
        final Metamodel metamodel = session.getSessionFactory().getMetamodel();
        final Query query = session.createQuery("from Place");

        Transaction transaction = session.beginTransaction();
        Place entity = new Place();
        entity.setStrefa(1);
        entity.setWolne("true");

        session.save(entity);
        transaction.commit();

        session.clear();
        session.close();*/

    }
}