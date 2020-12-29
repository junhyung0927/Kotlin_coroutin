package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

fun foo12(): Flow<Int> = flow {
    withContext(Dispatchers.Default) {
        for (i in 1..3) {
            Thread.sleep(100)
            emit(i)
        }
    }
}

fun main() = runBlocking<Unit> {
    foo12().collect { value -> println(value) }
}