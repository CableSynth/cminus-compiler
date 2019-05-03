package parser.statement

import parser.CMinusParser
import parser.expression.BinaryExpression
import parser.expression.Expression
import scanner.Token
import scanner.advanceToken
import scanner.matchToken
import lowlevel.CodeItem
import lowlevel.Function
import java.io.FileOutputStream

class ExpressionStatement(private val expr: Expression? = null): Statement() {
    override fun print(spacing: String, fos: FileOutputStream) {
        expr?.print(spacing, fos)
    }

    override fun genLLCode(function: Function): CodeItem? {
        return expr?.genLLCode()
    }

    companion object {
        fun parse(): ExpressionStatement {
            return when (CMinusParser.scanner!!.viewNextToken().type) {
                Token.TokenType.SEMI_TOKEN -> {
                    advanceToken()
                    ExpressionStatement()
                }
                Token.TokenType.NUM_TOKEN,
                Token.TokenType.ID_TOKEN-> {
                    val expression = Expression.parse()
                    matchToken(Token.TokenType.SEMI_TOKEN)
                    ExpressionStatement(expression)
                }
                Token.TokenType.LPAREN_TOKEN -> {
                    advanceToken()
                    val expression = Expression.parse()
                    matchToken(Token.TokenType.RPAREN_TOKEN)

                    val expr = when(CMinusParser.scanner!!.viewNextToken().type) {
                        Token.TokenType.SEMI_TOKEN -> {
                            expression
                        }
                        Token.TokenType.PLUS_TOKEN,
                        Token.TokenType.MINUS_TOKEN,
                        Token.TokenType.LESS_EQUAL_TOKEN,
                        Token.TokenType.LESS_THAN_TOKEN,
                        Token.TokenType.GREATER_EQUAL_TOKEN,
                        Token.TokenType.GREATER_THAN_TOKEN,
                        Token.TokenType.TIMES_TOKEN,
                        Token.TokenType.DIV_TOKEN,
                        Token.TokenType.NOT_EQUAL_TOKEN,
                        Token.TokenType.EQUAL_TOKEN -> {
                            val binaryExpression = BinaryExpression.parse()
                            if (binaryExpression.leftExpression == null) {
                                binaryExpression.leftExpression = expression
                            } else if (binaryExpression.rightExpression == null) {
                                binaryExpression.rightExpression = expression
                            }
                            binaryExpression
                        }
                        else -> {
                            throw(Exception("Failed to parse expression statement after num finding left paren "
                                    + CMinusParser.scanner!!.viewNextToken().type))
                        }
                    }
                    matchToken(Token.TokenType.SEMI_TOKEN)
                    ExpressionStatement(expr)
                }
                else -> {
                    throw(Exception("Failed to parse expression statement "
                            + CMinusParser.scanner!!.viewNextToken().type))
                }
            }
        }
    }
}