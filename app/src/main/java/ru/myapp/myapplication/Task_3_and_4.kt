package ru.myapp.myapplication

/** Задача 3 */
fun List<Any>.filterInt(): List<Int> {
    return this.filter { it is Int }.map { it as Int } // можно уместить и в одну функцию
}

// Я просто выполнял их в Intellij IDEA
fun main() {
    val list = listOf<Any>("String", 45, 2L, "dwa", false, true, "fes", 120, 932, 51)
    val intList = listOf(4, null, null, 1, 5, 8)

    println(cocktailShakerSort(intList))
    println(list.filterInt())
}

/** Задача 4 */
fun cocktailShakerSort(list: List<Int?>?): List<Int?> {
    if (list == null) return emptyList()

    val nonNulls = list.filterNotNull().toMutableList()
    val nulls = list.count { it == null }

    var swapped: Boolean
    var start = 0
    val end = nonNulls.size - 1

    do {
        swapped = false

        for (i in start until end) {
            if (nonNulls[i] > nonNulls[i + 1]) {
                nonNulls[i] = nonNulls[i + 1].also { nonNulls[i + 1] = nonNulls[i] }
                swapped = true
            }
        }
        start++
    } while (swapped)

    return nonNulls + List(nulls) { null }
}