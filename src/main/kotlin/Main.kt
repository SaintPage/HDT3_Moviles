fun main() {
    // Creación de instancias de diferentes alimentos
    val burger = Burger("Hamburguesa Clásica", 8.0)
    val pizza = Pizza("Pizza Margarita", 10.0)
    val iceCream = IceCream("Helado de Vainilla", 5.0)
    val juice = Juice("Jugo de Naranja", 4.0)

    // Función para mostrar el menú y recibir la opción del usuario
    fun showMenu(): Int {
        println("\nSeleccione una opción\n")
        println("1. Cocinar hamburguesa")
        println("2. Cocinar pizza")
        println("3. Cocinar helado")
        println("4. Cocinar jugo")
        println("5. Comer helado")
        println("6. Verter jugo")
        println("7. Precio con descuento de la hamburguesa")
        println("8. Precio con descuento de la pizza")
        println("9. Precio con descuento del helado")
        println("10. Precio con descuento del jugo")
        println("11. Salir")
        print("Seleccione una opción: ")

        return readLine()?.toIntOrNull() ?: -1
    }

    // Función para recibir el porcentaje de descuento
    fun getDiscountPercentage(): Double {
        print("Ingrese el porcentaje de descuento: ")
        return readLine()?.toDoubleOrNull() ?: 0.0
    }

    // Bucle principal del menú
    var exit = false
    while (!exit) {
        when (val option = showMenu()) {
            1 -> println(burger.cook())
            2 -> println(pizza.cook())
            3 -> println(iceCream.cook())
            4 -> println(juice.cook())
            5 -> println(iceCream.eat())
            6 -> println(juice.pour())
            7 -> {
                val discount = getDiscountPercentage()
                println("Precio con descuento de la ${burger.name}: ${burger.discountedPrice(discount)}")
            }
            8 -> {
                val discount = getDiscountPercentage()
                println("Precio con descuento de la ${pizza.name}: ${pizza.discountedPrice(discount)}")
            }
            9 -> {
                val discount = getDiscountPercentage()
                println("Precio con descuento del ${iceCream.name}: ${iceCream.discountedPrice(discount)}")
            }
            10 -> {
                val discount = getDiscountPercentage()
                println("Precio con descuento del ${juice.name}: ${juice.discountedPrice(discount)}")
            }
            11 -> exit = true
            else -> println("Opción no válida. Intente de nuevo.")
        }
    }
}

// Clase base Food con propiedades name y price
abstract class Food(val name: String, val price: Double) {
    // Función abstracta que debe ser implementada por las subclases
    abstract fun cook(): String
}

// Subclase de Food para hamburguesas
class Burger(name: String, price: Double) : Food(name, price) {
    // Implementación de la función cook() para hamburguesas
    override fun cook(): String {
        return "Asando la hamburguesa $name"
    }
}

// Subclase de Food para pizzas
class Pizza(name: String, price: Double) : Food(name, price) {
    // Implementación de la función cook() para pizzas
    override fun cook(): String {
        return "Horneando la pizza $name"
    }
}

// Interfaz Dessert con la función eat()
interface Dessert {
    // Función abstracta que debe ser implementada por las clases que implementen la interfaz
    fun eat(): String
}

// Clase IceCream que implementa Dessert y es subclase de Food
class IceCream(name: String, price: Double) : Food(name, price), Dessert {
    // Implementación de la función cook() para helados
    override fun cook(): String {
        return "Enfriando el helado $name"
    }

    // Implementación de la función eat() de la interfaz Dessert
    override fun eat(): String {
        return "Comiendo el helado $name"
    }
}

// Clase abstracta Drink que extiende Food con una función abstracta pour()
abstract class Drink(name: String, price: Double) : Food(name, price) {
    // Función abstracta que debe ser implementada por las subclases
    abstract fun pour(): String
}

// Subclase de Drink llamada Juice
class Juice(name: String, price: Double) : Drink(name, price) {
    // Implementación de la función pour() para jugos
    override fun pour(): String {
        return "Vertiendo el jugo $name"
    }

    // Implementación de la función cook() para jugos
    override fun cook(): String {
        return "Exprimiendo el jugo $name"
    }
}

// Función de extensión para calcular el precio con descuento
fun Food.discountedPrice(discountPercentage: Double): Double {
    // Calcula el precio con descuento aplicando el porcentaje dado
    return price * (1 - discountPercentage / 100)
}
