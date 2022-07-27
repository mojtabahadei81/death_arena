package ir.ac.kntu.logic;

import ir.ac.kntu.AssaultRifle;
import ir.ac.kntu.Gun;
import ir.ac.kntu.SniperRifle;
import ir.ac.kntu.util.RandomHelper;

public class Soldier {

    private int health;

    private int damage;

    private Gun gun;

    private int soldierId;

    private boolean isDead = false;

    public Soldier(int health, int soldierId) {
        this.health = health;
        chooseAGun();
        damage = gun.getDamage();
        this.soldierId = soldierId;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public Gun getGun() {
        Gun copyGun = new Gun(gun);
        return copyGun;
    }

    public int getHealth() {
        return this.health;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setHealth(int health) {
        this.health = health;
        if (health < 0) {
            this.health = 0;
            this.setDead(true);
        }
    }

    private void chooseAGun() {
        int a = RandomHelper.nextInt();
        if (a % 2 == 0) {
            gun = new AssaultRifle();
        } else {
            gun = new SniperRifle();
        }
    }

    @Override
    public String toString() {
        String string = "Id: " + soldierId + "| ";
        string += "health: " + health + "| ";
        string += gun.toString();
        return string;
    }
}
