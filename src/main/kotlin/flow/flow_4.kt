package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun foo4(): Flow<Int> = flow{
    for(i in 1..3) {
        delay(100)
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    launch {
        for(k in 1..3) {
            println("I'm not blocked $k")
            delay(100)
        }
    }

    foo4().collect { value -> println(value) }
}