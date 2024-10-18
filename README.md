# Using Flow and StateFlow with MVVM project

## lab 1 

```Kotlin


fun getNumbers() = flow<Int> {
    for(i in 1..10){
        delay(1000)
        emit(i)
    }
}


suspend fun main(): Unit = coroutineScope {

    val stateFlow = MutableStateFlow("")

    launch { stateFlow.collect{
        println(it)
    }
    }

    stateFlow.value = "1"
    stateFlow.value = "2"
    stateFlow.value = "3"
    stateFlow.value = "4"
    stateFlow.value = "5"
    stateFlow.value = "6"
    stateFlow.value = "7"




    getNumbers()
        .filter { item -> item > 5 }
        .map { item -> item +5 }
        .collect{
            println("the number is : $it")
        }


Thread.sleep(100)
}


```

## Lab 2 

```Kotlin


fun getEvenNumbers() = flow {
    for (i in 0..40 step 2) {
        delay(1000)
        emit(i)
    }
}

suspend fun main(): Unit = coroutineScope {

    getEvenNumbers()
        .take(10)
        .collect { value ->
        println(value)

    }
}

```

## lab 5

• The Difference between Collect and CollectLatest

Collect will collect every value , and CollectLatest will stop current work to collect latest value,

The crucial difference from collect is that when the original flow emits a new value then the action block for the previous value is cancelled.


```

flow {
    emit(1)
    delay(50)
    emit(2)
}.collect { value ->
    println("Collecting $value")
    delay(100) // Emulate work
    println("$value collected")
}


```

prints "Collecting 1, 1 collected, Collecting 2, 2 collected"

```
flow {
    emit(1)
    delay(50)
    emit(2)
}.collectLatest { value ->
    println("Collecting $value")
    delay(100) // Emulate work
    println("$value collected")
}
```

prints "Collecting 1, Collecting 2, 2 collected"

