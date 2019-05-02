package compiler.scanner

import parser.CMinusParser

fun advanceToken(): Token {
    return CMinusParser.scanner!!.getNextToken()
}

fun matchToken(tokenType: Token.TokenType): Token {
    val token = CMinusParser.scanner?.viewNextToken()
    if (token != null && token.type == tokenType) {
        advanceToken()
        return token
    } else {
        throw(Exception("Failed on match " + token?.type))
    }
}

fun viewNextToken(): Token {
    return CMinusParser.scanner!!.viewNextToken()
}