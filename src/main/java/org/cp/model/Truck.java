package org.cp.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Truck {

    @NonNull
    private int id;
    @NonNull
    private double batteryCapacity;
    @NonNull
    private double currentChargeLevel;

    public double getRemainingCharge() {
        return batteryCapacity - currentChargeLevel;
    }

}
