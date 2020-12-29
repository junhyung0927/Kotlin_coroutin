package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

fun foo24(): Flow<Int> = (1..3).asFlow()

//Imperative(명령형) finally block
fun main() = runBlocking<Unit> {
    try {
        foo24().collect { value -> println(value) }
    } finally {
        println("Done")
    }

    /*
    try/catch에 추가적으로 수집 종료 시 실행할 코드를 finally 블록을 통해 정의할 수 있다.
     */
}

