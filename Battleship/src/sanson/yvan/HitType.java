package sanson.yvan;
public enum HitType {
    Hit("Hit", 1),
    Sank("Sank", 2),
    Miss("Miss", 0);

    private String type;
    private int level;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    HitType(String type, int level) {
        this.type = type;
        this.level = level;
    }

    @Override
    public String toString() {
        return type;
    }
}
