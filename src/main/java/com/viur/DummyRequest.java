package com.viur;

import com.viur.models.Parameters;
import org.h2.engine.Session;
import org.h2.result.SearchRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ultrix on 21/12/2016.
 */
public class DummyRequest extends Request {

    private RestTable restTable;

    public DummyRequest() {
    }

    public void setRestTable(RestTable restTable) {
        this.restTable = restTable;
    }

    public RestCursor request(Session session, SearchRow first, SearchRow last) {

        List<Map> data = new ArrayList<>();
        Parameters parameters = restTable.getParameters();

        Map<String, String> row = new HashMap<>();
        row.put("Date", "2015-10-05");
        data.add(row);

        row = new HashMap<>();
        row.put("Date", "2014-04-05");
        data.add(row);

        row = new HashMap<>();
        row.put("Date", "2014-06-05");
        data.add(row);

        return new RestCursor(restTable, data);
    }
}
