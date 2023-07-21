package party;

import hardcoded.Pokemon;

import javax.persistence.*;

@Entity
@Table(name = "party_mon")
public class PartyMon{

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "party_mon_id", updatable = false, nullable = false)
    private int partyMonID;

    @ManyToOne
    @JoinColumn(name="pokemon_id", nullable = false)
    private Pokemon pokemonID;

    @Column(name = "level")
    private byte level;

    @Enumerated(EnumType.STRING)
    @Column(name = "nature")
    private Nature nature;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_effect")
    private Status status_effect;

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

    public PartyMon() {
    }

    public PartyMon(Pokemon pokemonID) {
        this.partyMonID = 1;
        this.pokemonID = pokemonID;
    }

    private void setTotals(){
        short[] baseStats = pokemonID.getBaseStats();
        this.total_health = (short)((short)(((float)(2*baseStats[0])*level)/100) + level + 10);
        this.total_atk = calcBeforeNature(baseStats[1], 0, 0)
    }

    private float calcBeforeNature(int baseStat, int IV, float EV){
        float topFraction = (2*baseStat + IV + EV/4);
        float centreBracket = (float)Math.floor(topFraction/100) + 5;
        return centreBracket;
    }
}
