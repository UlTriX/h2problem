package com.viur.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ultrix on 21/10/2016.
 */
public class Column {

    public String name;
    @JsonProperty("data_type")
    public DataType dataType;
    @JsonProperty("json_path")
    public String jsonPath;
    public boolean key;
    public String description;

    public Column() {

    }

    public org.h2.table.Column getColumn() {
        return new org.h2.table.Column(name.toUpperCase(), dataType.getType());
    }

}
