package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

//flowOn 연산자

fun foo13(): Flow<Int> = flow {
    for (i in 1..3){
        Thread.sleep(100)
        log("Emitting $i ")

        emit(i)
    }
}.flowOn(Dispatchers.Default) //flow builder에서 CPU가 많이 사용되는 코드의 context를 변경하는 올바른 방법

fun main() = runBlocking<Unit> {
    foo13().collect { value ->
        log("Collected $value")
    }

    /*
    flow { .. }가 백그라운드 스레드에서 작동하며 collect는 main 스레드에서 작동하는 것을 알 수 있다.
    flowOn 연산자가 flow의 기본 순차적인 특성을 변경했다는 것도 알 수 있다.
    한 코루틴에서 collect가 이루어지고 다른 스레드에서 collect 코루틴과 동시에 실행되는 다른 코루틴에선 emit이 이루어진다.
    flowOn 사용자는 context에서 CoroutineDispather를 변경해야할 때 upstream flow를 위해 또 다른 코루틴을 생성한다.
    */
}