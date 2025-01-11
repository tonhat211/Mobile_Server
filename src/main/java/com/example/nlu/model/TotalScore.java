package com.example.nlu.model;

import jakarta.persistence.*;

public class TotalScore {
    private double avgScore10;
    private double avgScore4;
    private int reachedCredit;

    public TotalScore(double avgScore10, double avgScore4, int reachedCredit) {
        this.avgScore10 = avgScore10;
        this.avgScore4 = avgScore4;
        this.reachedCredit = reachedCredit;
    }

    public double getAvgScore10() {
        return avgScore10;
    }

    public void setAvgScore10(double avgScore10) {
        this.avgScore10 = avgScore10;
    }

    public double getAvgScore4() {
        return avgScore4;
    }

    public void setAvgScore4(double avgScore4) {
        this.avgScore4 = avgScore4;
    }

    public int getReachedCredit() {
        return reachedCredit;
    }

    public void setReachedCredit(int reachedCredit) {
        this.reachedCredit = reachedCredit;
    }
}
