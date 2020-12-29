package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*
import java.lang.RuntimeException

//Declarative(선언적) handling
fun foo26(): Flow<Int> = flow {
    emit(1)
    throw RuntimeException()
}

fun main() = runBlocking<Unit> {
    foo26()
        .onCompletion { cause -> if (cause != null) println("Flow completed exceptionally")}
        .catch { cause -> println("Caught exception") }
        .collect { value -> println("value") }

    /*
    onCompletion을 사용함으로서 얻을 수 있는 최대 이점은 람다에 nullable로 정의되는 Throwable 파라미터를 통해
    플로우 수집이 성공적으로 종료되었는지 혹은 예외가 발생되었는지 알 수 있다는 것이다.
     */
}
