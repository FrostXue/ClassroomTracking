package com.example.classroomtracking;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SignalTracker { //Retrieves signal strength from beacon and store at a constant interval. Callback afterwards with StorageListener.
    public SignalTracker(int aTrackRate, DataStorage aStorage, ArrayList<MockBeacon> aBeacons) {
        trackRate = aTrackRate;
        storage = aStorage;
        timer = new Timer();
        beacons = new ArrayList<>();
        beacons.addAll(aBeacons);
    }

    class SignalRetriever extends TimerTask {
        @Override
        public void run() {
            for (int i = 0; i < beacons.size(); i++) {
                storage.store(beacons.get(i).getStrength(), i);
            }
            if (listener != null) listener.onSignalDataAdded();
        }
    }

    public void startTracking() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new SignalRetriever(), 0, 1000 * trackRate);
    }

    public void pauseTracking() {
        timer.cancel();
    }

    public void endTracking() {
        timer.purge();
    }

    public void registerListener(StorageListener aListener) {
        listener = aListener;
    }


    private Timer timer;
    private DataStorage storage;
    private int trackRate;
    private ArrayList<MockBeacon> beacons;
    private StorageListener listener;
}
