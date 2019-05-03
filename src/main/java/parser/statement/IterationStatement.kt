package parser.statement

import compiler.misc.write
import lowlevel.CodeItem
import lowlevel.Function
import parser.expression.Expression
import scanner.Token
import scanner.advanceToken
import scanner.matchToken
import java.io.FileOutputStream

class IterationStatement(private val expression: Expression, private val statement: Statement) : Statement() {
    override fun genLLCode(function: Function): CodeItem? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing IterationStmt \n")
        expression.print("$spacing  ", fos)
        statement.print("$spacing  ", fos)
    }

    companion object {
        fun parse(): IterationStatement {
            advanceToken()
            matchToken(Token.TokenType.LPAREN_TOKEN)
            val expression = Expression.parse()
            matchToken(Token.TokenType.RPAREN_TOKEN)
            val statement = Statement.parse()
            return IterationStatement(expression, statement)
        }
    }
}