package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

/*
    Flow Completion
    flow의 collect가 완료되었을 때 어떤 조치가 필요할 수 있다.
    이것을 명령과 선언 두 가지 방법으로 구현할 수 있다.
 */

fun foo24(): Flow<Int> = (1..3).asFlow()

//Imperative(명령형) finally block
fun main() = runBlocking<Unit> {
    try {
        foo24().collect { value -> println(value) }
    } finally {
        println("Done")
    }

    /*
    try/catch 외에도 collector는 collect 완료 시 final 블록을 사용해 작업을 실행할 수 있다.
     */
}

