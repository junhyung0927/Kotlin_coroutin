package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

//Declarative(선언적) handling

fun foo25(): Flow<Int> = (1..3).asFlow()

fun main() = runBlocking<Unit> {
    foo25()
        .onCompletion { println("Done") }
        .collect { value -> println(value) }

    /*
    선언적인 접근으로는 플로우에 onCompletion 중간 연산자를 추가해서
    플로우가 완전히 수집되었을 때 실행 될 로직을 정의할 수 있다.
     */
}