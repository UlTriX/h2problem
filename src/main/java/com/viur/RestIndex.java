package com.viur;

import org.h2.engine.Session;
import org.h2.index.BaseIndex;
import org.h2.index.Cursor;
import org.h2.index.IndexType;
import org.h2.message.DbException;
import org.h2.result.Row;
import org.h2.result.SearchRow;
import org.h2.result.SortOrder;
import org.h2.table.Column;
import org.h2.table.IndexColumn;
import org.h2.table.TableFilter;

import java.util.HashSet;

/**
 * Created by ultrix on 03/11/2016.
 */
public class RestIndex extends BaseIndex {

    private RestTable table;
    private long rowCount;

    public RestIndex(RestTable table, int id, IndexColumn[] columns, IndexType indexType) {
        initBaseIndex(table, id, table.getName() + "_DATA", columns, indexType);
        this.table = table;
    }

    @Override
    public void checkRename() {

    }

    @Override
    public void close(Session session) {

    }

    @Override
    public void add(Session session, Row row) {

    }

    @Override
    public void remove(Session session, Row row) {

    }

    @Override
    public Cursor find(Session session, SearchRow first, SearchRow last) {
        //session.getDatabase().getTableOrViewByName("TABLES").get(0).getScanIndex(session).find(session, null, null);
        return table.getRequest().request(session, first, last);
    }

    @Override
    public double getCost(Session session, int[] ints, TableFilter[] tableFilters, int i, SortOrder sortOrder, HashSet<Column> hashSet) {
        return 0;
    }

    @Override
    public void remove(Session session) {
        throw DbException.getUnsupportedException("REST");
    }

    @Override
    public void truncate(Session session) {

    }

    @Override
    public boolean canGetFirstOrLast() {
        return false;
    }

    @Override
    public Cursor findFirstOrLast(Session session, boolean b) {
        throw DbException.getUnsupportedException("REST");
    }

    @Override
    public boolean needRebuild() {
        return false;
    }

    @Override
    public long getRowCount(Session session) {
        return rowCount;
    }

    @Override
    public long getRowCountApproximation() {
        return rowCount;
    }

    @Override
    public long getDiskSpaceUsed() {
        return 0;
    }
}
