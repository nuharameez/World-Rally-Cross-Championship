package com.example.progcw;

import java.util.Collections;

import static com.example.progcw.DriverList.*;

public class RandomRace {

    public RandomRace() {

        Collections.shuffle(allDrivers);
        int rank = 1;
        for (int i = 0; i < allDrivers.size(); i++) {
            Object elem = allDrivers.get(i).get(4);
            if (elem instanceof String) {
                if (i == 0) {
                    int current = Integer.parseInt((String) allDrivers.get(i).get(4));
                    int newVal = current + 10;
                    allDrivers.get(i).set(4, Integer.toString(newVal));
                } else if (i == 1) {
                    int current = Integer.parseInt((String) allDrivers.get(i).get(4));
                    int newVal = current + 7;
                    allDrivers.get(i).set(4, Integer.toString(newVal));
                } else if (i == 2) {
                    int current = Integer.parseInt((String) allDrivers.get(i).get(4));
                    int newVal = current + 7;
                    allDrivers.get(i).set(4, Integer.toString(newVal));
                }
                System.out.println("Position: " + rank + " " + allDrivers.get(i).get(0) + "Points: " + allDrivers.get(i).get(4));
                rank += 1;
            }
            else{
                if (i == 0) {
                    int current = (int) allDrivers.get(i).get(4);
                    int newVal = current + 10;
                    allDrivers.get(i).set(4, newVal);
                } else if (i == 1) {
                    int current = (int) allDrivers.get(i).get(4);
                    int newVal = current + 7;
                    allDrivers.get(i).set(4, newVal);
                } else if (i == 2) {
                    int current = (int) allDrivers.get(i).get(4);
                    int newVal = current + 7;
                    allDrivers.get(i).set(4, newVal);
                }

                System.out.println("Position: " + rank + " " + allDrivers.get(i).get(0) + "Points: " + allDrivers.get(i).get(4));
                rank += 1;
            }
        }
    }
}
