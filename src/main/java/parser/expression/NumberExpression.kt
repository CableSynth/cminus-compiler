package parser.expression

import compiler.misc.write
import lowlevel.CodeItem
import java.io.FileOutputStream

class NumberExpression(var number: Int = 0): Expression() {
    override fun genLLCode(): CodeItem {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing NumExpr $number \n")
    }

    companion object {
        fun parse(): NumberExpression {
            return NumberExpression()
        }
    }
}