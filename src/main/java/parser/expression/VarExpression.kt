package parser.expression

import compiler.misc.write
import lowlevel.CodeItem
import java.io.FileOutputStream

class VarExpression(var identifier: String = ""): Expression() {
    override fun genLLCode(): CodeItem {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing VarExpr $identifier \n")
    }

    companion object {
        fun parse(): VarExpression {
            return VarExpression()
        }
    }
}