package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

//Buffering
/*
    flow의 다른 부분을 다른 코루틴으로 실행하는 것은 장기간 실행되는 비동기 연산이 관련된 경우,
    flow의 collect에 걸리는 전체 시간에 도움이 될 수 있다.
 */
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
    첫 번째 숫자 100ms만 기다린 다음 각 숫자를 처리하기 위해 300ms의 시간만 소요했기 때문에 동일한 숫자를 더 빨리 처리한다.
    flowOn 연산자가 CoroutineDispather를 변경할 경우 동일한 버퍼링 매커니즘을 사용함을 알아야한다.
    buffer 연산자를 사용함으로서 실행 컨텍스트의 전환없이 버퍼링을 수행했다.
     */

    println("Collected in $time ms")
}