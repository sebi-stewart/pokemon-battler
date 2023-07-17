import java.util.ArrayList;
import java.util.List;

public class Pokemon {

    private int pokemonID;

    private String name;

    private List<Type> type = new ArrayList<>();

    private int[] baseStats = new int[6];

    private List<Move> moves = new ArrayList<>();

    public int getPokemonID() {
        return pokemonID;
    }

    public void setPokemonID(int pokemonID) {
        this.pokemonID = pokemonID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Type> getType() {
        return type;
    }

    public void setType(List<Type> type) {
        this.type = type;
    }

    public int[] getBaseStats() {
        return baseStats;
    }

    public void setBaseStats(int[] baseStats) {
        this.baseStats = baseStats;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Pokemon() {
    }

    public Pokemon(int pokemonID, String pokemonName, List<Type> pokemonType, int[] baseStats, List<Move> moves) {
        this.pokemonID = pokemonID;
        this.name = pokemonName;
        this.type = pokemonType;
        this.baseStats = baseStats;
        this.moves = moves;
    }

    @Override
    public String toString(){
        return "Pokemon{" +
                "pokemonID=" + pokemonID +
                ", name=" + name +
                ", type=" + type +
                ", baseStats=" + baseStats +
                ", moves=" + moves;
    }
}
