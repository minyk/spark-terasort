Spark-Terasort
==============

Terasort for Spark

# How to run

## TeraGen
Usage:
```
spark-submit --class com.nexr.spark.terasort.TeraGen --deploy-mode client --master yarn spark-terasort-0.1.jar [output-size] [output-directory]
```

Example:
```
spark-submit --class com.nexr.spark.terasort.TeraGen --deploy-mode client --master yarn spark-terasort-0.1.jar 1G /user/root/teragen
```

## TeraSort
Usage:
```
spark-submit --class com.nexr.spark.terasort.TeraSort --deploy-mode client --master yarn spark-terasort-0.1.jar [input-file] [output-file]
```

Example:
```
spark-submit --class com.nexr.spark.terasort.TeraSort --deploy-mode client --master yarn spark-terasort-0.1.jar /user/root/teragen /user/root/terasort
```

## TeraValidate
Usage:
```
spark-submit --class com.nexr.spark.terasort.TeraValidate --deploy-mode client --master yarn spark-terasort-0.1.jar [input-directory]
```

Example:
```
spark-submit --class com.nexr.spark.terasort.TeraValidate --deploy-mode client --master yarn spark-terasort-0.1.jar /user/root/teragen
```

# Internals

# Acknowledgement

This codes are from `https://github.com/ehiggs/spark/tree/terasort`, [Ewan Higgs](https://github.com/ehiggs)' terasort example. Thank you for great example.