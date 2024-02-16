package kz.kdlolymp.springcallkomek.entity;

public class TableData {
    private String columnName;
    private String dataValue;

    public TableData() { }

    public TableData(String columnName, String dataValue) {
        this.columnName = columnName;
        this.dataValue = dataValue;
    }

    public String getColumnName() { return columnName; }

    public void setColumnName(String columnName) { this.columnName = columnName; }

    public String getDataValue() { return dataValue; }

    public void setDataValue(String dataValue) { this.dataValue = dataValue; }
}
