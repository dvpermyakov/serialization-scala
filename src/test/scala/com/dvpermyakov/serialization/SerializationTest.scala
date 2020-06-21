package com.dvpermyakov.serialization

import org.junit.{Assert, Test}

class SampleDefaultConstructor() {
  val valueString: String = "str"
  val valueInt: Int = 100
  val valueLong: Long = 110L
  val valueFloat: Float = 120f
  val valueDouble: Double = 130
  val valueChar: Char = '$'
  val valueBoolean: Boolean = true
  val valueByte: Byte = 10
  val valueShort: Short = 20
}

class SampleConstructor(
                         val valueString: String,
                         val valueInt: Int,
                         val valueLong: Long,
                         val valueFloat: Float,
                         val valueDouble: Double,
                         val valueChar: Char,
                         val valueBoolean: Boolean,
                         val valueByte: Byte,
                         val valueShort: Short
                       )

case class SampleCase(
                       valueString: String,
                       valueInt: Int,
                       valueLong: Long,
                       valueFloat: Float,
                       valueDouble: Double,
                       valueChar: Char,
                       valueBoolean: Boolean,
                       valueByte: Byte,
                       valueShort: Short
                     )

class SerializationTest {

  @Test
  def sampleDefaultConstructor(): Unit = {
    val serialization = new Serialization(ClassFactory.get(classOf[SampleDefaultConstructor]))

    val sample = new SampleDefaultConstructor()
    val serData = serialization.serialize(sample)
    val sample2: SampleDefaultConstructor = serialization.deserialize(serData)

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

  @Test
  def sampleConstructor(): Unit = {
    val serialization = new Serialization(ClassFactory.get(classOf[SampleConstructor]))

    val sample = new SampleConstructor(
      valueString = "new string",
      valueInt = 10,
      valueBoolean = false,
      valueByte = 100,
      valueChar = '*',
      valueDouble = 200,
      valueLong = 300L,
      valueFloat = 400f,
      valueShort = 20
    )
    val serData = serialization.serialize(sample)
    val sample2: SampleConstructor = serialization.deserialize(serData)

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

  @Test
  def sampleCase(): Unit = {
    val serialization = new Serialization(ClassFactory.get(classOf[SampleCase]))

    val sample = SampleCase(
      valueString = "str",
      valueInt = 400,
      valueBoolean = true,
      valueByte = 121,
      valueChar = '0',
      valueDouble = 111,
      valueLong = 112L,
      valueFloat = 122f,
      valueShort = 30
    )
    val serData = serialization.serialize(sample)
    val sample2: SampleCase = serialization.deserialize(serData)

    val assertStringsEquals = (str1: String, str2: String) => Assert.assertEquals(str1, str2)
    assertStringsEquals.apply(sample.valueString, sample2.valueString)

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
