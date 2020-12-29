package flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun requestFlow2(i: Int): Flow<String> = flow {
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

    /*
    연쇄적인 평탄화 방식은 flatMapConcat과 flattenConcat 연산자에 의해 구현된다.
    이들은 다음 수직 작업을 시작하기 전에 내부 flow가 완료될 때까지 기다린다.
    flatMapConcat의 순차적인 특성은 출력 결과에서 명확하게 알 수 있다.
     */
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
    flatMapMerge는 유입되는 모든 flow를 동시에 수집하고 그 값을 단일 flow로 병합하여 가능한 한 빨리 값이 방출되도록 하는 방식이다.
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
    collectLatest 연산자와 유사한 방식으로, 새로운 flow가 방출되는 동시에 이전 flow의 컬렉션이 취소되는 최신 평탄화 방식.
    flatMapLatest 연산자로 구현되어 있어 새로운 플로우가 방출될 때마다 직전 플로우를 취소시킨다.
     */
}

fun main(){
//    flatMapConcatFunction()
//    flatMapMergeFunction()
    flatMapLatestFunction()
}