package parser.statement

import compiler.misc.write
import lowlevel.CodeItem
import parser.CMinusParser
import parser.expression.Expression
import scanner.Token
import scanner.matchToken
import java.io.FileOutputStream

class ReturnStatement(var expression: Expression? = null) : Statement() {
    override fun genLLCode(): CodeItem? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing ReturnStmt \n")
        expression?.print("$spacing  ", fos)
    }

    companion object {
        fun parse(): ReturnStatement {
            matchToken(Token.TokenType.RETURN_TOKEN)
            val expression: Expression? = if(CMinusParser.scanner!!.viewNextToken().type != Token.TokenType.SEMI_TOKEN) {
                Expression.parse()
            } else {
                null
            }
            matchToken(Token.TokenType.SEMI_TOKEN)
            return ReturnStatement(expression)
        }
    }
}