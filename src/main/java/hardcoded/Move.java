package hardcoded;

import javax.persistence.*;
import java.util.HashSet;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "type_id")
    private Type type;

    @Enumerated(EnumType.STRING)
    @Column (name = "category_id")
    private MoveCategory category;

    @ManyToMany(mappedBy = "moves")
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

    public Move(){

    }

    public Move(short moveID, String name, short power, byte accuracy, Type type, MoveCategory category){
        this.moveID = moveID;
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
        this.type = type;
        this.category = category;
    }

    @Override
    public String toString(){
        return name + "{" +
                "moveID=" + moveID +
                ", power=" + power +
                ", accuracy=" + accuracy + "%" +
                ", type=" + type +
                ", category=" + category + "}";
    }
}
