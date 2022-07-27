package ir.ac.kntu.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ir.ac.kntu.util.RandomHelper;

import java.util.Scanner;

public class Director {

    private List<Soldier> groupA;
    private List<Soldier> groupB;
    private int arenaSize;
    private Scanner sc;

    public List<Soldier> getGroupA() {
        return groupA;
    }

    public List<Soldier> getGroupB() {
        return groupB;
    }

    private GraphicsEngine graphicsEngine;

    private Soldier currentASoldier;
    private Soldier currentBSoldier;

    private VictoryState victoryState;

    public Director(GraphicsEngine graphicsEngine) {
        //TODO: Intialize soldiers
        groupA = new ArrayList<>();
        groupB = new ArrayList<>();
        sc = new Scanner(System.in);
        arenaSize = sc.nextInt();

        System.out.println("group A:");
        for (int i = 0; i < arenaSize; i++) {
            int health = RandomHelper.nextInt(81) + 20;
            Soldier a = new Soldier(health, i + 1);
            groupA.add(a);
            System.out.println(a);
        }
        System.out.println("\ngroup B:");
        for (int i = 0; i < arenaSize; i++) {
            int health = RandomHelper.nextInt(100) + 1;
            Soldier a = new Soldier(health, i + 1);
            groupB.add(a);
            System.out.println(a);
        }

        this.graphicsEngine = graphicsEngine;

        victoryState = VictoryState.NOT_FINISHED;
    }

    public void startGameLoop() {
        //TODO: Add Game Logic Loop here - Graphics also go here
        int turn = 1;
        while (victoryState == VictoryState.NOT_FINISHED) {
            System.out.println("       $$ Arena $$  \n");
            System.out.println("TURN " + turn + ":");
            currentASoldier = selectSoldier((ArrayList<Soldier>) groupA);
            if (currentASoldier == null) {
                victoryState = VictoryState.WIN_B;
                System.out.println("group B win");
                break;
            }
            currentBSoldier = selectSoldier((ArrayList<Soldier>) groupB);
            if (currentBSoldier == null) {
                victoryState = VictoryState.WIN_A;
                System.out.println("group A win");
                break;
            }
            if (currentASoldier != null) {
                System.out.println("soldier A --> " + currentASoldier);
            }
            if (currentBSoldier != null) {
                System.out.println("soldier B --> " + currentBSoldier);
            }

            int shootingPriority = RandomHelper.nextInt(2);
            if (shootingPriority == 0) {
                shoot('A', 'B', currentASoldier, currentBSoldier);
                if (!currentBSoldier.isDead()) {
                    shoot('B', 'A', currentBSoldier, currentASoldier);
                }
            } else {
                shoot('B', 'A', currentBSoldier, currentASoldier);
                if (!currentASoldier.isDead()) {
                    shoot('A', 'B', currentASoldier, currentBSoldier);
                }
            }
            turn++;
            promptEnterKey();
        }
    }

    private Soldier selectSoldier(ArrayList<Soldier> group) {
        Soldier selectedSoldier;
        ArrayList<Soldier> livingSoldiers = new ArrayList<>();
        for (Soldier s : group) {
            if (!s.isDead()) {
                livingSoldiers.add(s);
            }
        }
        if (livingSoldiers.size() == 0) {
            return null;
        }
        Collections.shuffle(livingSoldiers);
        return livingSoldiers.get(0);
    }

    private boolean isHit(Soldier a, int accuracyRoll) {
        if (accuracyRoll <= a.getGun().getAccuracy()) {
            return true;
        }
        return false;
    }

    private void shoot(char at, char def, Soldier a, Soldier b) {
        int accuracyRoll = RandomHelper.nextInt(101);
        System.out.print("Soldier " + at + " Attacks @ AccuracyRoll: " + accuracyRoll);
        if (isHit(a, accuracyRoll)) {
            System.out.print(" @ Attack Hits @ ");
            b.setHealth(b.getHealth() - a.getDamage());
            if (b.isDead()) {
                System.out.println("Soldier " + def + " died");
            } else {
                System.out.println("Soldier " + def + " takes " + a.getDamage() + " DMG");
            }
        } else {
            System.out.println(" @ Attack missed ");
        }
    }

    public static void promptEnterKey() {
        System.out.print("Press \"ENTER\" to continue...");
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum VictoryState {WIN_A, WIN_B, NOT_FINISHED}
}
