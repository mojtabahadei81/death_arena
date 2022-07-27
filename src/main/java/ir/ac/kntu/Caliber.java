package ir.ac.kntu;

public enum Caliber {
    C5(0, 15),
    C7(10, -10);

    private final int damage;
    private final int accuracy;

    Caliber(int damage, int accuracy) {
        this.damage = damage;
        this.accuracy = accuracy;
    }

    public int getDamage() {
        return damage;
    }

    public int getAccuracy() {
        return accuracy;
    }
}