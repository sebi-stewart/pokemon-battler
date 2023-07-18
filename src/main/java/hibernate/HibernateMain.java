package hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import hardcoded.*;

import java.util.Set;

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

    public static Pokemon createPokemon(int pokemonID, String pokemonName, Type[] types, int[] baseStats, Set<Move> moves){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Pokemon p1 = new Pokemon();
        try {
            session.beginTransaction();
            p1.setPokemonID((short) pokemonID);
            p1.setName(pokemonName);
            p1.setTypes(types);
            p1.setBaseStats(baseStats);
            p1.setMoves(moves);

            session.save(p1);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if(session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
        return p1;

    }

    public static Move createMove (int moveID, String name, int power, int accuracy, Type type, MoveCategory category){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Move m1 = new Move();
        try {
            session.beginTransaction();
            m1.setMoveID((short) moveID);
            m1.setName(name);
            m1.setPower((short) power);
            m1.setAccuracy((byte) accuracy);
            m1.setType(type);
            m1.setCategory(category);

            session.save(m1);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            if(session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
        return m1;
    }
}
