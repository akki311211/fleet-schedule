package org.cp.strategy;

import org.cp.model.Charger;
import org.cp.model.Truck;
import org.cp.response.Response;

import java.util.List;

/**
 * ChargingStrategy - Interface for charging strategy
 */
public interface ChargingStrategy {

    Response getFleetChargingSchedule(List<Truck> trucks, List<Charger> chargers);

}
