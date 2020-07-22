Util classes for serialize an object to a string and deserialize a string to an object

For example, if you have a class:
```Scala
case class Sample(str: String = "str", value: Int = 100)
```

You can use serialization method in order to convert the object to the string:
```Scala
val str: String = Serialization.serialize(Sample())
```

The string will be:
```
Sample-str:str:String+value:100:int
```

You can deserialize the string to the object:
```Scala
val sample: Sample = Serialization.deserialize(str)
```
