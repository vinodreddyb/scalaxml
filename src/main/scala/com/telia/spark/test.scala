package com.telia.spark

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import java.io.IOException
import java.io.FileNotFoundException
import scala.io.Source
import java.io.{FileReader, FileNotFoundException, IOException}
import scala.xml.XML
object test {

  def main(args: Array[String]): Unit = {
    var sb = new StringBuilder
    val filename = "sample.xml"
    try {
      for (line <- Source.fromFile(filename).getLines()) {
        sb.append(line)
        //println(line)
      }
    } catch {
      case ex: FileNotFoundException => println("Couldn't find that file.")
      case ex: IOException => println("Had an IOException trying to read that file")
    }
    val shipTo = scalaxb.fromXML[com.telia.spark.generated.Ticket](XML.loadString(sb.toString()))
    println(shipTo)
    
  }
}