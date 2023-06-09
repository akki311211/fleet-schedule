package org.cp.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cp.model.Charger;
import org.cp.model.Truck;
import org.cp.response.ChargedTruckResponse;
import org.cp.response.Response;

/**
 * MaxOptimalStrategy - Optimal scheduling strategy
 */
public class MaxOptimalStrategy implements ChargingStrategy {

    /**
     *
     * @param trucks
     * @param chargers
     * @return charging schedule of trucks with optimal strategy
     */

    @Override
    public Response getFleetChargingSchedule(List<Truck> trucks, List<Charger> chargers) {
        if (trucks.isEmpty()) {
            throw new IllegalArgumentException("Truck list cannot be empty");
        }
        if(chargers.isEmpty()){
            throw new IllegalArgumentException("Charger list cannot be empty");
        }
        Response response = new Response();
        trucks.sort(Comparator.comparingDouble(Truck::getRemainingCharge));

        /* Truck 1 : 50 , Truck 2 : 100
           charger 1 - 100 , charger 2 - 50
           charger 2 - 50 charger 1: 100




          Time = 6 hour  : 12 am : 10 am
           Cheap window = 2-4 am
           Normal - 12-2 hours , 4-6 hours
          Truck 1 : 20 , Truck 2 : 40
          Charger 1 : 5 , charger 2 : 10
          Charger 1 : 5 * 2 = 10 , 5

          Charger 1  - Priority( 1= 1, )

          Truck 1 : Charger 1 (2 hour) = 10 + Charger 2(1 hr) = 10
          Truck 2 : Charger 2 (2 hour) = 20 + Charger 1( 1hour ) = 5



          Charger 1 : 10  Charger 2 : 15
          Truck 1 : 20 , Truck 2 : 15

          2-4 = Truck - 2  Charger 2 = 1 hour
                Truck - 1 Charger 1 =  20



         */
        chargers.sort(Comparator.comparingDouble(Charger::getChargeRate).reversed());
        Map<Integer, List<Truck>> schedule = new HashMap<>();
        for (Charger charger : chargers) {
            schedule.put(charger.getId(), new ArrayList<>());
        }

        for (Truck truck : trucks) {
            for (Charger charger : chargers) {
                double diff  = truck.getRemainingCharge() - charger.getChargeRate();
                //Sorting parameter for charger
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
