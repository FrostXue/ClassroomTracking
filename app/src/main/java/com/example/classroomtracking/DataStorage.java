package com.example.classroomtracking;

import java.util.ArrayList;

public class DataStorage {
    public DataStorage(int aNumBeacons) {
        numBeacons = aNumBeacons;
        signals = new ArrayList[numBeacons];
        for (int i = 0; i < numBeacons; i++) {
            signals[i] = new ArrayList<>();
        }
        coordinates = new ArrayList<>();
    }

    public DataStorage(DataStorage aCopy) {
        numBeacons = aCopy.numBeacons;
        signals = new ArrayList[numBeacons];
        for (int i = 0; i < numBeacons; i++) {
            signals[i] = new ArrayList<>(aCopy.signals[i]);
        }
        coordinates = new ArrayList<>(coordinates);
    }

    public boolean store(int aSignalStrength, int aBeaconIndex) {
        if (aBeaconIndex < numBeacons && aBeaconIndex >= 0){
            signals[aBeaconIndex].add(aSignalStrength);
            return true;
        }
        else return false;
    }

    public boolean store(Coordinate aCoordinate) {
        coordinates.add(aCoordinate);
        return true;
    }

    public ArrayList<Integer> getSignalData(int aBeaconIndex) throws IndexOutOfBoundsException{
        if (aBeaconIndex < numBeacons && aBeaconIndex >= 0){
            return signals[aBeaconIndex];
        }
        else throw new IndexOutOfBoundsException("Beacon " + aBeaconIndex + " does not exist!");
    }

    public ArrayList<Coordinate> getCoordinates(){
        return coordinates;
    }

    public int getNumBeacons(){
        return numBeacons;
    }
    private ArrayList<Integer>[] signals;
    private ArrayList<Coordinate> coordinates;
    private int numBeacons;
    private StorageListener listener;
}
