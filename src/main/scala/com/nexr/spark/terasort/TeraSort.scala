package com.nexr.spark.terasort

import com.google.common.primitives.UnsignedBytes
import org.apache.spark.SparkContext._
import org.apache.spark._
import org.apache.spark.{SparkConf, SparkContext}

/**
 * An application that generates data according to the terasort spec and shuffles them.
 * This is a great example program to stress test Spark's shuffle mechanism.
 *
 * See http://sortbenchmark.org/
 */
object TeraSort {

  implicit val caseInsensitiveOrdering = UnsignedBytes.lexicographicalComparator

  def main(args: Array[String]) {

    if (args.length < 2) {
      println("usage:")
      println("DRIVER_MEMORY=[mem] bin/run-example terasort.TeraSort " +
        "[input-file] [output-file]")
      println(" ")
      println("example:")
      println("DRIVER_MEMORY=50g bin/run-example terasort.TeraSort " +
        "/home/myuser/terasort_in /home/myuser/terasort_out")
      System.exit(0)
    }

    // Process command line arguments
    val inputFile = args(0)
    val outputFile = args(1)

    val conf = new SparkConf()
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .setAppName(s"TeraSort")
    val sc = new SparkContext(conf)

    val dataset = sc.newAPIHadoopFile[Array[Byte], Array[Byte], TeraInputFormat](inputFile)
    val sorted = dataset.partitionBy(new TeraSortPartitioner(dataset.partitions.length)).sortByKey()
    sorted.saveAsNewAPIHadoopFile[TeraOutputFormat](outputFile)
  }
}