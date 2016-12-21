package com.viur;

import com.viur.models.Column;
import com.viur.models.DataType;
import com.viur.models.Parameters;
import org.h2.command.ddl.CreateTableData;
import org.h2.table.Table;

import java.util.ArrayList;

/**
 * Created by ultrix on 21/12/2016.
 */
public class RestEngine implements org.h2.api.TableEngine {

    @Override
    public Table createTable(CreateTableData createTableData) {

        com.viur.models.Table table = new com.viur.models.Table();
        table.name = createTableData.tableName;
        table.jsonPath = "$.*";

        Column column = new Column();
        column.name = "Date";
        column.dataType = DataType.DATE;
        column.jsonPath = "Date";

        table.columns.add(column);

        createTableData.columns = table.getColumns();

        Parameters parameters = new Parameters(new ArrayList<>());

        return new RestTable(table, createTableData, "com.viur.DummyRequest", parameters);
    }

}
