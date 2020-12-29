package flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

//최근 값 처리
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

    /*
    xxxLatest 연산자들은 xxx 연산자와 동일하게 필수적인 로직은 수행하지만
    새로운 값에 대해 블록 안의 코드를 취소하는 경우가 있다.
    collectLatest 블록에서 300ms가 걸리지만 100ms마다 새로운 값이 방출되기 때문에,
    블록은 모든 값에 대해 실행되지만 마지막 값에 대해서만 완료됨을 알 수 있다.
     */
}