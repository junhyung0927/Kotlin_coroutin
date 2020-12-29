package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun foo15(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        foo15()
            .conflate() //병합
            .collect { value ->
                delay(300)
                println(value)
            }
    }

    println("Collectd in $time ms")
}
