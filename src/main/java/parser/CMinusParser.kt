package compiler.parser

import compiler.scanner.CMinusScanner
import compiler.scanner.Scanner
import compiler.scanner.ScannerMain
import java.io.*
import java.util.logging.Level
import java.util.logging.Logger

class CMinusParser
    (private var filename: String) {
    //need Scanner
    var inputFile: FileInputStream? = null
    var outFile: FileOutputStream? = null

    init {
        try {
            inputFile = FileInputStream(filename)
            outFile = FileOutputStream("$filename.output")
        } catch (ex: FileNotFoundException) {
            Logger.getLogger(ScannerMain::class.java.name).log(Level.SEVERE, null, ex)
        }

        val inFile = BufferedReader(InputStreamReader(inputFile))
        scanner = CMinusScanner(inFile)
    }

    companion object {
        var scanner : Scanner? = null
    }

    fun parse() : Program {
        val program = Program.parse()
        return program
    }

    fun printAst(program: Program) {
        program.print(fos = outFile!!)
    }
}