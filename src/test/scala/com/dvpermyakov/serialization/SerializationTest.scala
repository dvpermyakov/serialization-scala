package com.dvpermyakov.serialization

import org.junit.{Assert, Test}

class Sample() {
  val valueString: String = "str"
  val valueInt: Int = 0
  val valueLong: Long = 0L
  val valueFloat: Float = 0f
  val valueDouble: Double = 0
  val valueChar: Char = '0'
  val valueBoolean: Boolean = false
  val valueByte: Byte = 0
  val valueShort: Short = 0
}

class SerializationTest {

  @Test
  def simpleClass(): Unit = {
    val sample = new Sample()
    val serData = Serialization.serialize(sample)
    val sample2: Sample = Serialization.deserialize(serData).asInstanceOf[Sample]

    Assert.assertEquals(
      sample.valueString,
      sample2.valueString
    )
    Assert.assertEquals(
      sample.valueInt,
      sample2.valueInt
    )
    Assert.assertEquals(
      sample.valueLong,
      sample2.valueLong
    )
    Assert.assertEquals(
      sample.valueFloat,
      sample2.valueFloat,
      0.000001f
    )
    Assert.assertEquals(
      sample.valueDouble,
      sample2.valueDouble,
      0.000001
    )
    Assert.assertEquals(
      sample.valueChar,
      sample2.valueChar
    )
    Assert.assertEquals(
      sample.valueBoolean,
      sample2.valueBoolean
    )
    Assert.assertEquals(
      sample.valueByte,
      sample2.valueByte
    )
    Assert.assertEquals(
      sample.valueShort,
      sample2.valueShort
    )
  }

}
