package ir.ac.kntu;

import ir.ac.kntu.util.RandomHelper;

public class Gun {
    private int damage;

    private int accuracy;

    private Caliber caliber;

    public Gun(int damage, int accuracy) {
        this.damage = damage;
        this.accuracy = accuracy;
        setCaliber();
        this.accuracy += (int) ((double) accuracy * caliber.getAccuracy() / 100);
        this.damage += caliber.getDamage();
    }

    public Gun(Gun gun) {
        this.damage = gun.damage;
        this.accuracy = gun.accuracy;
        this.caliber = gun.caliber;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public Caliber getCaliber() {
        return caliber;
    }

    private void setCaliber() {
        int a = RandomHelper.nextInt();
        if (a % 2 == 0) {
            caliber = Caliber.C5;
        } else {
            caliber = Caliber.C7;
        }
    }

    @Override
    public String toString() {
        String string = "Damage: " + damage + "| ";
        string += "Accuracy: " + accuracy + "| ";
        return string;
    }
}
