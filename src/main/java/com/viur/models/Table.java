package com.viur.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ultrix on 21/10/2016.
 */
public class Table {

    public String name;

    public String description;

    public String url;

    @JsonProperty("json_path")
    public String jsonPath;

    @JsonProperty("count_json_path")
    public String countJsonPath;

    public List<Column> columns = new ArrayList<>();

    public Table() {

    }

    public ArrayList<org.h2.table.Column> getColumns() {
        //TODO improve performance maybe generate the list only one time
        ArrayList<org.h2.table.Column> columns = new ArrayList<>();

        for (Column column : this.columns) {
            columns.add(column.getColumn());
        }

        return columns;
    }

    public Column getColumn(int index) {
        return columns.get(index);
    }
}
