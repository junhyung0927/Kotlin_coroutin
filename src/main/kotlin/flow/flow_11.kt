package flow


import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

//Flow context preservation

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun foo11(): Flow<Int> = flow {
    log("Started foo flow")
    for (i in 1..3) {
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    foo11().collect {value -> log("Collected $value")}

/*
    flow의 collection은 항상 코루틴을 호출하는 context에서 일어난다.
    foo11.collect()는 메인 스레드에서 호출되기 때문에 foo11 flow의 body도 메인 스레드에서 호출된다.
    이는 실행되는 context에 상관하지 않고 호출자를 블록하지 않는 fast-running 또는 비동기 코드의 완벽한 기본값이다.
 */
}