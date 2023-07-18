import hibernate.HibernateMain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class main {
    public static void main(String[] args){

        HibernateMain.initialConnection();

        Move move0 = new Move(0, "Thunderbolt", 90, 100, Type.ELECTRIC, MoveCategory.SPECIAL);
        Move move1 = new Move(1, "Thunder Punch", 75, 100, Type.ELECTRIC, MoveCategory.PHYSICAL);
        Move move2 = new Move(2, "Thunder", 110, 70, Type.ELECTRIC, MoveCategory.SPECIAL);
        Move move3 = new Move(3, "Thunder Wave", 0, 90, Type.ELECTRIC, MoveCategory.STATUS);

        Type[] pokeType = {Type.ELECTRIC, null};
        Set<Move> moves = new HashSet<>(Arrays.asList(move0, move1, move2, move3));
        int[] stats = new int[]{35, 55, 30, 50, 40, 90};
        Pokemon pikachu = new Pokemon(0, "Pikachu", pokeType, stats, moves);

        System.out.println(pikachu);


    }
}
