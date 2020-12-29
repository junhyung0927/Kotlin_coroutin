package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

//모든 예외를 잡아내는 예제
fun foo22(): Flow<String> =
    flow {
        for (i in 1..3) {
            println("Emitting $i")
            emit(i)
        }
    }.map { value ->
            check(value <= 1) {"Crashed on $value"}
            "string $value"
            }

//try catch를 사용하여 예외처리
//fun main() = runBlocking<Unit> {
//    try {
//        foo22().collect { value -> println(value) }
//    } catch (e: Throwable) {
//        println("Caught $e")
//    }
//}

//예외 투명성을 지키기 위한 catch 로직에서 emit을 사용하여 값 타입으로 방출하는 방법
fun main() = runBlocking<Unit> {
    foo22()
        .catch { e -> emit("Caught $e") }
        .collect { value -> println(value) }
}
