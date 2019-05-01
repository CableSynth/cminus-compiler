package compiler.scanner

import compiler.scanner.Token.TokenType
import java.io.*
import java.util.logging.Level
import java.util.logging.Logger


object ScannerMain {
    @JvmStatic fun main(args: Array<String>) {
        var outFile: FileOutputStream? = null
        var inputFile: FileInputStream? = null

        try {
            outFile = FileOutputStream("output\\outputTestTokens.txt")
            inputFile = FileInputStream("test\\scannerTokenTypeTestFile.txt")
        } catch (ex: FileNotFoundException) {
            Logger.getLogger(ScannerMain::class.java.name).log(Level.SEVERE, null, ex)
        }

        if (inputFile != null) {
            val inFile = BufferedReader(InputStreamReader(inputFile))
            val testScanner = CMinusScanner(inFile)
            var token = testScanner.viewNextToken()

            while (token.type != TokenType.EOF_TOKEN) {
                token = testScanner.getNextToken()

                try {
                    writeTokenToFile(outFile!!, token)
                } catch (ex: IOException) {
                    Logger.getLogger(ScannerMain::class.java.name).log(Level.SEVERE, null, ex)
                }

            }

            try {
                outFile!!.flush()

                inputFile.close()
                outFile.close()
            } catch (ex: IOException) {
                Logger.getLogger(ScannerMain::class.java.name).log(Level.SEVERE, null, ex)
            }

        } else {
            System.out.println("Could not find input file.")
        }

        try {
            outFile = FileOutputStream("output\\TestProgramScannerOut.txt")
            inputFile = FileInputStream("test\\TestProgram.txt")
        } catch (ex: FileNotFoundException) {
            Logger.getLogger(ScannerMain::class.java.name).log(Level.SEVERE, null, ex)
        }

        if (inputFile != null) {
            val inFile = BufferedReader(InputStreamReader(inputFile))

            val testScanner = CMinusScanner(inFile)
            var token = testScanner.viewNextToken()

            while (token.type != TokenType.EOF_TOKEN) {
                token = testScanner.getNextToken()

                try {
                    writeTokenToFile(outFile!!, token)
                } catch (ex: IOException) {
                    Logger.getLogger(ScannerMain::class.java.name).log(Level.SEVERE, null, ex)
                }

            }

            try {
                outFile!!.flush()

                inputFile.close()
                outFile.close()
            } catch (ex: IOException) {
                Logger.getLogger(ScannerMain::class.java.name).log(Level.SEVERE, null, ex)
            }

        } else {
            System.out.println("Could not find input file.")
        }

    }

    @Throws(IOException::class)
    fun writeTokenToFile(fos: FileOutputStream, token: Token) {
        val line = token.type.toString() + " " + token.data.toString() + "\n"
        fos.write(line.toByteArray())
    }
    fun scanall(){

    }
}
