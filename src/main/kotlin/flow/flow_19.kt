package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

//Flattening flows
fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500)
    emit("$i: Second")
}

fun main()= runBlocking<Unit> {
    (1..3).asFlow().map { requestFlow(it) }

    
}

