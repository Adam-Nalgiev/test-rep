package ru.myapp.myapplication

import java.util.PriorityQueue

sealed class Node(val frequency: Int) {
    data class Leaf(val char: Char, val freq: Int) : Node(freq)
    data class Internal(val left: Node, val right: Node, val freq: Int) : Node(freq)
}

typealias Codes = Map<Char, String>

data class EncodedResult(
    val encodedData: String,
    val codes: Codes,
    val tree: Node
)

class Compressor {
    private fun calculateFrequencies(text: String): Map<Char, Int> {
        return text.groupingBy { it }.eachCount()
    }

    fun buildTree(text: String): Node {
        val frequencies = calculateFrequencies(text)

        val priorityQueue = PriorityQueue<Node>(compareBy { it.frequency })

        frequencies.forEach { (char, freq) ->
            priorityQueue.add(Node.Leaf(char, freq))
        }

        while (priorityQueue.size > 1) {
            val left = priorityQueue.poll()
            val right = priorityQueue.poll()
            val internal = Node.Internal(left, right, left.frequency + right.frequency)

            priorityQueue.add(internal)
        }

        return priorityQueue.poll() ?: throw IllegalArgumentException("ПУстая строка!")
    }

    fun generateCodes(root: Node): Codes {
        val codes = mutableMapOf<Char, String>()
        generateCodesRecursive(root, "", codes)
        return codes
    }

    fun generateCodesRecursive(node: Node, code: String, codes: MutableMap<Char, String>) {
        when (node) {
            is Node.Leaf -> {
                codes[node.char] = code.ifEmpty { "0" }
            }

            is Node.Internal -> {
                generateCodesRecursive(node.left, code + "0", codes)
                generateCodesRecursive(node.right, code + "1", codes)
            }
        }
    }

    fun encode(text: String): EncodedResult {
        if (text.isEmpty()) throw IllegalArgumentException("Пустая строка!")

        val tree = buildTree(text)
        val codes = generateCodes(tree)

        val encodedBuilder = StringBuilder()

        for (char in text) {
            encodedBuilder.append(codes[char])
        }
        return EncodedResult(encodedBuilder.toString(), codes, tree)
    }

}