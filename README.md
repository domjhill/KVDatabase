* KVDatabase (KVDB) was written in order to easily read and write objects and list of objects of any type into Android's Shared Preferences. In addition to this, all primitive data types can be stored and read.

* KVDBuses Java's generic type in order to convert any type of object/list of object into their JSON representation, without the need to modify any code inside the library. 


### Usage: ###

If you only require use of primitive data types:


Reading and writing a string:

```
#!Android
//Instantiating the class:
//KVDatabase(context)
KVDatabase kvDatabase = new KVDatabase(getContext())

//storeString(String value, String key)
kvDatabase.storeString("String to be stored", "keyRetrieve");

//getString(String key)
String retrievedString = kvDatabase.getString("keyRetrieve");


//Other methods work in precisely the same way.
```

If you want object usage:



```
#!Android
//Using the following example class:
public class exampleClass {
     String name;
     int age;
}


//Instantiating the KVDB class:
//KVDatabase(context, Class<T>[] classtype)
 
KVDatabase kvDatabase = new KVDatabase(getContext(), exampleClass[].class);

//storeObject(T obj, String key)
exampleClass example = new exampleClass("Example", 21);
storeObject(example , "keyObj");

//getObject(String key)
exampleClass example = getObject("keyObj");

```


### Who do I talk to? ###
Any and all feedback is appreciated. If you think something needs to be added, email me at djhill1997@gmail.com