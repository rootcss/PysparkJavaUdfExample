package com.rootcss;

import org.apache.spark.sql.api.java.UDF1;

public class SparkJavaUdfExample implements UDF1<String, Integer> {
    public Integer call(String input) {
        return input.length();
    }
}