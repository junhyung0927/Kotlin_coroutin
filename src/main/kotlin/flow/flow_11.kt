package flow


import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

fun foo11(): Flow<Int> = flow {
    println("Started foo flow")
    for (i in 1..3) {
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    foo11().collect {value -> println("Collected $value")}
}