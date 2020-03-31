package com.example.classroomtracking;


public class PositionCalculator implements StorageListener {
    public PositionCalculator(DataStorage aStorage, int RSSI1, int RSSI2) {
        storage = aStorage;
        this.RSSI1 = RSSI1;
        this.RSSI2 = RSSI2;
    }

    public void onSignalDataAdded() {
        //Get the last two signal strength data
        int indexOfLast0 = storage.getSignalData(0).size() - 1;
        int theStrength0 = storage.getSignalData(0).get(indexOfLast0);

        int indexOfLast1 = storage.getSignalData(1).size() - 1;
        int theStrength1 = storage.getSignalData(1).get(indexOfLast1);

        //Calculate and store the coordinate
        storage.store(calculateCoordinate(theStrength0, theStrength1));
        if (listener != null) listener.onPositionDataAdded();
    }

    public void onPositionDataAdded() {
    }

    //Calculate the coordinate on a 100*100 mesh, assuming that the beacons are at (0, 0) and (100, 0)
    //10 unit on the mesh is 1 meter (10m*10m)
    public Coordinate calculateCoordinate(int aStrength1, int aStrength2) {
        //Calculate the distances from signal strength and RSSI, in decimeter
        //The formula is 10^((strength-RSSI)/(10*N)) where N depends on the environment, ranging from 2-4.
        double distance1 = (Math.pow(10.0, ((double) (RSSI1 - aStrength1) / 20.0)) * 10.0);
        double distance2 = (Math.pow(10.0, ((double) (RSSI2 - aStrength2) / 20.0)) * 10.0);

        //Equation of x is obtained by solving x^2+y^2=d1^2 and (100-x)^2 + y^2 = d2^2
        double x = (Math.pow(distance1, 2) - Math.pow(distance2, 2)) / 200.0 + 50.0;
        double y = Math.sqrt(Math.pow(distance1, 2) - Math.pow(x, 2));

        if (x > 100) x = 100;
        else if (x < 0) x = 0;
        if (y > 100) y = 100;

        return new Coordinate((int) x, (int) y);

    }

    public void registerListener(StorageListener aListener){
        listener = aListener;
    }

    private DataStorage storage;
    private int RSSI1;
    private int RSSI2;
    private StorageListener listener;
}
