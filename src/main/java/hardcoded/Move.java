package hardcoded;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "moves")
public class Move {

    @Id
    @Column(name = "move_id", updatable = false, nullable = false)
    private short moveID;

    @Column(name = "name")
    private String name;

    @Column(name = "move_power")
    private short power;

    @Column(name = "accuracy")
    private byte accuracy;

    @Column(name = "power_point")
    private byte power_point;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Enumerated(EnumType.STRING)
    @Column (name = "category")
    private MoveCategory category;

    @Column (name = "status_effect")
    private StatusChanges statusEffect;

    @Column (name = "status_chance")
    private byte statusChance;

    @ManyToMany(mappedBy = "moves", fetch=FetchType.EAGER)
    private Set<Pokemon> pokemon = new HashSet<>();

    public short getMoveID() {
        return moveID;
    }

    public void setMoveID(short moveID) {
        this.moveID = moveID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getPower() {
        return power;
    }

    public void setPower(short power) {
        this.power = power;
    }

    public byte getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(byte accuracy) {
        this.accuracy = accuracy;
    }

    public byte getPower_point() {
        return power_point;
    }

    public void setPower_point(byte power_point) {
        this.power_point = power_point;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public MoveCategory getCategory() {
        return category;
    }

    public void setCategory(MoveCategory category) {
        this.category = category;
    }

    public StatusChanges getStatusEffect() {
        return statusEffect;
    }

    public void setStatusEffect(StatusChanges statusEffect) {
        this.statusEffect = statusEffect;
    }

    public byte getStatusChance() {
        return statusChance;
    }

    public void setStatusChance(byte statusChance) {
        this.statusChance = statusChance;
    }

    public Set<Pokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(Set<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }

    public void removePokemon(Pokemon p){
        this.pokemon.remove(p);
        p.getMoves().remove(this);
    }

    public boolean compareMove(Move m1){
        return this.moveID == m1.getMoveID() && Objects.equals(this.name, m1.getName()) &&
                this.power == m1.getPower() && this.accuracy == m1.getAccuracy() &&
                this.type == m1.getType() && this.category == m1.getCategory() &&
                this.statusEffect == m1.getStatusEffect() && this.statusChance == m1.getStatusChance();
    }

    public Move(){

    }

    public Move(short moveID, String name, int power, int accuracy, int power_point, Type type, MoveCategory category, StatusChanges statusEffect, int statusChance){
        this.moveID = moveID;
        this.name = name;
        this.power = (short) power;
        this.accuracy = (byte) accuracy;
        this.power_point = (byte) power_point;
        this.type = type;
        this.category = category;
        this.statusEffect = statusEffect;
        this.statusChance = (byte) statusChance;
    }

    @Override
    public String toString(){
        return name + "{" +
                "moveID=" + moveID +
                ", power=" + power +
                ", accuracy=" + accuracy + "%" +
                ", PP=" + power_point +
                ", type=" + type +
                ", category=" + category +
                ", status_effect=" + statusEffect +
                ", status_chance=" + statusChance +
                "}";
    }
}
