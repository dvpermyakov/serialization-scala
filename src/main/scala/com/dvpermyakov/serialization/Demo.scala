package com.dvpermyakov.serialization

object Demo {
  def main(args: Array[String]): Unit = {
    val obj = new Sample()
    val ser = Serialization.serialize(obj)
    println(ser)
    val obj2 = Serialization.deserialize(ser)
    println(obj2)
  }
}
