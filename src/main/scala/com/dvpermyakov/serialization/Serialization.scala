package com.dvpermyakov.serialization

import scala.collection.mutable.ListBuffer

object Serialization {

  def serialize(obj: Object): String = {
    val fullName = obj.getClass.getCanonicalName

    var fieldNames = new ListBuffer[String]()
    obj.getClass.getDeclaredFields.foreach { field =>
      field.setAccessible(true)
      fieldNames += s"${field.getName}:${field.get(obj)}:${field.getType.getSimpleName}"
    }

    s"$fullName-${fieldNames.mkString("+")}"
  }

  def deserialize(string: String): Any = {
    val fullName: String = string.split('-')(0)
    val fieldArgs: List[String] = string.split('-')(1).split('+').toList

    val clazz = Class.forName(fullName)
    val obj = clazz.getConstructor().newInstance()

    for (fieldArg: String <- fieldArgs) {
      val name = fieldArg.split(':')(0)
      val value = fieldArg.split(':')(1)
      val typ = fieldArg.split(':')(2)
      val field = clazz.getDeclaredField(name)
      field.setAccessible(true)
      field.set(obj, castToTypes(typ, value))
    }

    obj
  }

  private def castToTypes(typ: String, value: String): Any = {
    typ match {
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
