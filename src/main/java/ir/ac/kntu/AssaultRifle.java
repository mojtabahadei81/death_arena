package ir.ac.kntu;

import ir.ac.kntu.util.RandomHelper;

public class AssaultRifle extends Gun {
    public AssaultRifle() {
        super(10, 50);
    }

    @Override
    public String toString() {
        String string = "Damage: " + getDamage() + "| ";
        string += "Accuracy: " + getAccuracy() + "| ";
        string += "Gun: AR | ";
        if (super.getCaliber() == Caliber.C5) {
            string += "Caliber Size: 5mm";
        } else if (super.getCaliber() == Caliber.C7) {
            string += "Caliber Size: 7mm";
        }
        return string;
    }
}