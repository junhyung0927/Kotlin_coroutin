package flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun requestFlow2(i: Int): Flow<String> = flow {
    //플로우 플래트닝
    emit("$i: First")
    delay(500)
    emit("$i: Second")
}
//flatMapConcat
fun flatMapConcatFunction() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
        .flatMapConcat { requestFlow2(it) }
        .collect { value ->
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}

//flatMapMerge
fun flatMapMergeFunction() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
        .flatMapMerge { requestFlow2(it) }
        .collect { value ->
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }

    /*
    출력 결과를 통해 flatMapMerge가 코드 블록을 순차적으로 호출하지만, 결과를 보면 플로우들은 동시에 수집한다는 것을 알 수 있다.
    순차적으로 map {requestFlow(it)}를 호출하고, 그 결과에 flattenMerge를 호출하는 것과 동일하다.
    */
}

//flatMapLatest
fun flatMapLatestFunction() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
        .flatMapLatest { requestFlow2(it) }
        .collect { value ->
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
    /*
    flatMapLatest 연산자로 구현되어 있어 새로운 플로우가 방출될 때마다 직전 플로우를 취소시킨다.
     */
}

fun main(){
//    flatMapConcatFunction()
//    flatMapMergeFunction()
    flatMapLatestFunction()
}