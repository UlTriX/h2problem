package com.viur;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.viur.models.Column;
import com.viur.models.DataType;
import com.viur.models.Table;
import org.h2.index.Cursor;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.value.Value;

import java.util.List;
import java.util.Map;

/**
 * Created by ultrix on 03/11/2016.
 */
public class RestCursor implements Cursor {

    private static Configuration conf = Configuration
            .builder()
            .mappingProvider(new JacksonMappingProvider())
            .build();

    private RestTable restTable;
    private List<Map> data;
    private Row current;
    private int position = -1;

    public RestCursor(RestTable restTable, List<Map> data) {
        this.restTable = restTable;
        this.data = data;
    }

    @Override
    public Row get() {
        return current;
    }

    @Override
    public SearchRow getSearchRow() {
        return current;
    }

    @Override
    public boolean next() {

        position++;

        if (position >= data.size()) {
            current = null;
            return false;
        }

        Map row = data.get(position);
        Table table = restTable.getTable();

        current = restTable.getTemplateRow();
        for (int i = 0; i < current.getColumnCount(); i++) {
            Column column = table.getColumn(i);
            Object v = JsonPath.using(conf).parse(row).read(column.jsonPath, Object.class);
            Value value = DataType.toValue(v, column.dataType.getType());
            current.setValue(i, value);
        }
        return true;
    }

    @Override
    public boolean previous() {
        throw DbException.throwInternalError();
    }
}
