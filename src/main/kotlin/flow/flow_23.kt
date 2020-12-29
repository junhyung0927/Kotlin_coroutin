package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

fun foo23(): Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    foo23()
        .catch { e ->println("Caught $e") } //does not catch downsteam exceptions
        .collect { value ->
            check(value <= 1) { "Collected $value" }
            println(value)
        }
    /*
    catch 중간 연산자는 오직 업 스트림에서 발생하는 예외(catch 연산자 위의 모든 연산자)들에 대해서만
    동작하며 다운 스트림에서 발생한 예외에 대해서는 처리하지 않는다.
     */
}