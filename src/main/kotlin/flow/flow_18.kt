package flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() {
    zipCurrentTimeMillis()
    combineCurrentTimeMillis()
    /*
    해당 예제에서 onEach 중간 연산자를 사용하여 각각의 항목들을 지연시킴으로서 방출 코드를 더 가독성 있고 간결하게 만들 수 있다.
     */
}

fun zipCurrentTimeMillis() = runBlocking {
    val nums = (1..3).asFlow().onEach { delay(300) }
    val strs = flowOf("one", "two", "three").onEach { delay(400) }
    val startTime = System.currentTimeMillis()

    nums.zip(strs) { a,b -> "$a -> $b"}
        .collect { value ->
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}

fun combineCurrentTimeMillis() = runBlocking<Unit> {
    val nums = (1..3).asFlow().onEach { delay(300) }
    val strs = flowOf("one", "two", "three").onEach { delay(400) }
    val startTime = System.currentTimeMillis()

    nums.combine(strs) { a,b -> "$a -> $b" }
        .collect { value ->
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}