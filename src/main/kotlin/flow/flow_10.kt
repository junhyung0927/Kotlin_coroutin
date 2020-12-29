package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

//Terminal flow 연산자
fun main() = runBlocking<Unit> {
    val sum = (1..5).asFlow()
        .map { it * it }
        .reduce { a,b -> a+b }

    println(sum)

    /*
    Terminal 연산자는 flow의 수집을 시작하는 suspending 함수다.
    collect는 기본 연산자이지만, 다른 terminal 연산자가 있다.
        - toList, toSet과 같은 다양한 컬렉션으로 변환 가능.
        - 연산자는 첫 번째 값을 얻고 flow가 단일 값을 방출하도록 보장한다.
        - reduce 및 fold로 flow를 어떤 값까지 Reducing 할 수 있다.
     */

    val sum2 = (1..5).asFlow()
        .filter {
            println("Filter $it")
            it % 2 == 0
        }

        .map {
            println("Map $it")
            "string $it"
        }.collect {
            println("Collect $it")
        }
    /*
    flow의 각각의 개별적인 collection은 여러 개의 flow에서 사용하는 특수한 연산자를 사용하지 않는 한 순차적으로 수행된다.
    collection은 terminal 연산자를 호출하는 코루틴에서 직접 작동한다.
    기본적으로 새로운 코루틴은 launch 되지 않으며 각각의 방출된 값은 upstream에서 downstream 까지 모든 중간 연산자에 의헤
    처리 된 후 terminal 연산자에게 전달된다.
     */

}