package com.dvpermyakov.serialization

class Sample() {
  private val id: String = "id"
  private val value: Int = 0

  override def toString: String = {
    s"id=$id" +
      s"value=$value"
  }
}