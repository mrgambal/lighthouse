package be.dataminded.lighthouse.spark

import org.apache.spark.sql.SparkSession

/**
  * Mixin provides the application with a [[SparkSession]].
  */
trait SparkSessions {

  /**
    * Custom Spark configuration for the job.
    */
  val sparkOptions: Map[String, String] = Map.empty

  private val defaultConfiguration: Map[String, String] = Map(
    "spark.driver.memory"                                    -> "2g",
    "spark.serializer"                                       -> "org.apache.spark.serializer.KryoSerializer",
    "spark.sql.avro.compression.codec"                       -> "snappy",
    "spark.sql.parquet.compression.codec"                    -> "snappy",
    "spark.sql.sources.partitionColumnTypeInference.enabled" -> "false"
  )

  lazy val spark: SparkSession =
    (defaultConfiguration ++ sparkOptions)
      .foldLeft(SparkSession.builder()) {
        case (b, (key, value)) => b.config(key, value)
      }
      .enableHiveSupport()
      .getOrCreate()
}
