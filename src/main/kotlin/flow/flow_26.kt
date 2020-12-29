package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*
import java.lang.RuntimeException

//Declarative(선언적) handling
fun foo26(): Flow<Int> = flow {
    emit(1)
    throw RuntimeException()
}

fun main() = runBlocking<Unit> {
    foo26()
        .onCompletion { cause -> if (cause != null) println("Flow completed exceptionally")}
        .catch { cause -> println("Caught exception") }
        .collect { value -> println("value") }
}
