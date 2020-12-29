package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

//Successful completion
/*
    catch 연산자와의 또 다른 차이점은 onCompletion은 모든 예외를 확인하고(취소 또는 실패 없이)
    upstream flow가 성공적으로 완료될 때만 null 예외를 받는다는 것이다.
 */
fun foo27(): Flow<Int> = (1..3).asFlow()

//Upstream exceptions only
fun main() = runBlocking<Unit> {
    foo27()
        .onCompletion { cause -> println("Flow completed with $cause") }
        .collect { value ->
            check(value <= 1) { "Collected $value" }
            println(value)
        }
    /*
    downstream의 예외로 인해 flow가 중단되었기 떄문에 완료 원인이 null이 아님을 알 수 있다.
     */
}