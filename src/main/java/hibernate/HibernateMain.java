package hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * This class contains the functions to access and modify the database
 * @author Sebastian Stewart
 */
public class HibernateMain {

    /**
     * Calls the initial connection to the database, so that the red hibernate text doesn't show up after the
     * first few interface options
     */
    public static void initialConnection() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if(session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
