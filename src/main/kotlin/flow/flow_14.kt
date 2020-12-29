package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun foo14(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
//    val time = measureTimeMillis {
//        foo14().collect { value ->
//            delay(300)
//            println(value)
//        }
//    }

    val time = measureTimeMillis {
        foo14()
            .buffer()
            .collect { value ->
                delay(300)
                println(value)
            }
    }
    /*
    flowOn 연산자가 코루틴 디스패처를 변경할 경우 동일한 버퍼링 매커니즘을 사용함을 알아야한다.
    buffer 연산자를 사용함으로서 실행 컨텍스트의 전환없이 버퍼링을 수행했다.
     */
    println("Collected in $time ms")
}