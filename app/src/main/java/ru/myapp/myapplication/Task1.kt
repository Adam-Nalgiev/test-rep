package ru.myapp.myapplication

fun quickSorter(arr: List<Int>): List<Int> {
    if (arr.size <= 1) return arr

    val pivot = arr[arr.size / 2]
    val left = arr.filter { it < pivot }
    val middle = arr.filter { it == pivot }
    val right = arr.filter { it > pivot }

    return quickSort(left) + middle + quickSort(right)
}