package com.viur;

import org.h2.engine.Session;
import org.h2.result.SearchRow;

/**
 * Created by ultrix on 03/11/2016.
 */
public abstract class Request {

    public abstract void setRestTable(RestTable table);

    public abstract RestCursor request(Session session, SearchRow first, SearchRow last);
}
