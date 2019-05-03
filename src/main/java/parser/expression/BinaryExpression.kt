package parser.expression

import compiler.misc.write
import lowlevel.CodeItem
import lowlevel.Function
import scanner.Token
import scanner.advanceToken
import scanner.matchToken
import scanner.viewNextToken
import java.io.FileOutputStream

class BinaryExpression(val binopToken: Token.TokenType? = null, var leftExpression: Expression? = null,
                       var rightExpression: Expression? = null) : Expression() {
    override fun genLLCode(function: Function): CodeItem {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing BinaryExp $binopToken\n")
        leftExpression?.print("$spacing  ", fos)
        rightExpression?.print("$spacing  ", fos)
    }

    companion object {
        fun parse(): BinaryExpression {
            val binopToken = advanceToken()
            val expression: Expression
            return if (viewNextToken().type == Token.TokenType.LPAREN_TOKEN) {
                matchToken(Token.TokenType.LPAREN_TOKEN)
                expression = Expression.parse()
                matchToken(Token.TokenType.RPAREN_TOKEN)

                var binaryExpression = BinaryExpression(binopToken.type, leftExpression = expression)
                if (viewNextToken().type != Token.TokenType.SEMI_TOKEN) {
                    val extraExpression = parse()
                    binaryExpression = BinaryExpression(binopToken.type, leftExpression = expression, rightExpression = extraExpression)
                }

                binaryExpression
            } else {
                expression = Expression.parse()
                if (expression is BinaryExpression) {
                    if (expression.binopToken == Token.TokenType.TIMES_TOKEN ||
                        expression.binopToken == Token.TokenType.DIV_TOKEN) {
                        BinaryExpression(binopToken.type, leftExpression = expression)
                    } else {
                        BinaryExpression(binopToken.type, rightExpression = expression)
                    }
                } else {
                    BinaryExpression(binopToken.type, rightExpression = expression)
                }
            }
        }
    }
}