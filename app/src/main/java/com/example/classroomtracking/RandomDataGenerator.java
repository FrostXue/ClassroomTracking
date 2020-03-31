package com.example.classroomtracking;

import java.util.Random;

public class RandomDataGenerator {
    public RandomDataGenerator(){
        rand = new Random();
        currentData = (min + max) / 2;
    }

    public int getData(){
        int theOffset = rand.nextInt(2 * maxStep + 1) - maxStep;
        currentData += theOffset;

        if (currentData > max) currentData = max;
        else if (currentData < min) currentData = min;

        return currentData;
    }

    private Random rand;
    private int currentData;
    private static int min = -65;
    private static int max = -45;
    private static int maxStep = 4;
}
