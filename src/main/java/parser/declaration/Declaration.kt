package compiler.parser.declaration

import compiler.parser.CMinusParser.Companion.scanner
import compiler.scanner.Token
import compiler.scanner.advanceToken
import compiler.scanner.matchToken
import java.io.FileOutputStream

abstract class Declaration {
    abstract fun print(spacing: String, fos: FileOutputStream)

    companion object {
        fun parse(): Declaration {
            return when (scanner!!.viewNextToken().type) {
                Token.TokenType.INT_TOKEN -> {
                    advanceToken()
                    val idToken = matchToken(Token.TokenType.ID_TOKEN)
                    parseDeclarationPrime(idToken)
                }
                Token.TokenType.VOID_TOKEN -> {
                    advanceToken()
                    val idToken = matchToken(Token.TokenType.ID_TOKEN)
                    parseFunctionDeclaration(idToken)
                }
                else -> throw(Exception("In Decl Parse " + scanner!!.viewNextToken().type))

            }
        }

        private fun parseDeclarationPrime(token: Token): Declaration {
            return when (scanner!!.viewNextToken().type) {
                Token.TokenType.SEMI_TOKEN -> {
                    advanceToken()

                    VarDeclaration(token.data.toString())
                }
                Token.TokenType.LBRACKET_TOKEN -> {
                    advanceToken()
                    val sizeToken = matchToken(Token.TokenType.NUM_TOKEN)
                    matchToken(Token.TokenType.RBRACKET_TOKEN)
                    matchToken(Token.TokenType.SEMI_TOKEN)

                    VarDeclaration(token.data.toString(), sizeToken.data.toString())
                }
                Token.TokenType.LPAREN_TOKEN -> {
                    parseFunctionDeclaration(token)
                }
                else -> throw(Exception("In Decl' Parse " + scanner!!.viewNextToken().type))
            }
        }

        private fun parseFunctionDeclaration(token: Token) : Declaration {
            if (scanner!!.viewNextToken().type == Token.TokenType.LPAREN_TOKEN){
                advanceToken()

                val functionDeclaration = FunctionDeclaration.parse()
                functionDeclaration.identifierName = token.data.toString()

                return functionDeclaration
            }
            else{
                throw (Exception("In Parse FunDecl " + scanner!!.viewNextToken().type))
            }
        }

    }

}