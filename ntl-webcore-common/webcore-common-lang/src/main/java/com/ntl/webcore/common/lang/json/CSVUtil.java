package com.ntl.webcore.common.lang.json;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil<T> {

    public static<T> List<T> deserializeCSVFileToListObject(String fileName, Class<T> clazz){
        File file = new File(fileName);
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.typedSchemaFor(clazz).withHeader().withColumnReordering(true);
        List<T> list = new ArrayList<>();
        try {
            MappingIterator<T> it = mapper.readerFor(clazz).with(schema).readValues(file);
            while (it.hasNextValue()) {
                list.add((T) it.nextValue());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    public static<T> List<T> deserializeCSVFileToListObject(InputStream file, Class<T> clazz){
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.typedSchemaFor(clazz).withHeader().withColumnReordering(true);
        List<T> list = new ArrayList<>();
        try {
            MappingIterator<T> it = mapper.readerFor(clazz).with(schema).readValues(file);
            while (it.hasNextValue()) {
                list.add((T) it.nextValue());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return list;
    }


}
