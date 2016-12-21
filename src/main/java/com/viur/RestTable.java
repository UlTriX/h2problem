package com.viur;

import com.viur.models.Parameters;
import com.viur.models.Table;
import org.h2.command.ddl.CreateTableData;
import org.h2.engine.Session;
import org.h2.index.Index;
import org.h2.index.IndexType;
import org.h2.result.Row;
import org.h2.table.IndexColumn;
import org.h2.table.TableBase;
import org.h2.util.New;

import java.util.ArrayList;

/**
 * Created by ultrix on 03/11/2016.
 */
public class RestTable extends TableBase {

    private Table table;
    private Request request;
    private Parameters parameters;

    //private final PageDataIndex mainIndex;
    private final Index mainIndex;
    private Index scanIndex;

    private final ArrayList<Index> indexes = New.arrayList();

    public RestTable(Table table, CreateTableData data, String requestClass, Parameters parameters) {
        super(data);
        this.table = table;

        try {

            Class<Request> clazz = (Class<Request>) Class.forName(requestClass);
            request = clazz.newInstance();
            request.setRestTable(this);

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.parameters = parameters;

        if (data.persistData && database.isPersistent()) {
            mainIndex = new RestIndex(this, data.id,
                    IndexColumn.wrap(getColumns()), IndexType.createScan(data.persistData));
            scanIndex = mainIndex;
        } else {
            mainIndex = null;
            scanIndex = new RestIndex(this, data.id,
                    IndexColumn.wrap(getColumns()), IndexType.createScan(data.persistData));
        }

        this.indexes.add(scanIndex);
    }


    @Override
    public boolean lock(Session session, boolean b, boolean b1) {
        return false;
    }

    @Override
    public void close(Session session) {

    }

    @Override
    public void unlock(Session session) {

    }

    @Override
    public Index addIndex(Session session, String s, int i, IndexColumn[] indexColumns, IndexType indexType, boolean b, String s1) {
        return null;
    }

    @Override
    public void removeRow(Session session, Row row) {

    }

    @Override
    public void truncate(Session session) {

    }

    @Override
    public void addRow(Session session, Row row) {

    }

    @Override
    public void checkSupportAlter() {

    }

    @Override
    public String getTableType() {
        return null;
    }

    @Override
    public Index getScanIndex(Session session) {
        return scanIndex;
    }

    @Override
    public Index getUniqueIndex() {
        return null;
    }

    @Override
    public ArrayList<Index> getIndexes() {
        return indexes;
    }

    @Override
    public boolean isLockedExclusively() {
        return false;
    }

    @Override
    public long getMaxDataModificationId() {
        return 0;
    }

    @Override
    public boolean isDeterministic() {
        return false;
    }

    @Override
    public boolean canGetRowCount() {
        return false;
    }

    @Override
    public boolean canDrop() {
        return false;
    }

    @Override
    public long getRowCount(Session session) {
        return 0;
    }

    @Override
    public long getRowCountApproximation() {
        return 0;
    }

    @Override
    public long getDiskSpaceUsed() {
        return 0;
    }

    @Override
    public void checkRename() {

    }

    public Table getTable() {
        return table;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public Request getRequest() {
        return request;
    }
}
