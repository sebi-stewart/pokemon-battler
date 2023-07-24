import hardcoded.*;

import hibernate.HibernateMain;

import java.util.Scanner;
import java.util.Set;

public class dbManager {

    private final Scanner myScanner = new Scanner(System.in);

    private int userInt(String message){
        System.out.println(message);
        String input = myScanner.nextLine().toLowerCase().strip();
        int result=-1;
        if(input.equals("null") || input.equals("none") || input.equals("nothing")){
            return result;
        }

        try{
            result= Integer.parseInt(input);
        } catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    private String userString(String message){
        System.out.println(message);
        String input = myScanner.nextLine().strip();
        String output = input.substring(0, 1).toUpperCase() + input.substring(1);
        return output;
    }

    private Type userType(String entity){
        System.out.println("Enter " + entity + "'s the type: ");
        String input = myScanner.nextLine().toUpperCase().strip();
        Type result=null;
        if(input.equals("NULL") || input.equals("NONE") || input.equals("NOTHING")){
            return null;
        }

        try{
            result=Type.valueOf(input);
        } catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    private MoveCategory userCategory(String entity){
        System.out.println("Enter " + entity + "'s the category: ");
        String input = myScanner.nextLine().toUpperCase().strip();
        MoveCategory result=null;
        if(input.equals("NULL") || input.equals("NONE") || input.equals("NOTHING")){
            return null;
        }

        try{
            result=MoveCategory.valueOf(input);
        } catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    private StatusChanges userStatus(String entity){
        System.out.println("Enter " + entity + "'s the status effect: ");
        String input = myScanner.nextLine().toUpperCase().strip();
        StatusChanges result=null;
        if(input.equals("NULL") || input.equals("NONE") || input.equals("NOTHING")){
            return null;
        }

        try{
            result=StatusChanges.valueOf(input);
        } catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    public void createPokemon(){
        int pokemonID = userInt("What is the Pokemon's ID: ");
        String pokemonName = userString("What is the Pokemon's name: ");
        Type[] types = new Type[]{userType("Pokemon"), userType("Pokemon")};
        int[] baseStats = new int[]{userInt("What is the Pokemon's Health: "),
                                    userInt("What is the Pokemon's Attack: "),
                                    userInt("What is the Pokemon's Defense: "),
                                    userInt("What is the Pokemon's Special Attack: "),
                                    userInt("What is the Pokemon's Special Defense: "),
                                    userInt("What is the Pokemon's Speed: ")};
        Set<Move> moves=null;

        HibernateMain.createPokemon(pokemonID, pokemonName, types, baseStats, moves);
    }

    public void createMove(){
        int moveID = userInt("What is the Move's ID: ");
        String name = userString("What is the Move's name: ");
        int power = userInt("What is the Move's power: ");
        int accuracy = userInt("What is the Move's accuracy: ");
        int power_point = userInt("How many PP does this move have: ");
        Type type = userType("Move");
        MoveCategory category = userCategory("Move");
        StatusChanges statusEffect = userStatus("Move");
        int statusChance = userInt("What are the chances of the Move's status affect: ");

        HibernateMain.createMove(moveID, name, power, accuracy, power_point, type, category, statusEffect, statusChance);
    }

    public dbManager(){
        HibernateMain.initialConnection();
    }
}
