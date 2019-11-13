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
