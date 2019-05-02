package parser

import scanner.Token
import parser.CMinusParser.Companion.scanner
import parser.declaration.Declaration
import lowlevel.CodeItem
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
        val codeItem: CodeItem = declList[0].genLLCode()
        var currentItem = codeItem

        var nextItem: CodeItem
        for (item in 1 until declList.size) {
            nextItem = declList[item].genLLCode()
            currentItem.nextItem = nextItem
            currentItem = nextItem
        }

        return codeItem
    }

    fun print(spacing: String = "", fos: FileOutputStream) {
        fos.write("Program {\n".toByteArray())
        for (decl in declList) {
            decl.print("$spacing  ", fos)
        }
        fos.write("}\n".toByteArray())
    }
}

