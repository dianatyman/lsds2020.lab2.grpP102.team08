/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upf.edu;

import java.util.Arrays;
import java.util.List;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
import upf.edu.parser.ExtendedSimplifiedTweet;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dianatyman
 */
public class BiGramsApp {
    public static void main(String[] args) {
        List<String> argsList = Arrays.asList(args);
        String language = argsList.get(0);
        String outputFile = argsList.get(1);
        String inputFile = argsList.get(2);

        //Create a SparkContext to initialize
        SparkConf conf = new SparkConf().setAppName("BiGramsApp");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        // Load input
        JavaRDD<String> sentences = sparkContext.textFile(inputFile);


        JavaPairRDD<Integer, String> counts = sentences
            .map(a->ExtendedSimplifiedTweet.fromJson(a))
            .filter(b->b.isPresent())
            .map(a->a.get())
            .filter(a->ExtendedSimplifiedTweet.getLanguage(a).equals(language))
            .map(a->a.generateBiGrams())
            .filter(c->c.isPresent())
            .flatMap(a->a.get().iterator())
            .mapToPair(word->new Tuple2<>(word,1))
            .reduceByKey((d, e) -> d+e)
            .mapToPair(b -> new Tuple2<>(b._2, b._1))
            .sortByKey();


        System.out.println("Total words: " + counts.count());
        counts.saveAsTextFile(outputFile);
    }
    
}

    


