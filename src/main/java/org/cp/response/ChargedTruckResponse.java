package org.cp.response;

import lombok.Data;
import org.cp.model.Charger;
import org.cp.model.Truck;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChargedTruckResponse {
    private int chargerId;
    private Charger charger;
    private List<Truck> trucks = new ArrayList<>();

}
