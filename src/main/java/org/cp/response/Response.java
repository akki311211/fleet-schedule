package org.cp.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Response {

    private int responseCode = 200;
    private String responseMsg = "Success";
    private List<ChargedTruckResponse> chargedTruckResponse = new ArrayList<>();

}
