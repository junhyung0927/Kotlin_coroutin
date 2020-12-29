package flow

fun foo2(): Sequence<Int> = sequence {
    for(i in 1..3) {
        Thread.sleep(100) //pretend we are computing it
        yield(i) //yield next value
    }
}

fun main(){
    foo2().forEach { value -> println(value) }
}