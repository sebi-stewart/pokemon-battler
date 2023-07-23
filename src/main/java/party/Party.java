package party;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "party")
public class Party {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "party_id", updatable = false, nullable = false)
    private int partyID;

    @OneToMany(mappedBy= "myParty", fetch=FetchType.EAGER)
    private Set<PartyMon> partyMons = new HashSet<>();

    public int getPartyID() {
        return partyID;
    }

    public void setPartyID(int partyID) {
        this.partyID = partyID;
    }

    public Set<PartyMon> getPartyMons() {
        return partyMons;
    }

    public void setPartyMons(Set<PartyMon> partyMons) {
        this.partyMons = partyMons;
    }

    public Party() {
    }

    @Override
    public String toString() {
        return "Party{" +
                "partyID=" + partyID +
                ", partyMons=" + partyMons +
                '}';
    }
}
