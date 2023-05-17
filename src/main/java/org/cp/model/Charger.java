package org.cp.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Charger {

    @NonNull
    private int id;
    @NonNull
    private double chargeRate;
    @NonNull
    private double totalRemainingTime;

    public boolean canChargeTruck(Truck truck) {
        double chargingTime = truck.getRemainingCharge() / chargeRate;
        return chargingTime <= totalRemainingTime;
    }

    public void chargeTruck(Truck truck) {
        double chargingTime = truck.getRemainingCharge() / chargeRate;
        totalRemainingTime -= chargingTime;
        truck.setCurrentChargeLevel(truck.getBatteryCapacity());
    }

}
