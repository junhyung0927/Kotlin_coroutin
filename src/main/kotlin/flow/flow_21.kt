package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

fun foo21(): Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i) //emit next value
    }
}

fun main() = runBlocking<Unit> {
    try {
        foo21().collect { value ->
            println(value)
            check(value <= 1) { "Collected $value" }
        }
    } catch (e: Throwable) {
        println("Caught $e")
    }
}

