package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

fun requestFlow(i: Int): Flow<String> = flow {
    //플로우 플래트닝
    emit("$i: First")
    delay(500)
    emit("$i: Second")
}

fun main()= runBlocking<Unit> {
    (1..3).asFlow().map { requestFlow(it) }

}

