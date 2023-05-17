package org.cp.service.impl;

import org.cp.model.Charger;
import org.cp.model.Truck;
import org.cp.response.Response;
import org.cp.service.FleetScheduleService;
import org.cp.strategy.ChargingStrategy;
import org.cp.strategy.FcfsStrategy;
import org.cp.strategy.MaxOptimalStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Test class for testing fleet scheduling
 */
public class FleetServiceImplTest {

    private FleetScheduleService fleetScheduleService;

    @Test
    public void testGetFleetChargingScheduleEmptyTrucks() {

        double time = 4;

        ChargingStrategy strategy = new MaxOptimalStrategy();
        fleetScheduleService = new FleetScheduleServiceImpl(strategy);

        List<Truck> trucks = new ArrayList<>();

        List<Charger> chargers = new ArrayList<>();
        chargers.add(new Charger(1, 15, time));
        chargers.add(new Charger(2, 25, time));


        Assertions.assertThrows(IllegalArgumentException.class,
                () -> fleetScheduleService.getFleetChargingSchedule(trucks, chargers));

    }

    @Test
    public void testGetFleetChargingScheduleEmptyCharger() {
        double time = 4;

        ChargingStrategy strategy = new MaxOptimalStrategy();
        fleetScheduleService = new FleetScheduleServiceImpl(strategy);

        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck(1, 100, 50));
        trucks.add(new Truck(2, 120, 60));
        trucks.add(new Truck(3, 150, 100));

        List<Charger> chargers = new ArrayList<>();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> fleetScheduleService.getFleetChargingSchedule(trucks, chargers));
    }

    @Test
    public void testGetFleetChargingScheduleOptimal() {
        double time = 4;

        ChargingStrategy strategy = new MaxOptimalStrategy();
        fleetScheduleService = new FleetScheduleServiceImpl(strategy);

        List<Truck> trucks = new ArrayList<>();
        trucks = new ArrayList<>();
        trucks.add(new Truck(1, 100, 50));
        trucks.add(new Truck(2, 120, 60));
        trucks.add(new Truck(3, 150, 100));

        List<Charger> chargers = new ArrayList<>();
        chargers.add(new Charger(1, 15, time));
        chargers.add(new Charger(2, 25, time));


        Response response = fleetScheduleService.getFleetChargingSchedule(trucks, chargers);
        Assertions.assertEquals(2, response.getChargedTruckResponse().stream().filter(charger -> charger.getChargerId() == 2).collect(Collectors.toList()).get(0).getTrucks().size());
        Assertions.assertEquals(1, response.getChargedTruckResponse().stream().filter(charger -> charger.getChargerId() == 1).collect(Collectors.toList()).get(0).getTrucks().size());

    }

    @Test
    public void testGetFleetChargingScheduleOptimalNotEnoughTime() {
        double time = 1;

        ChargingStrategy strategy = new MaxOptimalStrategy();
        fleetScheduleService = new FleetScheduleServiceImpl(strategy);

        List<Truck> trucks = new ArrayList<>();
        trucks = new ArrayList<>();
        trucks.add(new Truck(1, 100, 50));
        trucks.add(new Truck(2, 120, 60));
        trucks.add(new Truck(3, 150, 100));

        List<Charger> chargers = new ArrayList<>();
        chargers.add(new Charger(1, 15, time));
        chargers.add(new Charger(2, 25, time));


        Response response = fleetScheduleService.getFleetChargingSchedule(trucks, chargers);
        Assertions.assertEquals(0, response.getChargedTruckResponse().stream().filter(charger -> charger.getChargerId() == 2).collect(Collectors.toList()).get(0).getTrucks().size());
        Assertions.assertEquals(0, response.getChargedTruckResponse().stream().filter(charger -> charger.getChargerId() == 1).collect(Collectors.toList()).get(0).getTrucks().size());

    }

    @Test
    public void testGetFleetChargingScheduleOptimalFullCharge() {
        double time = 4;

        ChargingStrategy strategy = new MaxOptimalStrategy();
        fleetScheduleService = new FleetScheduleServiceImpl(strategy);

        List<Truck> trucks = new ArrayList<>();
        trucks = new ArrayList<>();
        trucks.add(new Truck(1, 100, 50));
        trucks.add(new Truck(2, 120, 60));
        trucks.add(new Truck(3, 150, 100));

        List<Charger> chargers = new ArrayList<>();
        chargers.add(new Charger(1, 15, time));
        chargers.add(new Charger(2, 25, time));


        Response response = fleetScheduleService.getFleetChargingSchedule(trucks, chargers);
        Assertions.assertEquals(0, response.getChargedTruckResponse().stream().filter(charger -> charger.getChargerId() == 2).collect(Collectors.toList()).get(0).getTrucks().get(0).getRemainingCharge());
        Assertions.assertEquals(0, response.getChargedTruckResponse().stream().filter(charger -> charger.getChargerId() == 1).collect(Collectors.toList()).get(0).getTrucks().get(0).getRemainingCharge());

    }

    @Test
    public void testGetFleetChargingScheduleFcfs() {
        double time = 4;

        ChargingStrategy strategy = new FcfsStrategy();
        fleetScheduleService = new FleetScheduleServiceImpl(strategy);

        List<Truck> trucks = new ArrayList<>();
        trucks = new ArrayList<>();
        trucks.add(new Truck(1, 100, 50));
        trucks.add(new Truck(2, 120, 60));
        trucks.add(new Truck(3, 150, 100));

        List<Charger> chargers = new ArrayList<>();
        chargers.add(new Charger(1, 15, time));
        chargers.add(new Charger(2, 25, time));


        Response response = fleetScheduleService.getFleetChargingSchedule(trucks, chargers);
        Assertions.assertEquals(1, response.getChargedTruckResponse().stream().filter(charger -> charger.getChargerId() == 2).collect(Collectors.toList()).get(0).getTrucks().size());
        Assertions.assertEquals(1, response.getChargedTruckResponse().stream().filter(charger -> charger.getChargerId() == 1).collect(Collectors.toList()).get(0).getTrucks().size());

    }

    @Test
    public void testGetFleetChargingScheduleFcfsNotEnoughTime() {
        double time = 1;

        ChargingStrategy strategy = new FcfsStrategy();
        fleetScheduleService = new FleetScheduleServiceImpl(strategy);

        List<Truck> trucks = new ArrayList<>();
        trucks = new ArrayList<>();
        trucks.add(new Truck(1, 100, 50));
        trucks.add(new Truck(2, 120, 60));
        trucks.add(new Truck(3, 150, 100));

        List<Charger> chargers = new ArrayList<>();
        chargers.add(new Charger(1, 15, time));
        chargers.add(new Charger(2, 25, time));


        Response response = fleetScheduleService.getFleetChargingSchedule(trucks, chargers);
        Assertions.assertEquals(0, response.getChargedTruckResponse().stream().filter(charger -> charger.getChargerId() == 2).collect(Collectors.toList()).get(0).getTrucks().size());
        Assertions.assertEquals(0, response.getChargedTruckResponse().stream().filter(charger -> charger.getChargerId() == 1).collect(Collectors.toList()).get(0).getTrucks().size());

    }

}
