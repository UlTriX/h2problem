package com.viur.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.h2.message.DbException;
import org.h2.value.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Created by ultrix on 21/10/2016.
 */
public enum DataType {

    @JsonProperty("string")
    STRING(Value.STRING),
    @JsonProperty("long_string")
    LONG_STRING(Value.CLOB),
    @JsonProperty("boolean")
    BOOLEAN(Value.BOOLEAN),
    @JsonProperty("integer")
    INTEGER(Value.INT),
    @JsonProperty("number")
    NUMBER(Value.DOUBLE),

    // Must be in YYYY-MM-DD
    @JsonProperty("date")
    DATE(Value.DATE),
    // Must be in ISO 8601
    @JsonProperty("date_time")
    DATE_TIME(Value.TIMESTAMP);

    private int type;

    DataType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static Value toValue(Object value, int type) {

        //TODO support to get dates and date times in custom formats

        Value v;
        switch (type) {
            case Value.STRING:
                v = (value == null) ? ValueNull.INSTANCE : ValueString.get((String) value);
                break;
            case Value.BOOLEAN:
                v = (value == null) ? ValueNull.INSTANCE : ValueBoolean.get((Boolean) value);
                break;
            case Value.INT:
                v = (value == null) ? ValueNull.INSTANCE : ValueInt.get((Integer) value);
                break;
            case Value.DOUBLE:
                v = (value == null) ? ValueNull.INSTANCE : ValueDouble.get((value instanceof Double) ? (Double) value : ((Integer) value).doubleValue());
                break;
            case Value.DATE:
                v = (value == null) ? ValueNull.INSTANCE : ValueDate.fromMillis((new DateTime(value, DateTimeZone.UTC)).getMillis());
                break;
            case Value.TIMESTAMP:
                v = (value == null) ? ValueNull.INSTANCE : ValueTimestamp.fromMillis((new DateTime(value, DateTimeZone.UTC)).getMillis());
                break;
            default:
                throw DbException.throwInternalError("type=" + type);
        }

        return v;

    }
}
