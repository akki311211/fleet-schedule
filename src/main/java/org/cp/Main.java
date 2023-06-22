package org.cp;

import org.cp.model.Charger;
import org.cp.model.Truck;
import org.cp.response.Response;
import org.cp.service.FleetScheduleService;
import org.cp.service.impl.FleetScheduleServiceImpl;
import org.cp.strategy.ChargingStrategy;
import org.cp.strategy.FcfsStrategy;
import org.cp.strategy.GreedyStrategy;
import org.cp.strategy.MaxOptimalStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main class
 */
public class Main {
  public static void main(String[] args) {

    double time = 4;

    List<Truck> trucks = new ArrayList<>();
    trucks = new ArrayList<>();
    trucks.add(new Truck(1, 100, 50));
    trucks.add(new Truck(2, 120, 60));
    trucks.add(new Truck(3, 150, 100));

    List<Charger> chargers = new ArrayList<>();
    chargers.add(new Charger(1, 15, time));
    chargers.add(new Charger(2, 25, time));




    ChargingStrategy strategy = new FcfsStrategy();
    FleetScheduleService fleetScheduleService = new FleetScheduleServiceImpl(strategy);
    Response response = fleetScheduleService.getFleetChargingSchedule(trucks, chargers);
    System.out.println("\nCharged Trucks by FCFS:");
    printComplete(response);

    trucks = new ArrayList<>();
    trucks.add(new Truck(1, 100, 50));
    trucks.add(new Truck(2, 120, 60));
    trucks.add(new Truck(3, 150, 100));

    chargers = new ArrayList<>();
    chargers.add(new Charger(1, 15, time));
    chargers.add(new Charger(2, 25, time));


    strategy = new MaxOptimalStrategy();
    fleetScheduleService = new FleetScheduleServiceImpl(strategy);
    response = fleetScheduleService.getFleetChargingSchedule(trucks, chargers);
    System.out.println("\n Charged Trucks max Optimally: ");
    printComplete(response);

    //Truck 1 : 50 , Truck 2 : 100
    //charger 1 - 100 , charger 2 - 50
    // charger 2 - 50 charger 1: 100
    time = 1;
    trucks = new ArrayList<>();
    trucks.add(new Truck(1, 100, 50));
    trucks.add(new Truck(2, 100, 0));

    chargers = new ArrayList<>();
    chargers.add(new Charger(1, 120, time));
    chargers.add(new Charger(2, 50, time));

    strategy = new GreedyStrategy();
    fleetScheduleService = new FleetScheduleServiceImpl(strategy);
    response = fleetScheduleService.getFleetChargingSchedule(trucks, chargers);
    System.out.println("\n Charged Trucks max Greedy: ");
    printComplete(response);
  }

  private static void print(Response response){
    response.getChargedTruckResponse().stream().forEach(item -> {
      System.out.println(item.getCharger().getId() + " ==>" + item.getTrucks().stream().map((Truck::getId)).collect(Collectors.toList()));
    });
  }
  private static void printComplete(Response response){
    response.getChargedTruckResponse().stream().forEach(item -> {
      System.out.println(item.getCharger().getId() + " ==>" + item.getTrucks());
    });
  }

}
