import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @Column(name = "pokemon_id", updatable = false, nullable = false)
    private int pokemonID;

    @Column(name = "name")
    private String name;

    @Column(name = "primary_type")
    private Type primary_type;

    @Column(name = "secondary_type")
    private Type secondary_type;

    @Column(name = "health")
    private int health;
    @Column(name = "attack")
    private int attack;
    @Column(name = "defense")
    private int defense;
    @Column(name = "special_attack")
    private int special_attack;
    @Column(name = "special_defense")
    private int special_defense;
    @Column(name = "speed")
    private int speed;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "pokemon_move",
            joinColumns = {@JoinColumn(name = "pokemon_id")},
            inverseJoinColumns = {@JoinColumn(name = "move_id")}
    )
    private Set<Move> moves = new HashSet<>();

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

    public Type[] getTypes() {
        return new Type[] {primary_type, secondary_type};
    }

    public void setTypes(Type[] types) {
        this.primary_type = types[0];
        this.secondary_type = types[1];
    }

    public int[] getBaseStats() {
        return new int[] {health, attack, defense, special_attack, special_defense, speed};
    }

    public void setBaseStats(int[] baseStats) {
        this.health = baseStats[0];
        this.attack = baseStats[1];
        this.defense = baseStats[2];
        this.special_attack = baseStats[3];
        this.special_defense = baseStats[4];
        this.speed = baseStats[5];
    }

    public Set<Move> getMoves() {
        return moves;
    }

    public void setMoves(Set<Move> moves) {
        this.moves = moves;
    }

    public Pokemon() {
    }

    public Pokemon(int pokemonID, String pokemonName, Type[] types, int[] baseStats, Set<Move> moves) {
        this.pokemonID = pokemonID;
        this.name = pokemonName;
        this.primary_type = types[0];
        this.secondary_type = types[1];
        this.health = baseStats[0];
        this.attack = baseStats[1];
        this.defense = baseStats[2];
        this.special_attack = baseStats[3];
        this.special_defense = baseStats[4];
        this.speed = baseStats[5];
        this.moves = moves;
    }

    @Override
    public String toString(){
        return name + "{" +
                "pokemonID=" + pokemonID +
                ", type={" + primary_type + ", " + secondary_type + "}" +
                ", baseStats={" + health +", " +  attack +", " +
                defense + special_attack + ", " +
                special_defense + speed + "}" +
                ", moves=" + moves + "}";
    }
}
