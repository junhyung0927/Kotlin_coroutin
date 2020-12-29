package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

fun foo27(): Flow<Int> = (1..3).asFlow()

//Upstream exceptions only
fun main() = runBlocking<Unit> {
    foo27()
        .onCompletion { cause -> println("Flow completed with $cause") }
        .collect { value ->
            check(value <= 1) { "Collected $value" }
            println(value)
        }
}