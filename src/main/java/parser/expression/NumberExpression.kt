package compiler.parser.expression

import compiler.misc.write
import java.io.FileOutputStream

class NumberExpression(var number: Int = 0): Expression() {
    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing NumExpr $number \n")
    }

    companion object {
        fun parse(): NumberExpression {
            return NumberExpression()
        }
    }
}