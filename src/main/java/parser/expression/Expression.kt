package parser.expression

import parser.CMinusParser
import scanner.Token
import scanner.advanceToken
import lowlevel.CodeItem
import lowlevel.Function
import java.io.FileOutputStream

abstract class Expression {
    abstract fun print(spacing: String, fos: FileOutputStream)

    abstract fun genLLCode(function: Function): Int

    companion object {
        fun parse(): Expression {
            return when (CMinusParser.scanner!!.viewNextToken().type) {
                Token.TokenType.NUM_TOKEN -> {
                    val numberToken = advanceToken()
                    when (CMinusParser.scanner!!.viewNextToken().type) {
                        Token.TokenType.RPAREN_TOKEN,
                        Token.TokenType.SEMI_TOKEN,
                        Token.TokenType.COMMA_TOKEN -> {
                            val numberExpression = NumberExpression.parse()
                            numberExpression.number = numberToken.data as Int
                            numberExpression
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
                            val numberExpression = NumberExpression.parse()
                            numberExpression.number = numberToken.data as Int

                            var binaryExpression = BinaryExpression.parse()

                            if (binaryExpression.leftExpression == null) {
                                binaryExpression.leftExpression = numberExpression
                            } else if (binaryExpression.rightExpression == null) {
                                binaryExpression.rightExpression = numberExpression
                            }

                            if (binaryExpression.rightExpression is BinaryExpression &&
                                (binaryExpression.rightExpression as BinaryExpression).binopToken != Token.TokenType.TIMES_TOKEN &&
                                (binaryExpression.rightExpression as BinaryExpression).binopToken != Token.TokenType.DIV_TOKEN) {

                                val rightOp = (binaryExpression.rightExpression as BinaryExpression).leftExpression
                                val lateOp = (binaryExpression.rightExpression as BinaryExpression).rightExpression
                                binaryExpression = BinaryExpression(
                                    (binaryExpression.rightExpression as BinaryExpression).binopToken, leftExpression =
                                    BinaryExpression(
                                        binaryExpression.binopToken,
                                        leftExpression = numberExpression,
                                        rightExpression = rightOp
                                    ),
                                    rightExpression = lateOp
                                )
                            }

                            binaryExpression

                        }
                        else -> {
                            throw(Exception(
                                "Failed to parse expression after num finding num token "
                                        + CMinusParser.scanner!!.viewNextToken().type
                            ))
                        }
                    }
                }
                Token.TokenType.ID_TOKEN -> {
                    val idToken = advanceToken()
                    when (CMinusParser.scanner!!.viewNextToken().type) {
                        Token.TokenType.RPAREN_TOKEN,
                        Token.TokenType.SEMI_TOKEN,
                        Token.TokenType.COMMA_TOKEN -> {
                            val varExpression = VarExpression.parse()
                            varExpression.identifier = idToken.data.toString()
                            varExpression
                        }
                        Token.TokenType.LPAREN_TOKEN -> {
                            val callExpression = CallExpression.parse()
                            callExpression.identifier = idToken.data.toString()
                            callExpression
                        }
                        Token.TokenType.ASSIGN_TOKEN -> {
                            val varExpression = VarExpression.parse()
                            varExpression.identifier = idToken.data.toString()

                            val assignExpression = AssignExpression.parse()
                            assignExpression.varExpression = varExpression
                            assignExpression

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
                            val varExpression = VarExpression.parse()
                            varExpression.identifier = idToken.data.toString()

                            var binaryExpression = BinaryExpression.parse()
                            if (binaryExpression.leftExpression == null) {
                                binaryExpression.leftExpression = varExpression
                            } else if (binaryExpression.rightExpression == null) {
                                binaryExpression.rightExpression = varExpression
                            } else {
                                val lastOp = (binaryExpression.rightExpression as BinaryExpression).rightExpression
                                val opType = (binaryExpression.rightExpression as BinaryExpression).binopToken
                                binaryExpression.rightExpression = varExpression
                                binaryExpression = BinaryExpression(opType, binaryExpression, lastOp)
                            }

                            if (binaryExpression.rightExpression is BinaryExpression &&
                                (binaryExpression.rightExpression as BinaryExpression).binopToken != Token.TokenType.TIMES_TOKEN &&
                                (binaryExpression.rightExpression as BinaryExpression).binopToken != Token.TokenType.DIV_TOKEN) {

                                val rightOp = (binaryExpression.rightExpression as BinaryExpression).leftExpression
                                val lateOp = (binaryExpression.rightExpression as BinaryExpression).rightExpression
                                binaryExpression = BinaryExpression(
                                    (binaryExpression.rightExpression as BinaryExpression).binopToken, leftExpression =
                                    BinaryExpression(
                                        binaryExpression.binopToken,
                                        leftExpression = varExpression,
                                        rightExpression = rightOp
                                    ),
                                    rightExpression = lateOp
                                )
                            }

                            binaryExpression
                        }
                        else -> {
                            throw(Exception(
                                "Failed to parse expression after num finding void token "
                                        + CMinusParser.scanner!!.viewNextToken().type
                            ))
                        }
                    }
                }
                else -> {
                    throw(Exception(
                        "Failed to parse expression "
                                + CMinusParser.scanner!!.viewNextToken().type
                    ))
                }
            }
        }
    }
}