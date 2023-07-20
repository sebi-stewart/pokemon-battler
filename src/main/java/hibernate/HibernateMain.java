package hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import hardcoded.*;
import org.hibernate.query.Query;

import java.util.List;
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

    public static Move createMove (int moveID, String name, int power, int accuracy, Type type, MoveCategory category, StatusChanges statusEffect, int statusChance){
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
            m1.setStatusEffect(statusEffect);
            m1.setStatusChance((byte) statusChance);

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

    public static Pokemon getPokemon(int pokemonID){
        String hql = "FROM Pokemon p WHERE p.pokemonID = ".concat(Integer.toString(pokemonID));
        Pokemon result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query getPokemon = session.createQuery(hql);

            List<Pokemon> results = getPokemon.list();

            session.getTransaction().commit();

            if (results.size() != 0){
                result = results.get(0);
            }

        } catch (HibernateException e) {
            if(session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;

    }

    public static Move getMove(int moveID){
        String hql = "FROM Move m WHERE m.moveID = ".concat(Integer.toString(moveID));
        Move result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query getMove = session.createQuery(hql);

            List<Move> results = getMove.list();

            session.getTransaction().commit();

            if (results.size() != 0){
                result = results.get(0);
            }

        } catch (HibernateException e) {
            if(session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    public static Pokemon updatePokemon(int pokemonID, String pokemonName, Type[] types, int[] baseStats){
        Pokemon baseMon = getPokemon(pokemonID);
        if (baseMon==null) {return null;}

        if (pokemonName==null){pokemonName=baseMon.getName();}
        if (types==null){types=baseMon.getTypes();}
        if (baseStats==null){
            short[] oldStats=baseMon.getBaseStats();
            int[] newStats = new int[6];
            for (byte i=0; i<6; i++ ){
                newStats[i]=oldStats[i];
            }
            baseStats=newStats;
        }

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            baseMon.setName(pokemonName);
            baseMon.setTypes(types);
            baseMon.setBaseStats(baseStats);

            session.update(baseMon);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if(session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
        return baseMon;

    }

    public static Move updateMove(int moveID, String name, int power, int accuracy, Type type, MoveCategory category, StatusChanges statusEffect, int statusChance){
        Move baseMove = getMove(moveID);
        if (baseMove==null) {return null;}

        if (name==null){name=baseMove.getName();}
        if (power==-1){power=baseMove.getPower();}
        if (accuracy==-1){accuracy=baseMove.getAccuracy();}
        if (type==null){type=baseMove.getType();}
        if (category==null){category=baseMove.getCategory();}
        if (statusEffect==null){statusEffect=baseMove.getStatusEffect();}
        if (statusChance==-1){statusChance=baseMove.getStatusChance();}

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            //noinspection DuplicatedCode
            baseMove.setName(name);
            baseMove.setPower((short) power);
            baseMove.setAccuracy((byte) accuracy);
            baseMove.setType(type);
            baseMove.setCategory(category);
            baseMove.setStatusEffect(statusEffect);
            baseMove.setStatusChance((byte) statusChance);

            session.update(baseMove);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if(session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
        return baseMove;

    }

    public static void deletePokemon(int pokemonID){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Pokemon delPok = getPokemon(pokemonID);
            session.delete(delPok);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }

    }

    public static void deleteMove(int moveID){
        Move delMove = getMove(moveID);

        deleteAllRelations(delMove);

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.delete(delMove);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }

    }

    public static void deleteAllRelations(Pokemon pokemon){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            pokemon.setMoves(null);

            session.merge(pokemon); // important

            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
    }

    public static void deleteAllRelations(Move move){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            for (Pokemon p : move.getPokemon()){
                move.removePokemon(p);
                session.update(p);
            }

            session.update(move); // important

            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
    }

    public static Pokemon assignMove(int pokemonID, int moveID){
        Pokemon baseMon = getPokemon(pokemonID);
        Move newMove = getMove(moveID);
        return assignMove(baseMon, newMove);
    }

    public static Pokemon assignMove(Pokemon baseMon, int moveID){
        Move newMove = getMove(moveID);
        return assignMove(baseMon, newMove);
    }

    public static Pokemon assignMove(int pokemonID, Move newMove){
        Pokemon baseMon = getPokemon(pokemonID);
        return assignMove(baseMon, newMove);
    }

    public static Pokemon assignMove(Pokemon baseMon, Move newMove){
        if (baseMon==null || newMove==null){return null;}

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            baseMon.addMove(newMove);

            session.update(baseMon);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if(session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
        return baseMon;
    }

}
