package com.example.classroomtracking;

public class Coordinate {
    public Coordinate(int aRow, int aColumn){
        row=aRow;
        col=aColumn;
    }
    public Coordinate(Coordinate aCopy){
        row=aCopy.row;
        col=aCopy.col;
    }
    public int row;
    public int col;

    public boolean equals(Coordinate anOther){
        return row == anOther.row && col == anOther.col;
    }
}
