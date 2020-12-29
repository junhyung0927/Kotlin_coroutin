package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

suspend fun performRequest2(request: Int): String {
    delay(1000L)
    return "response $request"
}

fun main() = runBlocking<Unit> {
    (1..3).asFlow()
        .transform { request ->
            emit("Making request $request")
            emit(performRequest2(request))
        }
        .collect { response -> println(response) }
}