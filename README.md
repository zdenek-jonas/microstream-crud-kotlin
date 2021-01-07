# Microstream Kotlin Example

This project shows how to use Microstream with Kotlin

As a first step, the Microstream repo has to be included into build file. This example uses gradle. 

```gradle
repositories {
    mavenCentral()
    maven { url 'https://repo.microstream.one/repository/maven-releases/' }
}

dependencies {
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
    implementation('one.microstream:storage.embedded:04.01.00-MS-GA')
}
```

As a second step we create just one file Application.kt
```Kotlin
package one.microstream

import one.microstream.storage.types.EmbeddedStorage

class Customer(var firstName: String, var lastName: String, var customerNumber: Long) {
    override fun toString(): String {
        return "Customer(firstName='$firstName', lastName='$lastName', customerNumber=$customerNumber)"
    }
}

fun main() {
    val customers = mutableListOf<Customer>()
    customers.add(Customer("Thomas", "Wresler", 10005))
    customers.add(Customer("Jim", "Joe", 10006))
    customers.add(Customer("Kamil", "Limitsky", 1007))
    customers.add(Customer("Karel", "Ludvig", 1008))

    var storage = EmbeddedStorage.start(customers)

    customers[3].firstName = "Alex"
    storage.store(customers[3])
    storage.shutdown()

    val loadCustomers = mutableListOf<Customer>()
    storage = EmbeddedStorage.start(loadCustomers)
    loadCustomers.forEach(System.out::println)
    storage.shutdown()
}
```
This sample app makes an easiest operation with List. Create list, fill it with some data and save it. 
```kotlin
    val customers = mutableListOf<Customer>()
    customers.add(Customer("Thomas", "Wresler", 10005))
    customers.add(Customer("Jim", "Joe", 10006))
    customers.add(Customer("Kamil", "Limitsky", 1007))
    customers.add(Customer("Karel", "Ludvig", 1008))

    var storage = EmbeddedStorage.start(customers)
```    
As a second step, the last customer has been changed and store again.
```kotlin
    customers[3].firstName = "Alex"
    storage.store(customers[3])
```
The storage is shuted down and create again with empty list of the same class.
```kotlin
    val loadCustomers = mutableListOf<Customer>()
    storage = EmbeddedStorage.start(loadCustomers)
```
Then the program print the output into the screen to prove, that the last element has been saved and loaded correctly.
```kotlin
    loadCustomers.forEach(System.out::println)
```

The output is:
```
Customer(firstName='Thomas', lastName='Wresler', customerNumber=10005)
Customer(firstName='Jim', lastName='Joe', customerNumber=10006)
Customer(firstName='Kamil', lastName='Limitsky', customerNumber=1007)
Customer(firstName='Alex', lastName='Ludvig', customerNumber=1008)
```
