import hardcoded.*;
import hibernate.HibernateMain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class main {
    public static void main(String[] args){

        HibernateMain.initialConnection();

        // createPikachu()
        System.out.println(HibernateMain.getPokemon(22));
        System.out.println(HibernateMain.getMove(87));

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
