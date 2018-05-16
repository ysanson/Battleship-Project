package sanson.yvan;

public enum ShipType {
    aircraftCarrier("Aircraft Carrier", 5),
    battleship("Battleship", 4),
    cruiser("Cruiser", 3),
    submarine("Submarine", 3),
    destroyer("Destroyer", 2);

    private String name;
    private int length;

    ShipType(String name, int length){
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }
    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "ShipType{" +
                "name='" + name + '\'' +
                ", length=" + length +
                '}';
    }
}
