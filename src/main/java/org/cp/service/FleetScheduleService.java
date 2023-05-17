package org.cp.service;

import org.cp.model.Charger;
import org.cp.model.Truck;
import org.cp.response.Response;

import java.util.List;

/**
 * FleetScheduleService - Interface for fleet scheduling
 */
public interface FleetScheduleService {
    Response getFleetChargingSchedule(List<Truck> trucks, List<Charger> chargers);
}
