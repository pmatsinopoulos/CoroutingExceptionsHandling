/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.panosmatsinopoulos.coroutineexceptionhandling

import kotlinx.coroutines.*

fun log(message: String) {
    println("[${Thread.currentThread().name}] $message")
}

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    runBlocking {
        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
            log("CoroutineExceptionHandler got exception $throwable with coroutineContext $coroutineContext")
        }
        supervisorScope {
            launch(handler) {
                log("The child throws an exception")
                throw AssertionError()
            }
            log("The scope is completing")
        }
        println("The scope is completed")
    }
}
