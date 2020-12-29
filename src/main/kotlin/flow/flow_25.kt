package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

//Declarative(선언적) handling

/*
 선언적 접근방식의 경우, flow가 완전히 collect 되었을 때 호출되는 onCompletion이라는 중간 연산자를 가지고 있다.

 선언적인 접근으로는 플로우에 onCompletion 중간 연산자를 추가해서 플로우가 완전히 수집되었을 때 실행 될 로직을 정의할 수 있다.

 onCompletion을 사용함으로서 얻을 수 있는 최대 이점은 람다에 nullable로 정의되는 Throwable 파라미터를 통해
 플로우 수집이 성공적으로 종료되었는지 혹은 예외가 발생되었는지 알 수 있다는 것이다.
*/

fun foo25(): Flow<Int> = (1..3).asFlow()

fun main() = runBlocking<Unit> {
    foo25()
        .onCompletion { println("Done") }
        .collect { value -> println(value) }
}