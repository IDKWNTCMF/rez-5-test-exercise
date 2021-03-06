import java.io.File

class FileFormatException : Exception("Wrong file format!")

fun main() {
    try {
        val input = File("tmp.txt").readLines()
        val parsedInput: List<List<String>> = input.map { str -> str.split(" ", "\t").filter { it.isNotEmpty() } }
        val refs: MutableMap<String, String> = mutableMapOf()
        parsedInput.forEach { lst ->
            if (lst.size >= 4 && lst[0].isNotEmpty() && lst[0][0] == '#') {
                refs[lst[0]] = lst[3]
            }
        }
        parsedInput.forEach { lst ->
            if (lst.size >= 3 && lst[1] == "invokevirtual") {
                val refToClass = refs[lst[2]]?.split(".")?.get(0) ?: throw FileFormatException()
                val refToClassName = refs[refToClass] ?: throw FileFormatException()
                val className = refs[refToClassName] ?: throw FileFormatException()
                println(className)
            }
        }
    } catch (e : FileFormatException) {
        println(e)
    }
}
