package com.dvpermyakov.serialization

import scala.collection.mutable.ListBuffer

class Serialization[T](classFactory: ClassFactory[T]) {

  def serialize(obj: T): String = {
    val fullName = obj.getClass.getCanonicalName

    var fieldNames = new ListBuffer[String]()
    obj.getClass.getDeclaredFields.foreach { field =>
      field.setAccessible(true)
      fieldNames += s"${field.getName}:${field.get(obj)}:${field.getType.getSimpleName}"
    }

    s"$fullName-${fieldNames.mkString("+")}"
  }

  def deserialize(string: String): T = {
    val fullName: String = string.split('-')(0)
    val fieldArgs: List[String] = string.split('-')(1).split('+').toList

    val clazz = Class.forName(fullName)
    val obj = classFactory.newInstance()

    for (fieldArg: String <- fieldArgs) {
      val name = fieldArg.split(':')(0)
      val value = fieldArg.split(':')(1)
      val rawType = fieldArg.split(':')(2)
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
