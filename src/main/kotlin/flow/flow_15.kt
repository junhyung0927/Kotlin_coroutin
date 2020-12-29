package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

//Conflation

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
    /*
    flow가 실행 또는 상태 업데이트의 부분적인 결과를 나타내는 경우,
    각 값을 처리할 필요가 없고 가장 최근의 값만 처리할 필요가 있을 수 있다.
    이 경우, conflate 연산자를 사용하여 collector가 너무 느려 중간 값을 처리할 수 없을때 중간 값을 건너 뛸 수 있다.
     */
}
