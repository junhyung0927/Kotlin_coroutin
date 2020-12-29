package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

suspend fun performRequest2(request: Int): String {
    delay(1000)
    return "response $request"
}

//Transform operator
fun main() = runBlocking<Unit> {
    (1..3).asFlow()
        .transform { request ->
            emit("Making request $request")
            emit(performRequest2(request))
        }
        .collect { response -> println(response) }
    /*
    transform 변환은 map, filter와 같은 단순한 변환을 따라하고, 복잡한 변환을 구현하는데 사용된다.
    transform 연산자를 사용하여 임의의 값을 임의의 횟수로 방출할 수 있다.
     */
}