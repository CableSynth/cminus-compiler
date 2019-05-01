package compiler.parser.statement

import compiler.parser.CMinusParser
import compiler.parser.expression.Expression
import compiler.scanner.Token
import java.io.FileOutputStream

abstract class Statement {
    abstract fun print(spacing: String, fos: FileOutputStream)

    companion object {
        fun parse(): Statement {
            val statement = when (CMinusParser.scanner!!.viewNextToken().type) {
                Token.TokenType.SEMI_TOKEN,
                Token.TokenType.NUM_TOKEN,
                Token.TokenType.LPAREN_TOKEN,
                Token.TokenType.ID_TOKEN -> {
                    ExpressionStatement.parse()
                }
                Token.TokenType.IF_TOKEN -> {
                    SelectionStatement.parse()
                }
                Token.TokenType.WHILE_TOKEN -> {
                    IterationStatement.parse()
                }
                Token.TokenType.RETURN_TOKEN -> {
                    ReturnStatement.parse()
                }
                Token.TokenType.LCURLY_TOKEN -> {
                    CompoundStatement.parse()
                }
                else -> {
                    throw(Exception("Failed to parse statement "
                            + CMinusParser.scanner!!.viewNextToken().type))
                }
            }

            return statement
        }
    }
}