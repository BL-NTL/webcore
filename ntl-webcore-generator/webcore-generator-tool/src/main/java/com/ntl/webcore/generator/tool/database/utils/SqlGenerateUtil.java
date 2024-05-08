package com.ntl.webcore.generator.tool.database.utils;

import com.ntl.webcore.common.lang.string.StrUtils;
import com.ntl.webcore.generator.tool.database.bean.DBTable;
import com.ntl.webcore.generator.tool.database.bean.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成工具
 */
public class SqlGenerateUtil {


    /**
     * MySql 数据库表结构创建
     * @param tableEnum
     */
    public static void createMysqlTableSqlScript(DBTable tableEnum){
        System.out.println("\n\n-- --------------------------------------------------------------");
        System.out.println("-- "+ tableEnum.getTableName() + ": " + tableEnum.getTableComment());
        System.out.println("-- --------------------------------------------------------------");

        String[] dataArray = tableEnum.getDataCSV().split("\\|");
        StringBuffer primaryKeyColumns = new StringBuffer();
        // 建表语句
        System.out.println("DROP TABLE IF EXISTS "+ tableEnum.getTableName() +";");
        System.out.println("CREATE TABLE "+ tableEnum.getTableName() +"(");
        for(int i = 0; i< dataArray.length; i++){
            Table table = new Table(dataArray[i],Table.DbType.MYSQL);
            System.out.printf("    %-25s",table.getColumn());
            try{
                switch (table.getType().toUpperCase()){
                    case "VARCHAR":
                        System.out.printf("%-25s","varchar(" + table.getLength() + ")");
                        break;
                    case "TINYINT":
                    case "SMALLINT":
                    case "INTEGER":
                    case "INT":
                        System.out.printf("%-25s","int");
                        break;
                    case "BIGINT":
                        System.out.printf("%-25s","bigint");
                        break;
                    case "DOUBLE":
                        System.out.printf("%-25s","double");
                        break;
                    case "DATETIME":
                        System.out.printf("%-25s","datetime");
                        break;
                    case "TIMESTAMP":
                        System.out.printf("%-25s","timestamp(3)");
                        break;
                    case "CHAR":
                        System.out.printf("%-25s","char(" + table.getLength() + ")");
                        break;
                    case "LONGTEXT":
                        System.out.printf("%-25s","longtext");
                        break;
                }
                // 主键
                if(table.isPrimaryKey()){
                    if(primaryKeyColumns.length() > 0){
                        primaryKeyColumns.append("," + table.getColumn());
                    }else{
                        primaryKeyColumns.append(table.getColumn());
                    }
                    System.out.printf("%-60s","");
                }else{
                    // 可空
                    if(table.isEmpty()){
                        System.out.printf("%-25s","");
                    }else{
                        System.out.printf("%-25s","not null");
                    }
                    // 默认值
                    if(StrUtils.isNotEmpty(table.getDefaultValue())){
                        System.out.printf("%-35s","default " + table.getDefaultValue());
                    }else{
                        System.out.printf("%-35s","");
                    }
                }
                // 注释
                System.out.printf("%-30s","comment '" + table.getColumnComment() + "',");
                System.out.println();
            }catch(Exception e){
                e.printStackTrace();
            }

        }// for
        //打印主键
        if(primaryKeyColumns.length() > 0){
            System.out.println("    PRIMARY KEY (" + primaryKeyColumns + ")");
        }
        System.out.println(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='"+ tableEnum.getTableComment() +"';");

    }

    /**
     * Oracle 数据库表结构创建
     * @param tableEnum
     */
    public static void createOracleTableSqlScript(DBTable tableEnum){
        System.out.println("\n\n-- ----------------------------");
        System.out.println("-- "+ tableEnum.getTableComment() +" " + tableEnum.getTableName());
        System.out.println("-- ----------------------------");

        String[] dataArray = tableEnum.getDataCSV().split("\\|");
        StringBuffer primaryKeyColumns = new StringBuffer();
        List<String> commentList = new ArrayList<>();
        commentList.add("COMMENT ON TABLE "+ tableEnum.getTableName() +" IS '"+ tableEnum.getTableComment() +"';");
        // 建表语句
        System.out.println("CREATE TABLE "+ tableEnum.getTableName() +"(");
        for(int i = 0; i< dataArray.length; i++){
            Table table = new Table(dataArray[i], Table.DbType.ORACLE);
            commentList.add("COMMENT ON COLUMN "+ tableEnum.getTableName() + "." + table.getColumn() +" IS '"+ table.getColumnComment() +"';");
            System.out.printf("    %-25s",table.getColumn());
            try{
                String columnType = table.getType().toUpperCase();
                switch (columnType){
                    case "VARCHAR":
                        System.out.printf("%-25s","varchar2(" + table.getLength() + " char)");
                        break;
                    case "TINYINT":
                        System.out.printf("%-25s","number(5)");
                        break;
                    case "SMALLINT":
                        System.out.printf("%-25s","number(10)");
                        break;
                    case "INTEGER":
                    case "INT":
                        System.out.printf("%-25s","integer");
                        break;
                    case "BIGINT":
                        System.out.printf("%-25s","number(38)");
                        break;
                    case "DOUBLE":
                        System.out.printf("%-25s","number(" + table.getLength() + ")");
                        break;
                    case "DATETIME":
                        System.out.printf("%-25s","date");
                        break;
                    case "CHAR":
                        System.out.printf("%-25s","char(" + table.getLength() + ")");
                        break;
                    case "LONGTEXT":
                        System.out.printf("%-25s","clob");
                        break;
                }
                // 记录主键
                if(table.isPrimaryKey()){
                    if(primaryKeyColumns.length() > 0){
                        primaryKeyColumns.append("," + table.getColumn());
                    }else{
                        primaryKeyColumns.append(table.getColumn());
                    }
                    System.out.printf("%-35s","");
                    System.out.print(",");
                }else{
                    // 默认值
                    if(StrUtils.isNotEmpty(table.getDefaultValue())){
                        System.out.printf("%-35s","default " + table.getDefaultValue());
                    }else{
                        System.out.printf("%-35s","");
                    }
                    // 可空
                    if(table.isEmpty()){
                        System.out.printf("%-25s",",");
                    }else{
                        System.out.printf("%-25s","not null,");
                    }

                }
                System.out.println();
            }catch(Exception e){
                e.printStackTrace();
            }
        }// for
        // 打印主键
        System.out.println("    PRIMARY KEY (" + primaryKeyColumns + ")");
        System.out.println(" );");

        for(String comment: commentList){
            System.out.println(comment);
        }


    }



}
