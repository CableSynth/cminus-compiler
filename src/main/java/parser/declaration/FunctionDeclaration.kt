package parser.declaration

import compiler.misc.write
import parser.CMinusParser
import parser.param.Param
import parser.statement.CompoundStatement
import parser.statement.Statement
import scanner.Token
import scanner.advanceToken
import scanner.matchToken
import lowlevel.CodeItem
import java.io.FileOutputStream

class FunctionDeclaration(private var params: ArrayList<Param> = ArrayList(),
                          private var compoundStatement: Statement) : Declaration() {
    var identifierName: String? = null

    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing FunDecl $identifierName \n")
        for (param in params) {
           param.print("$spacing  ", fos)
        }

        compoundStatement.print("$spacing  ", fos)
    }

    override fun genLLCode(): CodeItem {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun parse(): FunctionDeclaration {
            val params = ArrayList<Param>()
            if(CMinusParser.scanner!!.viewNextToken().type != Token.TokenType.VOID_TOKEN){
                while (CMinusParser.scanner!!.viewNextToken().type != Token.TokenType.RPAREN_TOKEN){
                    if(CMinusParser.scanner!!.viewNextToken().type == Token.TokenType.COMMA_TOKEN) {
                        advanceToken()
                    } else if (CMinusParser.scanner!!.viewNextToken().type == Token.TokenType.EOF_TOKEN) {
                        throw(Exception("EOFToken unexpected: Class FunctionDeclaration.parse"))
                    }

                    val param = Param.parseParam()
                    params.add(param)
                }
            } else{
                val voidParam = Param("void")
                params.add(voidParam)
                advanceToken()

            }
            matchToken(Token.TokenType.RPAREN_TOKEN)

            val compoundStatement = CompoundStatement.parse()

            return FunctionDeclaration(params, compoundStatement)
        }
    }
}