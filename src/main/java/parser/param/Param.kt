package parser.param

import compiler.misc.write
import lowlevel.Data
import lowlevel.Data.TYPE_INT
import lowlevel.FuncParam
import parser.CMinusParser
import scanner.Token
import scanner.advanceToken
import scanner.matchToken
import java.io.FileOutputStream

class Param(var identifier: String = "", private var isArray: Boolean = false) {
    companion object {
        fun parseParam(): Param {
            matchToken(Token.TokenType.INT_TOKEN)
            val idToken: Token = matchToken(Token.TokenType.ID_TOKEN)

            return if (CMinusParser.scanner!!.viewNextToken().type == Token.TokenType.LBRACKET_TOKEN && CMinusParser.scanner!!.viewNextToken().type != Token.TokenType.ERROR_TOKEN){
                advanceToken()
                matchToken(Token.TokenType.RBRACKET_TOKEN)
                Param(idToken.data.toString(), true)
            } else if (CMinusParser.scanner!!.viewNextToken().type == Token.TokenType.ERROR_TOKEN){
                throw (Exception("EOFToken unexpected: Class Param.parse"))
            } else{
                Param(idToken.data.toString(), false)
            }
        }
    }


    fun print(spacing: String, fos: FileOutputStream) {
        if (!isArray){
            fos.write("$spacing Param $identifier\n")
        }else {
            fos.write("$spacing Param $identifier [ ]\n")
        }
    }

    fun genLLCode(): FuncParam {
        return if (identifier != "void") {
            FuncParam(TYPE_INT, identifier, isArray)
        } else {
            FuncParam()
        }
    }
}