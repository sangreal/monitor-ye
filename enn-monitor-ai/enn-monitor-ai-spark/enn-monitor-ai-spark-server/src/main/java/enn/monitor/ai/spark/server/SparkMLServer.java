package enn.monitor.ai.spark.server;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.ml.feature.PCA;
import org.apache.spark.ml.feature.PCAModel;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class SparkMLServer {

	public static void main(String args[]) throws Exception {
//		SparkSession spark = SparkSession.builder().appName("JavaTfIdfExample").master("local[32]").getOrCreate();
//
//		// $example on$
//		List<Row> data = Arrays.asList(RowFactory.create(0.0, "Hi I heard about Spark"),
//				RowFactory.create(0.0, "I wish Java could use case classes"),
//				RowFactory.create(1.0, "Logistic regression models are neat"));
//		StructType schema = new StructType(
//				new StructField[] { new StructField("label", DataTypes.DoubleType, false, Metadata.empty()),
//						new StructField("sentence", DataTypes.StringType, false, Metadata.empty()) });
//		Dataset<Row> sentenceData = spark.createDataFrame(data, schema);
//
//		Tokenizer tokenizer = new Tokenizer().setInputCol("sentence").setOutputCol("words");
//		Dataset<Row> wordsData = tokenizer.transform(sentenceData);
//		
//		int numFeatures = 20;
//		HashingTF hashingTF = new HashingTF().setInputCol("words").setOutputCol("rawFeatures")
//				.setNumFeatures(numFeatures);
//
//		Dataset<Row> featurizedData = hashingTF.transform(wordsData);
//		
//		System.out.println(featurizedData.apply("rawFeatures"));
//		
//		// alternatively, CountVectorizer can also be used to get term frequency
//		// vectors
//
//		IDF idf = new IDF().setInputCol("rawFeatures").setOutputCol("features");
//		IDFModel idfModel = idf.fit(featurizedData);
//		
//		Dataset<Row> rescaledData = idfModel.transform(featurizedData);
//		rescaledData.select("*").show(false);
//		// $example off$
//		
//		spark.stop();
		
		SparkSession spark = SparkSession.builder().appName("JavaTfIdfExample").master("local[32]").getOrCreate();
		
		List<Row> data = Arrays.asList(
		  RowFactory.create(Vectors.dense(1, 1, 1, 1, 1)),
		  RowFactory.create(Vectors.dense(2.0, 0.0, 3.0, 4.0, 5.0)),
		  RowFactory.create(Vectors.dense(4.0, 0.0, 0.0, 6.0, 7.0)),
		  RowFactory.create(Vectors.dense(2.0, 0.0, 3.0, 4.0, 5.0)),
		  RowFactory.create(Vectors.dense(4.0, 0.0, 0.0, 6.0, 7.0)),
		  RowFactory.create(Vectors.dense(2.0, 0.0, 3.0, 4.0, 5.0)),
		  RowFactory.create(Vectors.dense(4.0, 0.0, 0.0, 6.0, 7.0))
		);

		StructType schema = new StructType(new StructField[]{
		  new StructField("features", new VectorUDT(), false, Metadata.empty()),
		});

		Dataset<Row> df = spark.createDataFrame(data, schema);

		PCAModel pca = new PCA()
		  .setInputCol("features")
		  .setOutputCol("pcaFeatures")
		  .setK(3)
		  .fit(df);
		
		Dataset<Row> result = pca.transform(df).select("pcaFeatures");
		result.show(false);
				
		spark.stop();
	}

}
