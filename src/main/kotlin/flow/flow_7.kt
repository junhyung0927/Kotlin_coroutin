package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

suspend fun performRequest(request: Int): String {
    delay(1000)
    return "response $request"
}

//Intermediate flow operators
fun main() = runBlocking<Unit> {
    (1..3).asFlow()
        .map { request -> performRequest(request) }
        .collect { response -> println(response) }

    /*
    시퀀스와 중요한 차이점은 연산자 내부의 코드 블록이 suspend 함수를 호출할 수 있다는 것이다.
     */
}