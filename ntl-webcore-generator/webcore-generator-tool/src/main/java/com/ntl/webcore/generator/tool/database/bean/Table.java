package com.ntl.webcore.generator.tool.database.bean;


import com.ntl.webcore.common.lang.string.StrUtils;

import static com.ntl.webcore.generator.tool.database.constants.ColumnConstant.CURRENT_DATETIME;
import static com.ntl.webcore.generator.tool.database.constants.ColumnConstant.DEFAULT_EMPTY_STRING;

public class Table {
    private String column;
    private String columnComment;
    private String type;
    private boolean primaryKey;
    private String length;
    private boolean empty;
    private String defaultValue;
    private String remark;

    public Table() {
    }

    public Table(String row, String dbType){
        String[] data = row.split(",",-1);
        this.column = data[0];
        this.columnComment = data[1];
        this.type = data[2];
        this.primaryKey = "Y".equals(data[3]);
        this.length = data[4];
        this.empty = "Y".equals(data[5]);
        this.remark = data[7];
        if(DbType.MYSQL.equals(dbType)){
            if(DEFAULT_EMPTY_STRING.equals(data[6])){
                this.defaultValue = "''";
            }else if(CURRENT_DATETIME.equals(data[6])){
                this.defaultValue = "current_timestamp";
            }else{
                this.defaultValue = convertDefaultValue(data[6]);
            }
        }else if(DbType.ORACLE.equals(dbType)){
            if(DEFAULT_EMPTY_STRING.equals(data[6])){
                this.defaultValue = null;
            }else if(CURRENT_DATETIME.equals(data[6])){
                this.defaultValue = "sysdate";
            }else{
                this.defaultValue = convertDefaultValue(data[6]);
            }
        }

    }

    private String convertDefaultValue(String value){
        String result = null;
        if(StrUtils.isEmpty(value)){
            return null;
        }
        switch (this.type.toUpperCase()){
            case "VARCHAR":
            case "CHAR":
            case "DATETIME":
            case "LONGTEXT":
                result = "'"+ value +"'";
                break;
            case "TINYINT":
            case "SMALLINT":
            case "INTEGER":
            case "INT":
            case "BIGINT":
            case "DOUBLE":
                result = value;
                break;
        }
        return result;
    }

    public Table(String column, String columnComment, String type, boolean primaryKey, String length, boolean empty, String defaultValue, String remark) {
        this.column = column;
        this.columnComment = columnComment;
        this.type = type;
        this.primaryKey = primaryKey;
        this.length = length;
        this.empty = empty;
        this.defaultValue = defaultValue;
        this.remark = remark;
    }

    public String getColumn() {
        return column;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public String getType() {
        return type;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public String getLength() {

        return length.replace("_",",");
    }

    public boolean isEmpty() {
        return empty;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getRemark() {
        return remark;
    }

    public class DbType{
        public static final String MYSQL = "mysql";
        public static final String ORACLE = "oracle";
    }
}
