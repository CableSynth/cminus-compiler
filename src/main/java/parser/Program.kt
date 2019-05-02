package parser

import parser.CMinusParser.Companion.scanner
import parser.declaration.Declaration
import compiler.scanner.Token
import lowlevel.CodeItem
import lowlevel.Function
import java.io.FileOutputStream

class Program {

    var declList: ArrayList<Declaration> = ArrayList()

    companion object {
        fun parse(): Program {
            val program = Program()

            var declaration = Declaration.parse()
            program.declList.add(declaration)

            while ( scanner!!.viewNextToken().type != Token.TokenType.EOF_TOKEN &&
                    scanner!!.viewNextToken().type != Token.TokenType.ERROR_TOKEN) {

                declaration = Declaration.parse()
                program.declList.add(declaration)
            }
            return program
        }
    }

    fun genLLCode(): CodeItem {
        return Function(1, "test")
    }

    fun print(spacing: String = "", fos: FileOutputStream) {
        fos.write("Program {\n".toByteArray())
        for (decl in declList) {
            decl.print("$spacing  ", fos)
        }
        fos.write("}\n".toByteArray())
    }
}

