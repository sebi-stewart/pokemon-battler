package hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import hardcoded.*;
import org.hibernate.query.Query;
import party.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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

    public static Move createMove (int moveID, String name, int power, int accuracy, int power_point, Type type, MoveCategory category, StatusChanges statusEffect, int statusChance){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Move m1 = new Move();
        try {
            session.beginTransaction();
            m1.setMoveID((short) moveID);
            m1.setName(name);
            m1.setPower((short) power);
            m1.setAccuracy((byte) accuracy);
            m1.setPower_point((byte) power_point);
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

    public static PartyMon createPartyMon(int pokemonID, int level){
        Session session = HibernateUtil.getSessionFactory().openSession();

        Pokemon mon = getPokemon(pokemonID);
        if (mon==null){return null;}

        PartyMon p1 = new PartyMon(mon, level);
        try {
            session.beginTransaction();
            p1.setPartyMonID(1);

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

    public static Party createParty(int[] partyMonIDs){
        Set<PartyMon> partyMons = new HashSet<>();
        PartyMon newMon;
        Party p1 = new Party();

        for (int id : partyMonIDs){
            newMon = getPartyMon(id);
            if (Objects.isNull(newMon)){
                System.out.println("This party mon " + id + " does not exist");
                return null;
            } else if (Objects.isNull(assignParty(newMon, p1))){
                return null;
            }

            // System.out.println(newMon);
            partyMons.add(newMon);
        }

        p1.setPartyMons(partyMons);

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            for (PartyMon mon : partyMons){
                // System.out.println(mon);
                session.update(mon);
            }

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

    public static Pokemon getPokemon(int pokemonID){
        String hql = "FROM Pokemon p WHERE p.pokemonID = ".concat(Integer.toString(pokemonID));
        Pokemon result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query<Pokemon> getPokemonList = session.createQuery(hql);

            List<Pokemon> results = getPokemonList.list();

            session.getTransaction().commit();

            if (results.size() != 0){
                result = results.get(0);
            }

        } catch (HibernateException e) {
            if(session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
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

            Query<Move> getMove = session.createQuery(hql);

            List<Move> results = getMove.list();

            session.getTransaction().commit();

            if (results.size() != 0){
                result = results.get(0);
            }

        } catch (HibernateException e) {
            if(session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
        return result;
    }

    public static PartyMon getPartyMon(int partyMonID){
        String hql = "FROM PartyMon p WHERE p.partyMonID = ".concat(Integer.toString(partyMonID));
        PartyMon result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query<PartyMon> getPartyMonList = session.createQuery(hql);

            List<PartyMon> results = getPartyMonList.list();

            session.getTransaction().commit();

            if (results.size() != 0){
                result = results.get(0);
            }

        } catch (HibernateException e) {
            if(session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
        return result;

    }

    public static Party getParty(int partyID){
        String hql = "FROM Party p WHERE p.partyID = ".concat(Integer.toString(partyID));
        Party result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query<Party> getPartyList = session.createQuery(hql);

            List<Party> results = getPartyList.list();

            session.getTransaction().commit();

            if (results.size() != 0){
                result = results.get(0);
            }

        } catch (HibernateException e) {
            if(session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
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
        baseMon.setName(pokemonName);
        baseMon.setTypes(types);
        baseMon.setBaseStats(baseStats);

        return updateMon(baseMon);

    }

    private static Pokemon updateMon(Pokemon baseMon) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

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

    public static Move updateMove(int moveID, String name, int power, int accuracy, int power_point, Type type, MoveCategory category, StatusChanges statusEffect, int statusChance){
        Move baseMove = getMove(moveID);
        if (baseMove==null) {return null;}

        if (name==null){name=baseMove.getName();}
        if (power==-1){power=baseMove.getPower();}
        if (accuracy==-1){accuracy=baseMove.getAccuracy();}
        if (power_point==-1){power_point=baseMove.getPower_point();}
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
            baseMove.setPower_point((byte) power_point);
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

    public static PartyMon updatePartyMon(int partyMonID, int partyID, int pokemonID, int level, int currentHealth){
        PartyMon partyMon = getPartyMon(partyMonID);
        if (partyMon==null) {return null;}

        Pokemon mon;
        Party party = null;

        if (partyID==-1){
            party=partyMon.getMyParty();
        } else if (partyID==-2){
            System.out.println("Removed party");
        } else {
            party=getParty(partyID);
        }
        if (pokemonID==-1){
            mon=partyMon.getPartyPokemon();
        } else{
            mon=getPokemon(pokemonID);
        }
        if (level==-1){level=partyMon.getLevel();}
        if (currentHealth==-1){currentHealth=partyMon.getCurrentHealth();}

        int health_dif = partyMon.getTotal_health() - currentHealth;

        partyMon.setMyParty(party);
        partyMon.setPartyPokemon(mon);
        partyMon.setLevel((byte)level);
        partyMon.setTotals();

        int newCurrentHealth = partyMon.getTotal_health() - health_dif;

        partyMon.setCurrentHealth((short)newCurrentHealth);

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            session.update(partyMon);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if(session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
        return partyMon;

    }

    public static Party updateParty(int partyID, Set<PartyMon> mons){

        Party updater = getParty(partyID);
        if (Objects.isNull(updater)){return null;}
        if (mons==null){return null;}

        for (PartyMon oldMon : updater.getPartyMons()){
            rescindParty(oldMon);
        }

        updater.setPartyMons(mons);

        for (PartyMon mon : mons){
            System.out.println(mon.getMyParty());
            assignParty(mon, updater);
            System.out.println(mon.getMyParty());
        }

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            for (PartyMon mon : mons){
                // System.out.println(mon);
                session.update(mon);
            }

            session.update(updater);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if(session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
        return updater;
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

    public static void deletePartyMon(int partyMonID){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            PartyMon delMon = getPartyMon(partyMonID);
            session.delete(delMon);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }

    }

    public static void deleteParty(int partyID){
        Party delParty = getParty(partyID);
        for (PartyMon mon : delParty.getPartyMons()){
            rescindParty(mon);
        }

        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();


            session.delete(delParty);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
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

        if(!(baseMon.addMove(newMove))){
            System.out.println("Duplicate Error: Move=" + newMove + " is already contained in Pokemon=" + baseMon);return null;}

        return updateMon(baseMon);
    }

    public static Pokemon rescindMove(int pokemonID, int moveID){
        Pokemon baseMon = getPokemon(pokemonID);
        Move oldMove = getMove(moveID);
        return rescindMove(baseMon, oldMove);
    }

    public static Pokemon rescindMove(Pokemon baseMon, int moveID){
        Move oldMove = getMove(moveID);
        return rescindMove(baseMon, oldMove);
    }

    public static Pokemon rescindMove(int pokemonID, Move oldMove){
        Pokemon baseMon = getPokemon(pokemonID);
        return rescindMove(baseMon, oldMove);
    }

    public static Pokemon rescindMove(Pokemon baseMon, Move oldMove){
        if (baseMon==null || oldMove==null){return null;}

        if(!(baseMon.rescindMove(oldMove))){
            System.out.println("Missing Error: Move=" + oldMove + " is not contained in Pokemon=" + baseMon);return null;}

        return updateMon(baseMon);
    }

    public static PartyMon assignParty(PartyMon mon, Party party){
        if (Objects.isNull(mon.getMyParty())){
            mon.setMyParty(party);
            return mon;
        } else {
            System.out.println("This mon " + mon + " already has a party assigned");
            return null;
        }
    }

    public static PartyMon rescindParty(PartyMon mon){
        mon.setMyParty(null);
        updatePartyMon(mon.getPartyMonID(), -2, -1, -1, -1);
        return mon;
    }

}
