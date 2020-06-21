package com.dvpermyakov.serialization

abstract class ClassFactory[T] {
  def newInstance(): T
}

object ClassFactory {
  def get[T](rawType: Class[T]): ClassFactory[T] = {
    () => {
      val constructor = rawType.getDeclaredConstructor()
      constructor.setAccessible(true)
      constructor.newInstance()
    }
  }
}
