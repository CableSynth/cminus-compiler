package compiler.parser.statement

import compiler.misc.write
import compiler.parser.CMinusParser
import compiler.parser.expression.Expression
import compiler.scanner.Token
import compiler.scanner.advanceToken
import compiler.scanner.matchToken
import java.io.FileOutputStream

class SelectionStatement(private var expression : Expression, private var thenStatement : Statement? = null, private var elseStatement: Statement? = null) :
    Statement() {

    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing SelectionStmt \n")
        expression.print("$spacing  ", fos)
        thenStatement?.print("$spacing  ", fos)
        elseStatement?.print("$spacing  ", fos)
    }
    companion object {
        fun parse(): SelectionStatement {
            advanceToken()
            matchToken(Token.TokenType.LPAREN_TOKEN)
            val expression = Expression.parse()
            matchToken(Token.TokenType.RPAREN_TOKEN)
            val thenStatement = Statement.parse()
            var elseStatement: Statement? = null
            if (CMinusParser.scanner!!.viewNextToken().type == Token.TokenType.ELSE_TOKEN) {
                advanceToken()
                elseStatement = Statement.parse()
            }
            return SelectionStatement(expression, thenStatement, elseStatement)
        }
    }
}