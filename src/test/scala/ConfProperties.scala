package ru.arip

import java.io.FileInputStream
import java.util.Properties
import scala.io.Source.fromFile

// Класс будет читать записанные в conf.properties значения.
class ConfProperties {
    //Путь до файла с настроками
    val fileInputStream = new FileInputStream("src/test/resourses/conf.properties")
    val PROPERTIES = new Properties()
    PROPERTIES.load(fileInputStream)

  //Метод запроса значений из файла с настройками
  def getProperty(key: String): String = PROPERTIES.getProperty(key)

  //Метод возвращает список вина
  def listWines: Array[String] = {
    val source = fromFile(getProperty("path"), getProperty("enc")) // (Путь к файлу и кодировка)
    val lines = try source.getLines.toArray finally source.close()
    lines
  }
}
