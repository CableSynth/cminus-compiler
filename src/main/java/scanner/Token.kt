/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner

class Token(var type: TokenType = TokenType.ERROR_TOKEN, var data: Any? = null) {

    enum class TokenType {
        INT_TOKEN,
        IF_TOKEN,
        ELSE_TOKEN,
        WHILE_TOKEN,
        VOID_TOKEN,
        RETURN_TOKEN,
        ASSIGN_TOKEN,
        EQUAL_TOKEN,
        NOT_EQUAL_TOKEN,
        LESS_THAN_TOKEN,
        GREATER_THAN_TOKEN,
        GREATER_EQUAL_TOKEN,
        LESS_EQUAL_TOKEN,
        PLUS_TOKEN,
        MINUS_TOKEN,
        TIMES_TOKEN,
        DIV_TOKEN,
        COMMA_TOKEN,
        LBRACKET_TOKEN,
        RBRACKET_TOKEN,
        LCURLY_TOKEN,
        RCURLY_TOKEN,
        LPAREN_TOKEN,
        RPAREN_TOKEN,
        SEMI_TOKEN,
        COMMENT_TOKEN,
        NUM_TOKEN,
        ID_TOKEN,
        EOF_TOKEN,
        ERROR_TOKEN
    }
}
