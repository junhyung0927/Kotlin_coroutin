package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

//withContext의 잘못된 배출
fun foo12(): Flow<Int> = flow {
    //flow builder에서 CPU가 많이 사용되는 코드의 context를 변경하는 잘못된 방법
    withContext(Dispatchers.Default) {
        for (i in 1..3) {
            Thread.sleep(100) //CPU 많이 사용하는 척
            emit(i) //다음 값 방출
        }
    }
}

fun main() = runBlocking<Unit> {
    foo12().collect { value -> println(value) }
}