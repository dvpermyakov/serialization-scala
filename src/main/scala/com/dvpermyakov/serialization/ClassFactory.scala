package com.dvpermyakov.serialization

abstract class ClassFactory[T] {
  def newInstance(): T
}

object ClassFactory {
  def get[T](rawType: Class[T]): ClassFactory[T] = {
    new ClassFactory[T] {
      override def newInstance(): T = {
        try {
          return getByDefaultConstructor(rawType)
        } catch {
          case _: Throwable =>
        }

        try {
          return getByUnsafe(rawType)
        } catch {
          case _: Throwable =>
        }

        throw new IllegalStateException("Didn't find new instance method")
      }
    }
  }

  private def getByDefaultConstructor[T](rawType: Class[T]): T = {
    val constructor = rawType.getDeclaredConstructor()
    constructor.setAccessible(true)
    constructor.newInstance()
  }

  private def getByUnsafe[T](rawType: Class[T]): T = {
    val unsafeClass = Class.forName("sun.misc.Unsafe")
    val f = unsafeClass.getDeclaredField("theUnsafe")
    f.setAccessible(true)
    val unsafe = f.get(null)
    val allocateInstance = unsafeClass.getMethod("allocateInstance", classOf[Class[_]])
    val obj = allocateInstance.invoke(unsafe, rawType)
    obj.asInstanceOf[T]
  }

}
