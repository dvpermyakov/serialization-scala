package com.dvpermyakov.serialization

import scala.collection.mutable.ListBuffer

object Serialization {

  def serialize[T](obj: T): String = {

    val fullName = obj.getClass.getCanonicalName

    var fieldNames = new ListBuffer[String]()
    obj.getClass.getDeclaredFields.foreach { field =>
      field.setAccessible(true)
      fieldNames += s"${field.getName}:${field.get(obj)}:${field.getType.getSimpleName}"
    }

    s"$fullName-${fieldNames.mkString("+")}"
  }

  def deserialize[T](string: String): T = {
    val stringSplit = string.split('-')
    val (fullName: String, fieldArgs: List[String]) = (stringSplit(0), stringSplit(1).split('+').toList)

    val clazz = Class.forName(fullName)
    val obj = ClassFactory.get(clazz).newInstance().asInstanceOf[T]

    for (fieldArg: String <- fieldArgs) {
      val fieldSplit = fieldArg.split(':')
      val (name, value, rawType) = (fieldSplit(0), fieldSplit(1), fieldSplit(2))
      val field = clazz.getDeclaredField(name)
      field.setAccessible(true)
      field.set(obj, castToTypes(rawType, value))
    }

    obj
  }

  private def castToTypes(rawType: String, value: String): Any = {
    rawType match {
      case "String" => value
      case "int" => value.toInt
      case "long" => value.toLong
      case "float" => value.toFloat
      case "double" => value.toDouble
      case "char" => value.toCharArray()(0)
      case "boolean" => value.toBoolean
      case "byte" => value.toByte
      case "short" => value.toShort
    }
  }

}
