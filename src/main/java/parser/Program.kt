package compiler.parser

import compiler.parser.CMinusParser.Companion.scanner
import compiler.parser.declaration.Declaration
import compiler.scanner.Token
import java.io.FileOutputStream

class Program {

    var declList: ArrayList<Declaration> = ArrayList()

    companion object {
        fun parse(): Program {
            val program = Program()

            while ( scanner!!.viewNextToken().type != Token.TokenType.EOF_TOKEN &&
                    scanner!!.viewNextToken().type != Token.TokenType.ERROR_TOKEN) {

                val declaration = Declaration.parse()
                program.declList.add(declaration)
            }
            return program
        }
    }

    fun print(spacing: String = "", fos: FileOutputStream) {
        fos.write("Program {\n".toByteArray())
        for (decl in declList) {
            decl.print("$spacing  ", fos)
        }
        fos.write("}\n".toByteArray())
    }
}

