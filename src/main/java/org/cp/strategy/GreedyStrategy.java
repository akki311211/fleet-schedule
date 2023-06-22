package org.cp.strategy;

import org.cp.model.Charger;
import org.cp.model.Truck;
import org.cp.response.ChargedTruckResponse;
import org.cp.response.Response;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GreedyStrategy implements ChargingStrategy{
    @Override
    public Response getFleetChargingSchedule(List<Truck> trucks, List<Charger> chargers) {
        if (trucks.isEmpty()) {
            throw new IllegalArgumentException("Truck list cannot be empty");
        }
        if(chargers.isEmpty()){
            throw new IllegalArgumentException("Charger list cannot be empty");
        }
        Response response = new Response();
        trucks.sort(Comparator.comparingDouble(Truck::getRemainingCharge).reversed());
        chargers.sort(Comparator.comparingDouble(Charger::getChargeRate).reversed());
        Map<Integer, List<Truck>> schedule = new HashMap<>();
        for (Charger charger : chargers) {
            schedule.put(charger.getId(), new ArrayList<>());
        }

        for (Truck truck : trucks) {

            for (Charger charger : chargers) {
                if (charger.canChargeTruck(truck)) {
                    schedule.get(charger.getId()).add(truck);
                    charger.chargeTruck(truck);
                    break;
                }
            }
        }

        for (Charger charger :  chargers) {
            ChargedTruckResponse ctResponse = new ChargedTruckResponse();
            ctResponse.setChargerId(charger.getId());
            ctResponse.setCharger(charger);
            ctResponse.setTrucks(schedule.get(charger.getId()));
            response.getChargedTruckResponse().add(ctResponse);
        }

        return response;
    }
}
