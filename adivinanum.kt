import java.io.File
import kotlin.random.Random

// Códigos de color de fondo
const val BG_BLACK = "\u001B[40m"
const val BG_RED = "\u001B[41m"
const val BG_GREEN = "\u001B[42m"
const val BG_YELLOW = "\u001B[43m"
const val BG_BLUE = "\u001B[44m"
const val BG_PURPLE = "\u001B[45m"
const val BG_CYAN = "\u001B[46m"
const val BG_WHITE = "\u001B[47m"
// Colores ANSI básicos
const val RESET = "\u001B[0m"
const val BLACK = "\u001B[30m"
const val RED = "\u001B[31m"
const val GREEN = "\u001B[32m"
const val YELLOW = "\u001B[33m"
const val BLUE = "\u001B[34m"
const val PURPLE = "\u001B[35m"
const val CYAN = "\u001B[36m"
const val WHITE = "\u001B[37m"
const val BOLD = "\u001B[1m"
const val UNDERLINE = "\u001B[4m"

fun main() {
    var juega = true

    while (juega) {
        println("")
        println("${GREEN}1. Jugar ")
        println("${GREEN}2. Ultimo intento ")
        println("${GREEN}3. Salir ")
        println("${RESET} ")
        println("")

        print("Elige tu opción --> ")
        val opcion = readln().toInt()

        when (opcion) {
            1 -> {
                println("Vamos a jugar!")
                println("Introduce un número de 4 cifras que no se repitan desde 1 hasta el 6. (Tienes 5 intentos) ")
                println(" ")

                // Variables
                val aleatorio = (1..6).toList().shuffled().take(4)
                val randomNumber = aleatorio.joinToString("")
                var intentos = 0
                var adivino = false

                // Bucle que se repite hasta que se adivine o se acaben los intentos
                while (!adivino && intentos < 5) {
                    intentos++

                    print("Intento número $intentos: ")
                    val minumero = readln()
                    var naranja = 0 // Se encuentra pero en una posición incorrecta
                    var verde = 0 // Está en la posición correcta

                    for (i in minumero.indices) {
                        if (minumero[i] == randomNumber[i]) {
                            verde++
                        } else if (minumero[i] in randomNumber) {
                            naranja++
                        }
                    }
                    print("$minumero  ${BG_YELLOW}$naranja ${BG_GREEN}$verde ${RESET}")
                    println(" ")

                    // Comprueba si el número fue adivinado o si te quedaste sin intentos
                    if (randomNumber == minumero) {
                        println("${BG_GREEN}${BLACK}¡Ganaste en $intentos intentos! El número era $randomNumber")
                        adivino = true
                        val file = File("resultado.txt")
                        file.appendText("Resultado: Ganaste! en $intentos intentos.\n")
                    } else if (intentos == 5) {
                        println(" ")
                        println("${BG_RED}${BLACK}¡Perdiste! El número era $randomNumber${RESET}")
                        val file = File("resultado.txt")
                        file.appendText("Resultado: Perdiste en $intentos intentos.\n")
                    }
                }
            }

            2 -> { //ultimos intentos
                val leerintentos = File("resultado.txt").readLines()
                println(leerintentos)
            }

            3 -> { // Opción 3: Salir
                println("¡Adiós!")
                juega = false
            }

        }
    }
}
