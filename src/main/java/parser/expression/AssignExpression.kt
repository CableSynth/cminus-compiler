package parser.expression

import compiler.misc.write
import lowlevel.CodeItem
import lowlevel.Function
import scanner.advanceToken
import java.io.FileOutputStream

class AssignExpression(var varExpression: VarExpression? = null, private val expression: Expression): Expression() {
    override fun genLLCode(function: Function): CodeItem {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing AssignExpr\n")
        varExpression!!.print("$spacing  ", fos)
        expression.print("$spacing  ", fos)
    }

    companion object {
        fun parse(): AssignExpression {
            advanceToken()
            val expression = Expression.parse()
            return AssignExpression(expression = expression)
        }
    }
}