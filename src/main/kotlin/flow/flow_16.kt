package flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        foo15()
            .collectLatest { value ->
                println("Collecting $value")
                delay(300)
                println("Done $value")
            }
    }
    println("Collected in $time ms")
}