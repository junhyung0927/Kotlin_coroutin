package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

fun foo6(): Flow<Int> = flow {
    for (i in 1..3){
        delay(100)
        println("Emitting $i")
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    withTimeoutOrNull(250) {
        foo6().collect { value -> println(value) }
    }
    println("Done")

    /*
    cancellable한 suspend 함수에서 flow가 suspended 상태 일 때
    flow 수집을 취소할 수 있으며, 이 이외엔 달리 취소할 수 있는 방법이 없다.
    */

}

