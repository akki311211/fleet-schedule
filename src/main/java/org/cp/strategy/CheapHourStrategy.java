package org.cp.strategy;

import org.cp.model.Charger;
import org.cp.model.Truck;
import org.cp.response.Response;

import java.util.Comparator;
import java.util.List;

public class CheapHourStrategy implements ChargingStrategy{

    private int availableTime = 8;


    @Override
    public Response getFleetChargingSchedule(List<Truck> trucks, List<Charger> chargers) {
        trucks.sort(Comparator.comparingDouble(Truck::getRemainingCharge).reversed());
        chargers.sort(Comparator.comparingDouble(Charger::getChargeRate).reversed());
        for(int time=0; time< availableTime; time++){
            for(Charger charger : chargers){
                for(int j=0; j< trucks.size(); j++){
                    Truck truck = trucks.get(j);
                    if(truck.getCurrentChargeLevel() == truck.getBatteryCapacity()){
                        continue;
                    }
                    double maxCapacity = charger.getChargeRate();
                    double remainingCharge = truck.getRemainingCharge();
                    if(time >=2 && time <=4){
                        maxCapacity = Math.min(maxCapacity, remainingCharge);
                    }else{
                        maxCapacity = Math.min(maxCapacity, remainingCharge/2);
                    }
                    truck.setCurrentChargeLevel(truck.getCurrentChargeLevel()+maxCapacity);

                    //add to full charge list
                }
            }
        }
        return null;
    }
}
