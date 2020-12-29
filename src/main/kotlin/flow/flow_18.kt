package flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

//다중 flow 구성 - Combine
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

    /*
    flow가 변수나 연산의 가장 최근 값을 나타내는 경우 해당 flow의 가장 최근 값에 따라 달라지는 계산을 수행하고
    upstream flow가 값을 방출할 때마다 이를 다시 계산해야 할 수 있다.
    해당 연산자 계열을 combine이라 한다.
     */
}