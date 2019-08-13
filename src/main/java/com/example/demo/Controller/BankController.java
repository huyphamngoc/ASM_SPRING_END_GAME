package com.example.demo.Controller;

import com.example.demo.rest.RESTResponse;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "api/bank")
public class BankController {
    public double EIM;
    @RequestMapping(method = RequestMethod.POST, value = "/operation1")
    public ResponseEntity<Object> create(@RequestBody Map<String, Object> payload)
            throws JSONException {
        JSONObject json = new JSONObject(payload.toString());
        double  l = json.getDouble("l");
        double r = (json.getDouble("r")/12)/100;
        double n = json.getDouble("n");

         EIM = l*((r*(Math.pow((1+r), n)))/(Math.pow((1+r),n) - 1));

        System.out.println(EIM);

        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Success!")
                .setData(EIM)
                .build(), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "operation2")
    public ResponseEntity<Object> operation2(@RequestBody Map<String, Object> payload) throws JSONException{
        JSONObject json = new JSONObject(payload.toString());
        double r = (json.getDouble("r")/12)/100;
        double n = json.getDouble("n");

        double x = (EIM * r) + n;
        System.out.println(x);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Success!")
                .setData(x)
                .build(), HttpStatus.OK);
    }
}
