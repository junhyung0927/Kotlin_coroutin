package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

fun foo13(): Flow<Int> = flow {
    for (i in 1..3){
        Thread.sleep(100)
        println("Emitting $i ")

        emit(i)
    }
}.flowOn(Dispatchers.Default)

fun main() = runBlocking<Unit> {
    foo13().collect { value ->
        println("Collected $value")
    }
}