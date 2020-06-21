package com.dvpermyakov.serialization

import scala.collection.mutable.ListBuffer

object Serialization {

  def serialize(obj: Object): String = {
    val fullName = obj.getClass.getCanonicalName

    var fieldNames = new ListBuffer[String]()
    obj.getClass.getDeclaredFields.foreach { field =>
      field.setAccessible(true)
      fieldNames += s"${field.getName}:${field.get(obj)}"
    }

    s"$fullName-${fieldNames.mkString("+")}"
  }

  def deserialize(string: String): Any = {
    val fullName: String = string.split("-")(0)

    val clazz = Class.forName(fullName)
    val constructor = clazz.getConstructor()
    val obj = constructor.newInstance()

    obj
  }

}
