package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

fun events(): Flow<Int> = (1..3).asFlow().onEach { delay(100) }

//fun main() = runBlocking<Unit> {
//    events()
//        .onEach { event -> println("Event: $event") }
//        .collect()
//    println("Done")
//
//    /*
//    onEach 연산자는 중간 연산자이기 떄문에 플로우 수집 시작을 위해서 종단 연산자가 필요하다.
//    collect 종단 연산자를 사용하면 그 이후 코드는 플로우가 수집될 때까지 대기하게 될 것이다.
//     */
//}

fun main() = runBlocking<Unit> {
    events()
        .onEach { event -> println("Event: $event") }
        .launchIn(this) // <-- Launching the flow in a separate coroutine
    println("Done")

    /*
    launchIn 종단 연산자를 사용하면 플로우의 수집을 다른 코루틴에서 수행할 수 있으며
    이를 통해 이후에 작성된 코드들이 곧바로 실행되도록 할 수 있다.
     */
}