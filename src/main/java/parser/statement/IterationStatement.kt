package compiler.parser.statement

import compiler.misc.write
import compiler.parser.expression.Expression
import compiler.scanner.Token
import compiler.scanner.advanceToken
import compiler.scanner.matchToken
import java.io.FileOutputStream

class IterationStatement(private val expression: Expression, private val statement: Statement) : Statement() {
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