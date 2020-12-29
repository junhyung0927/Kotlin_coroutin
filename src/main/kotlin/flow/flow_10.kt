package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val sum = (1..5).asFlow()
        .map { it * it }
        .reduce { a,b -> a+b }

    println(sum)

    val sum2 = (1..5).asFlow()
        .filter {
            println("Filter $it")
            it % 2 == 0
        }

        .map {
            println("Map $it")
            "string $it"
        }.collect {
            println("Collect $it")
        }
}