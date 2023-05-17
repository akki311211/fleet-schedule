package org.cp.service.impl;

import org.cp.model.Charger;
import org.cp.model.Truck;
import org.cp.response.Response;
import org.cp.service.FleetScheduleService;
import org.cp.strategy.ChargingStrategy;

import java.util.List;

/**
 * FleetScheduleService implementation class
 */
public class FleetScheduleServiceImpl implements FleetScheduleService {

    private ChargingStrategy chargingStrategy;

    public FleetScheduleServiceImpl(ChargingStrategy chargingStrategy){
        this.chargingStrategy = chargingStrategy;
    }

    /**
     * Method to get truck charging schedule
     * @param trucks
     * @param chargers
     * @return response of fleet scheduling
     */
    @Override
    public Response getFleetChargingSchedule(List<Truck> trucks, List<Charger> chargers) {
        return chargingStrategy.getFleetChargingSchedule(trucks, chargers);
    }
}
