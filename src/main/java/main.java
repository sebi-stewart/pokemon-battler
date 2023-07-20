import hardcoded.*;
import hibernate.HibernateMain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class main {
    public static void main(String[] args){

        HibernateMain.initialConnection();

        //System.out.println(HibernateMain.getPokemon(94));

        //HibernateMain.deletePokemon(94);
        //HibernateMain.deleteMove(247);

        HibernateMain.assignMove(94, 86);
    }

    public static void createGengar(){
        Move shadowBall = HibernateMain.createMove(247, "Shadow Ball", 80, 100, Type.GHOST, MoveCategory.SPECIAL, StatusChanges.SPD_DOWN, 20);

        Type[] pokeType = {Type.GHOST, null};
        Set<Move> moves = new HashSet<>(Arrays.asList(shadowBall));
        int[] stats = new int[]{60, 65, 60, 130, 75, 110};

        Pokemon gengar = HibernateMain.createPokemon(94, "Gengar", pokeType, stats, moves);
    }

    public void updateStuff(){
        HibernateMain.updatePokemon(22, null, null, new int[] {35, 55, 30, 50, 40 ,90});
        HibernateMain.updateMove(85, "Thunderbolt", 90, 100, Type.ELECTRIC, MoveCategory.SPECIAL, StatusChanges.PARALYSE, 10);


        // createPikachu()
        System.out.println(HibernateMain.getPokemon(22));
    }

    public static void createPikachu(){
        Move move0 = HibernateMain.createMove(85, "Thunderbolt", 90, 100, Type.ELECTRIC, MoveCategory.SPECIAL, StatusChanges.PARALYSE, 10);
        Move move1 = HibernateMain.createMove(9, "Thunder Punch", 75, 100, Type.ELECTRIC, MoveCategory.PHYSICAL, StatusChanges.PARALYSE, 10);
        Move move2 = HibernateMain.createMove(87, "Thunder", 110, 70, Type.ELECTRIC, MoveCategory.SPECIAL, StatusChanges.PARALYSE, 30);
        Move move3 = HibernateMain.createMove(86, "Thunder Wave", 0, 90, Type.ELECTRIC, MoveCategory.STATUS, StatusChanges.PARALYSE, 100);

        Type[] pokeType = {Type.ELECTRIC, null};
        Set<Move> moves = new HashSet<>(Arrays.asList(move0, move1, move2, move3));
        int[] stats = new int[]{35, 55, 30, 50, 40, 90};
        Pokemon pikachu = HibernateMain.createPokemon(22, "Pikachu", pokeType, stats, moves);

        System.out.println(pikachu);
    }

}
