package compiler.parser.expression

import compiler.misc.write
import java.io.FileOutputStream

class VarExpression(var identifier: String = ""): Expression() {
    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing VarExpr $identifier \n")
    }

    companion object {
        fun parse(): VarExpression {
            return VarExpression()
        }
    }
}