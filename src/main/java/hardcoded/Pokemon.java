package hardcoded;

import party.PartyMon;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @Column(name = "pokemon_id", updatable = false, nullable = false)
    private short pokemonID;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "primary_type")
    private Type primary_type;

    @Enumerated(EnumType.STRING)
    @Column(name = "secondary_type")
    private Type secondary_type;

    @Column(name = "health")
    private short health;
    @Column(name = "attack")
    private short attack;
    @Column(name = "defense")
    private short defense;
    @Column(name = "special_attack")
    private short special_attack;
    @Column(name = "special_defense")
    private short special_defense;
    @Column(name = "speed")
    private short speed;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch=FetchType.EAGER)
    @JoinTable(
            name = "pokemon_move",
            joinColumns = {@JoinColumn(name = "pokemon_id")},
            inverseJoinColumns = {@JoinColumn(name = "move_id")}
    )
    private Set<Move> moves = new HashSet<>();

    @OneToMany(mappedBy="pokemonID")
    private Set<PartyMon> partyMons;

    public short getPokemonID() {
        return pokemonID;
    }

    public void setPokemonID(short pokemonID) {
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

    public short[] getBaseStats() {
        return new short[] {health, attack, defense, special_attack, special_defense, speed};
    }

    public void setBaseStats(int[] baseStats) {
        this.health = (short) baseStats[0];
        this.attack = (short) baseStats[1];
        this.defense = (short) baseStats[2];
        this.special_attack = (short) baseStats[3];
        this.special_defense = (short) baseStats[4];
        this.speed = (short) baseStats[5];
    }

    public Set<Move> getMoves() {
        return moves;
    }

    public void setMoves(Set<Move> moves) {
        this.moves = moves;
    }

    public boolean addMove(Move move){if (isPresent(move)==null){ moves.add(move); return true;} return false;}

    public boolean rescindMove(Move move) {
        Move remover = isPresent(move);
        if (remover!=null){
            return moves.remove(remover);
        } return false;
    }

    public Move isPresent(Move newMove){
        for (Move m1 : moves){
            if (newMove.compareMove(m1)){
                return m1;
            }
        } return null;
    }

    public Pokemon() {
    }

    public Pokemon(int pokemonID, String pokemonName, Type[] types, int[] baseStats, Set<Move> moves) {
        this.pokemonID = (short) pokemonID;
        this.name = pokemonName;
        this.primary_type = types[0];
        this.secondary_type = types[1];
        this.health = (short) baseStats[0];
        this.attack = (short) baseStats[1];
        this.defense = (short) baseStats[2];
        this.special_attack = (short) baseStats[3];
        this.special_defense = (short) baseStats[4];
        this.speed = (short) baseStats[5];
        this.moves = moves;
    }

    @Override
    public String toString(){
        return name + "{" +
                "pokemonID=" + pokemonID + "}";
    }

    public String longString(boolean show_moves){
        if (show_moves){
        return name + "{" +
                "pokemonID=" + pokemonID +
                ", type={" + primary_type + ", " + secondary_type + "}" +
                ", baseStats={" + health + ", " +  attack + ", " +
                defense + ", " + special_attack + ", " +
                special_defense + ", " + speed + "}" +
                ", moves=" + moves + "}";}
        return name + "{" +
                "pokemonID=" + pokemonID +
                ", type={" + primary_type + ", " + secondary_type + "}" +
                ", baseStats={" + health + ", " +  attack + ", " +
                defense + ", " + special_attack + ", " +
                special_defense + ", " + speed + "}" + "}";
    }
}
