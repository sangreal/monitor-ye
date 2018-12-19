package enn.monitor.ai.spark.test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;

import scala.Tuple2;

public class JavaWordCount {
	  private static final Pattern SPACE = Pattern.compile(" ");

	  public static void main(String[] args) throws Exception {

	    SparkSession spark = SparkSession
	      .builder()
	      .appName("JavaWordCount")
	      .master("local[32]")
	      .getOrCreate();

	    JavaRDD<String> lines = spark.read().textFile("/Users/micklongen/code/helium_zhaoyang_helium-server.ovpn").javaRDD();

	    JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(SPACE.split(s)).iterator());

	    JavaPairRDD<String, Integer> ones = words.mapToPair(s -> new Tuple2<>(s, 1));

	    JavaPairRDD<String, Integer> counts = ones.reduceByKey((i1, i2) -> i1 + i2);
	    
	    JavaPairRDD<Integer, String> sorts = counts.mapToPair(s -> new Tuple2<>(s._2, s._1));
	    JavaPairRDD<Integer, String> sortRDD = sorts.sortByKey(false);

	    List<Tuple2<Integer, String>> output = sortRDD.collect();
	    for (Tuple2<?,?> tuple : output) {
	      System.out.println(tuple._2() + ": " + tuple._1());
	    }
	    spark.stop();
	  }
}
