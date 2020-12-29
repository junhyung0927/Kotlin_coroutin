package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

//Flow exceptions
/*
    flow 수집 작업은 연산자 내부의 방출 작업 또는 코드가 예외를 던지는 경우 예외로 완료할 수 있다.
 */

fun foo21(): Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i) //emit next value
    }
}

//Collector try and catch
fun main() = runBlocking<Unit> {
    try {
        foo21().collect { value ->
            println(value)
            check(value <= 1) { "Collected $value" }
        }
    } catch (e: Throwable) {
        println("Caught $e")
    }
}

