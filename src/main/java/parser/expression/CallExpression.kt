package compiler.parser.expression

import compiler.misc.write
import compiler.parser.CMinusParser
import compiler.scanner.Token
import compiler.scanner.advanceToken
import compiler.scanner.matchToken
import java.io.FileOutputStream

class CallExpression(var identifier: String = "", var args: ArrayList<Expression> = ArrayList()):Expression() {
    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing CallExpr $identifier\n")
        for (arg in args) {
            arg.print("$spacing  ", fos)
        }
    }

    companion object {
        fun parse(): CallExpression {
            advanceToken()

            val args = ArrayList<Expression>()
            while (CMinusParser.scanner!!.viewNextToken().type != Token.TokenType.RPAREN_TOKEN &&
                   CMinusParser.scanner!!.viewNextToken().type != Token.TokenType.EOF_TOKEN) {
                if(CMinusParser.scanner!!.viewNextToken().type == Token.TokenType.COMMA_TOKEN) {
                    advanceToken()
                } else if (CMinusParser.scanner!!.viewNextToken().type == Token.TokenType.EOF_TOKEN) {
                    throw(Exception("EOFToken unexpected: Class CallExpression.parse"))
                }
                val arg = Expression.parse()
                args.add(arg)
            }
            matchToken(Token.TokenType.RPAREN_TOKEN)
            return CallExpression(args = args)
        }
    }
}
