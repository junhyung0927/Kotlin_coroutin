package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

fun foo6(): Flow<Int> = flow {
    for (i in 1..3){
        delay(100)
        println("Emitting $i")
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    withTimeoutOrNull(250) {
        foo6().collect { value -> println(value) }
    }

    println("Done")
}

