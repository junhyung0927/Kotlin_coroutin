package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

suspend fun foo3(): List<Int>{
    delay(1000)
    return listOf(1,2,3)
}

fun main() = runBlocking<Unit> {
    foo3().forEach { value -> println(value) }
}
