package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

suspend fun performRequest(request: Int): String {
    delay(1000L)
    return "response $request"
}

fun main() = runBlocking<Unit> {
    (1..3).asFlow()
        .map { request -> performRequest(request) }
        .collect { response -> println(response) }
}