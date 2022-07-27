package ir.ac.kntu;

import ir.ac.kntu.util.RandomHelper;

public class SniperRifle extends Gun {

    public SniperRifle() {
        super(20, 60);
        if (isZoom()) {
            int increaseHitProbable = RandomHelper.nextInt(11) + 5;
            setAccuracy(getAccuracy() + (int) (getAccuracy() * ((double) increaseHitProbable / 100)));
        }
    }

    private boolean isZoom() {
        int a = RandomHelper.nextInt(100);
        if (a % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String string = "Damage: " + super.getDamage() + "| ";
        string += "Accuracy: " + super.getAccuracy() + "| ";
        if (isZoom()) {
            string += "Gun: SR(scope) | ";
        } else {
            string += "Gun: SR | ";
        }
        if (super.getCaliber() == Caliber.C5) {
            string += "Caliber Size: 5mm";
        } else if (super.getCaliber() == Caliber.C7) {
            string += "Caliber Size: 7mm";
        }
        return string;
    }
}
