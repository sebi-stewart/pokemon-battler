import hardcoded.*;
import hibernate.HibernateMain;
import party.PartyMon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class main {
    public static void main(String[] args){

        HibernateMain.initialConnection();

//         deletePartyMon();
    }

    public static void deletePartyMon(){
        HibernateMain.deletePartyMon(3);
    }

    public static void getGengar(){
        Pokemon gengar = HibernateMain.getPokemon(94);
        System.out.println(gengar.longString(true));
        System.out.println(gengar.getPartyMons());
    }

    public static void createPartyMon(){
        PartyMon gengarP1 = HibernateMain.createPartyMon(94, 97);

        assert gengarP1 != null;
        System.out.println(gengarP1.longString());
    }


    public static void deletePokemonMove(){
        System.out.println(HibernateMain.getPokemon(94));

        HibernateMain.deletePokemon(94);
        HibernateMain.deleteMove(247);

         HibernateMain.rescindMove(94, 86);
    }

    public static void createGengar(){
        Move shadowBall = HibernateMain.createMove(247, "Shadow Ball", 80, 100, 15, Type.GHOST, MoveCategory.SPECIAL, StatusChanges.SPD_DOWN, 20);

        Type[] pokeType = {Type.GHOST, null};
        Set<Move> moves = new HashSet<>(List.of(shadowBall));
        int[] stats = new int[]{60, 65, 60, 130, 75, 110};

        Pokemon gengar = HibernateMain.createPokemon(94, "Gengar", pokeType, stats, moves);
    }

    public void updateStuff(){
        HibernateMain.updatePokemon(22, null, null, new int[] {35, 55, 30, 50, 40 ,90});
        HibernateMain.updateMove(85, "Thunderbolt", 90, 100, 15, Type.ELECTRIC, MoveCategory.SPECIAL, StatusChanges.PARALYSE, 10);


        // createPikachu()
        System.out.println(HibernateMain.getPokemon(22));
    }

    public static void createPikachu(){
        Move move0 = HibernateMain.createMove(85, "Thunderbolt", 90, 100, 15, Type.ELECTRIC, MoveCategory.SPECIAL, StatusChanges.PARALYSE, 10);
        Move move1 = HibernateMain.createMove(9, "Thunder Punch", 75, 100, 15, Type.ELECTRIC, MoveCategory.PHYSICAL, StatusChanges.PARALYSE, 10);
        Move move2 = HibernateMain.createMove(87, "Thunder", 110, 70, 10, Type.ELECTRIC, MoveCategory.SPECIAL, StatusChanges.PARALYSE, 30);
        Move move3 = HibernateMain.createMove(86, "Thunder Wave", 0, 90, 20, Type.ELECTRIC, MoveCategory.STATUS, StatusChanges.PARALYSE, 100);

        Type[] pokeType = {Type.ELECTRIC, null};
        Set<Move> moves = new HashSet<>(Arrays.asList(move0, move1, move2, move3));
        int[] stats = new int[]{35, 55, 30, 50, 40, 90};
        Pokemon pikachu = HibernateMain.createPokemon(22, "Pikachu", pokeType, stats, moves);

        System.out.println(pikachu);
    }

}
