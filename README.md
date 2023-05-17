# fleet-schedule

**Scheduling stategies - FCFS and Max Optimal**

**Run the application - **
1. Go to Main.java
2. Define list of trucks and chargers
3. Define scheduling strategy
4. Bind strategy with the service.
5. Invoke getFleetChargingSchedule method.
6. Print the Response

**Example :**

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



**Steps to run the Junit test cases - **
1. Go to FleetServiceImplTest.java
2. Right click and Run 
3. All test cases will be executed.

**Test Cases covered - **

testGetFleetChargingScheduleEmptyCharger()
testGetFleetChargingScheduleOptimalFullCharge()
testGetFleetChargingScheduleFcfsNotEnoughTime()
testGetFleetChargingScheduleEmptyTrucks()
testGetFleetChargingScheduleFcfs()
testGetFleetChargingScheduleOptimalNotEnoughTime()
testGetFleetChargingScheduleOptimal()

