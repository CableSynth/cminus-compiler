import java.io.*

interface Compiler {
    @Throws(IOException::class)
    fun compile(filePrefix: String)
}
