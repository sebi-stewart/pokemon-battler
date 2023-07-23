package party;

import hardcoded.Move;
import hardcoded.Pokemon;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "party_mon")
public class PartyMon{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "party_mon_id", updatable = false, nullable = false)
    private int partyMonID;

    @ManyToOne(targetEntity = Party.class)
    @JoinColumn(name="party_id", referencedColumnName = "party_id")
    private Party myParty;

    @ManyToOne(targetEntity = Pokemon.class)
    @JoinColumn(name="pokemon_id", referencedColumnName = "pokemon_id", nullable = false)
    private Pokemon partyPokemon;

    @Column(name = "level")
    private byte level;

    @Enumerated(EnumType.STRING)
    @Column(name = "nature")
    private Nature nature;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_effect")
    private Status status_effect=null;

    @Column(name = "total_health")
    private short total_health;

    @Column(name = "total_atk")
    private short total_atk;

    @Column(name = "total_def")
    private short total_def;

    @Column(name = "total_spa")
    private short total_spa;

    @Column(name = "total_spd")
    private short total_spd;

    @Column(name = "total_spe")
    private short total_spe;

    @Column(name = "current_health")
    private short currentHealth;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch=FetchType.EAGER)
    @JoinTable(
            name = "party_mon_move",
            joinColumns = {@JoinColumn(name = "party_mon_id")},
            inverseJoinColumns = {@JoinColumn(name = "move_id")}
    )
    private Set<Move> moves = new HashSet<>();

    public int getPartyMonID() {
        return partyMonID;
    }

    public void setPartyMonID(int partyMonID) {
        this.partyMonID = partyMonID;
    }

    public Party getMyParty() {
        return myParty;
    }

    public void setMyParty(Party myParty) {
        this.myParty = myParty;
    }

    public Pokemon getPartyPokemon() {
        return partyPokemon;
    }

    public void setPartyPokemon(Pokemon pokemonID) {
        this.partyPokemon = pokemonID;
    }

    public byte getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    public Nature getNature() {
        return nature;
    }

    public void setNature(Nature nature) {
        this.nature = nature;
    }

    public Status getStatus_effect() {
        return status_effect;
    }

    public void setStatus_effect(Status status_effect) {
        this.status_effect = status_effect;
    }

    public short getTotal_health() {
        return total_health;
    }

    public void setTotal_health(short total_health) {
        this.total_health = total_health;
    }

    public short getTotal_atk() {
        return total_atk;
    }

    public void setTotal_atk(short total_atk) {
        this.total_atk = total_atk;
    }

    public short getTotal_def() {
        return total_def;
    }

    public void setTotal_def(short total_def) {
        this.total_def = total_def;
    }

    public short getTotal_spa() {
        return total_spa;
    }

    public void setTotal_spa(short total_spa) {
        this.total_spa = total_spa;
    }

    public short getTotal_spd() {
        return total_spd;
    }

    public void setTotal_spd(short total_spd) {
        this.total_spd = total_spd;
    }

    public short getTotal_spe() {
        return total_spe;
    }

    public void setTotal_spe(short total_spe) {
        this.total_spe = total_spe;
    }

    public short getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(short currentHealth) {
        this.currentHealth = currentHealth;
    }

    public PartyMon() {
    }

    public PartyMon(Pokemon partyPokemon, int level) {
        this.partyMonID = 1;
        this.partyPokemon = partyPokemon;
        this.level = (byte) level;
        this.nature = Nature.getRandomNature();
        setTotals();
        this.currentHealth = total_health;
    }

    public void setTotals(){
        short[] baseStats = partyPokemon.getBaseStats();
        this.total_health = (short)((short)(((float)(2*baseStats[0])*level)/100) + level + 10);
        this.total_atk = (short)Math.floor(calcBeforeNature(baseStats[1], 0, 0) * getNatureMultiplier(1));
        this.total_def = (short)Math.floor(calcBeforeNature(baseStats[2], 0, 0) * getNatureMultiplier(2));
        this.total_spa = (short)Math.floor(calcBeforeNature(baseStats[3], 0, 0) * getNatureMultiplier(3));
        this.total_spd = (short)Math.floor(calcBeforeNature(baseStats[4], 0, 0) * getNatureMultiplier(4));
        this.total_spe = (short)Math.floor(calcBeforeNature(baseStats[5], 0, 0) * getNatureMultiplier(5));

    }

    private float calcBeforeNature(int baseStat, int IV, float EV){
        float topFraction = (2*baseStat + IV + EV/4)*level;
        return (float)Math.floor(topFraction/100) + 5;
    }

    private float getNatureMultiplier(int index){
        return switch(index){
            case 1: yield switch(nature) {
                case BOLD, MODEST, CALM, TIMID: yield 0.9f;
                case LONELY, ADAMANT, NAUGHTY, BRAVE: yield 1.1f;
                default: yield 1.0f;};
            case 2: yield switch(nature) {
                case LONELY, MILD, GENTLE, HASTY: yield 0.9f;
                case BOLD, IMPISH, LAX, RELAXED: yield 1.1f;
                default: yield 1.0f;};
            case 3: yield switch(nature) {
                case ADAMANT, IMPISH, CAREFUL, JOLLY: yield 0.9f;
                case MODEST, MILD, RASH, QUIET: yield 1.1f;
                default: yield 1.0f;};
            case 4: yield switch(nature) {
                case NAUGHTY, LAX, RASH, NAIVE: yield 0.9f;
                case CALM, GENTLE, CAREFUL, SASSY: yield 1.1f;
                default: yield 1.0f;};
            case 5: yield switch(nature) {
                case BRAVE, RELAXED, QUIET, SASSY: yield 0.9f;
                case TIMID, HASTY, JOLLY, NAIVE: yield 1.1f;
                default: yield 1.0f;};
            default: yield 1.0f;
        };
    }

    @Override
    public String toString() {
        return "PartyMon{" +
                "partyMonID=" + partyMonID +
                ", pokemonID=" + partyPokemon +
                ", level=" + level +
                '}';
    }

    public String longString(){
        return "PartyMon{" +
                "partyMonID=" + partyMonID +
                ", pokemonID=" + partyPokemon.longString(false) +
                ", level=" + level +
                '}';
    }
}
