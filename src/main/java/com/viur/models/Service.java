package com.viur.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ultrix on 21/10/2016.
 */
public class Service {

    @JsonProperty("request")
    private String requestClass;

    private List<Table> tables = new ArrayList<>();

    private Map<String, Table> tablesMap = new HashMap<>();

    public Service() {

    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {

        for (Table table : tables) {
            tablesMap.put(table.name.toUpperCase(), table);
        }

        this.tables = tables;
    }

    public String getRequestClass() {
        return requestClass;
    }

    public void setRequestClass(String requestClass) {
        this.requestClass = requestClass;
    }

    public Table getTable(String name) {
        return tablesMap.get(name);
    }
}
