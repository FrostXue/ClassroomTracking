package com.example.classroomtracking;

public interface StorageListener { //Listen to changes in the DataStorage
    void onSignalDataAdded();
    void onPositionDataAdded();
}
