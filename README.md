Example of Spark Java UDF for blog post.

-------------
Notes below:
mvn clean
mvn build


bin/pyspark --jars /path/to/file.jar

```python
from pyspark.sql.types import IntegerType
from pyspark.sql.functions import udf
sqlContext.registerJavaFunction("myCustomUdf", "com.rootcss.SparkJavaUdfExample", IntegerType())
spark.sql("SELECT myCustomUdf('shekhar')").show()

df = spark.read.text("/Users/rootcss/Downloads/input_file.csv")
df.count()

df.registerTempTable("table")
spark.sql("SELECT SUM(value) FROM table").show()


def wordCount(r):
    return len(r)

wcUdf = udf(wordCount, IntegerType())
spark.sql("SELECT myCustomUdf('shekhar')").show()


def myCustomUdf(column):
    from pyspark.sql.column import Column, _to_java_column, _to_seq
    jc = spark._jvm.com.rootcss.SparkJavaUdfExample
    return Column(jc(_to_seq(sc, [column], _to_java_column)))


def my_udf():
    from pyspark.sql.column import Column, _to_java_column, _to_seq
    pcls = "com.rootcss.SparkJavaUdfExample"
    jc = sc._jvm.java.lang.Thread.currentThread() \
        .getContextClassLoader().loadClass(pcls).newInstance().getUdf().apply
    return Column(jc(_to_seq(sc, [], _to_java_column)))

lines.withColumn('_counter', myCustomUdf('text')).show()
lines.withColumn('_counter', maturity_udf('text')).show()


def myCol(col):
    _f = sc._jvm.com.rootcss.SparkJavaUdfExample.apply
    return Column(_f(_to_seq(sc,[col], _to_java_column)))
```