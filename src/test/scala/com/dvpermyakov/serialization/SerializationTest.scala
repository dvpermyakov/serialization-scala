package com.dvpermyakov.serialization

import org.junit.{Assert, Test}

class Sample() {
  val id: String = "id"
  val value: Int = 0
}

class SerializationTest {

  @Test
  def simpleClass(): Unit = {
    val sample = new Sample()
    val serData = Serialization.serialize(sample)
    val sample2: Sample = Serialization.deserialize(serData).asInstanceOf[Sample]

    Assert.assertEquals(
      sample.id,
      sample2.id
    )
    Assert.assertEquals(
      sample.value,
      sample2.value
    )
  }

}
