package com.example.classroomtracking;


import java.util.Timer;
import java.util.TimerTask;

public class MockBeacon {
    public MockBeacon(int RSSI, int transmissionRate) {
        this.RSSI = RSSI;
        generator = new RandomDataGenerator();
        timer = new Timer();
        this.transmissionRate = transmissionRate;
    }

    class DataRetriever extends TimerTask {
        @Override
        public void run() {
            double distance1;
            double distance2;
            do {
                strength = generator.getData();
                distance1 = (Math.pow(10.0, ((double) (-45 - strength) / 20.0)));
                distance2 = (Math.pow(10.0, ((double) (-45 - otherBeacon.strength) / 20.0)));
            } while (distance1 + distance2 < 10);
        }
    }

    public void startTransmission() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new DataRetriever(), 0, 1000 * transmissionRate);
    }

    public void pauseTransmission() {
        timer.cancel();
    }

    public void endTransmission() {
        timer.purge();
    }

    public int getStrength() {
        return strength;
    }

    public int getRSSI() {
        return RSSI;
    }

    public void setOtherBeacon(MockBeacon otherBeacon) {
        this.otherBeacon = otherBeacon;
    }

    private int RSSI;
    private int strength;
    private RandomDataGenerator generator;
    private Timer timer;
    private int transmissionRate;
    private MockBeacon otherBeacon;
}
