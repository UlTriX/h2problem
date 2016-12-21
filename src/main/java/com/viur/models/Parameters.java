package com.viur.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ultrix on 03/11/2016.
 */
public class Parameters {

    private Map<String, String> parameters = new HashMap<>();

    public Parameters(ArrayList<String> parameters) {
        for (String param : parameters) {
            String[] splitted = param.split("=");
            this.parameters.put(splitted[0].toLowerCase(), splitted[1]);
        }
    }

    public String getParam(String key) {
        return parameters.get(key.toLowerCase());
    }

}
