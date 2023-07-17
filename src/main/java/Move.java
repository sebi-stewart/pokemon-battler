public class Move {

    private int moveID;

    private String name;

    private int power;

    private int accuracy;

    private Type type;

    private MoveCategory category;

    public int getMoveID() {
        return moveID;
    }

    public void setMoveID(int moveID) {
        this.moveID = moveID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
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

    public Move(int moveID, String name, int power, int accuracy, Type type, MoveCategory category){
        this.moveID = moveID;
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
        this.type = type;
        this.category = category;
    }

    @Override
    public String toString(){
        return "Move{" +
                "moveID=" + moveID +
                ", name=" + name +
                ", power=" + power +
                ", accuracy=" + accuracy + "%" +
                ", type=" + type +
                ", category=" + category;
    }
}
