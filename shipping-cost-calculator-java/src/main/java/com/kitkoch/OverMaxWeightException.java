package com.kitkoch;

public class OverMaxWeightException extends Exception {
    private final double weight;

    public OverMaxWeightException(double weight) {
        super("Weight " + weight + " exceeds the 20lb limit");
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}
