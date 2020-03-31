package com.example.classroomtracking;

import android.widget.ImageView;


import java.util.ArrayList;

public class Presenter implements ViewListener, StorageListener{
    public Presenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        storage = new DataStorage(2);

        beacons = new ArrayList<>();
        beacons.add(new MockBeacon(-45, 1));
        beacons.add(new MockBeacon(-45, 1));
        beacons.get(0).setOtherBeacon(beacons.get(1));
        beacons.get(1).setOtherBeacon((beacons.get(0)));

        calculator = new PositionCalculator(storage, beacons.get(0).getRSSI(), beacons.get(1).getRSSI());
        calculator.registerListener(this);

        tracker = new SignalTracker(1, storage, beacons);
        tracker.registerListener(calculator);


        drawer = new TraceDrawer((ImageView) mainActivity.findViewById(R.id.traceDrawboard),mainActivity);

        isTracking = false;
        isActive = true;
    }

    public void onStartClick() {
        if (!isTracking) {
            for (MockBeacon theBeacon : beacons) {
                theBeacon.startTransmission();
            }
            tracker.startTracking();
            isTracking = true;
        }
    }

    public void onPauseClick() {
        if (isTracking) {
            for (MockBeacon theBeacon : beacons) {
                theBeacon.pauseTransmission();
            }
            tracker.pauseTracking();
            isTracking = false;
        }
    }

    public void onClearClick() {
        for (MockBeacon theBeacon : beacons) {
            theBeacon.endTransmission();
        }
        tracker.endTracking();
        drawer.clear();
        isTracking = false;
    }

    public void onSignalDataAdded() { //No response

    }

    public void onPositionDataAdded() {
        if (isActive){
            int lastCoordinateIndex = storage.getCoordinates().size() - 1;
            drawer.drawNew(storage.getCoordinates().get(lastCoordinateIndex));
        }
    }

    private MainActivity mainActivity;
    private DataStorage storage;
    private SignalTracker tracker;
    private PositionCalculator calculator;
    private ArrayList<MockBeacon> beacons;
    private TraceDrawer drawer;
    private boolean isTracking;
    private boolean isActive; //Should the position be drawn?
}
