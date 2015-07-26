package ngn.spark

import org.apache.spark.{SparkConf, SparkContext}

trait SparkJobApp extends App with JobRunner {
  val conf = new SparkConf()
    .setMaster("local[*]")
    .setAppName("cross-validation")

  implicit val sc = new SparkContext(conf)
}

trait JobRunner {
  def run[T <: SparkJob](job: T)(implicit sc: SparkContext): Unit = {
    try {
      job.execute(sc)
    } finally {
      sc.stop()
    }
  }
}