package com.ntl.webcore.generator.tool.database.utils;


import com.ntl.webcore.generator.tool.database.bean.DBTable;

public class TableStructureSqlUtil {
    public static void main(String[] args){
        for(DBTable tableEnum: TableEnum.values()){
//            SqlGenerateUtil.createMysqlTableSqlScript(tableEnum);
//            SqlGenerateUtil.createOracleTableSqlScript(tableEnum);
        }
        SqlGenerateUtil.createMysqlTableSqlScript(TableEnum.SYS_DICT_TYPE);
    }
}
