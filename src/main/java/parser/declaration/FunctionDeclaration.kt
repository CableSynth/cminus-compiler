package parser.declaration

import compiler.misc.write
import lowlevel.BasicBlock
import parser.CMinusParser
import parser.param.Param
import parser.statement.CompoundStatement
import parser.statement.Statement
import scanner.Token
import scanner.advanceToken
import scanner.matchToken
import lowlevel.CodeItem
import lowlevel.Data.TYPE_INT
import lowlevel.Data.TYPE_VOID
import lowlevel.FuncParam
import lowlevel.Function
import parser.statement.ReturnStatement
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
        val firstParam = params[0].genLLCode()
        var currentParam = firstParam

        var nextParam: FuncParam
        for (param in 1 until params.size) {
            nextParam = params[param].genLLCode()!!
            currentParam!!.nextParam = nextParam
            currentParam = nextParam
        }

        val returnType: Int = if ((compoundStatement as CompoundStatement).statementList.isNotEmpty() &&
            (compoundStatement as CompoundStatement).statementList.last() is ReturnStatement &&
            ((compoundStatement as CompoundStatement).statementList.last() as ReturnStatement).expression != null) {
            TYPE_INT
        } else {
            TYPE_VOID
        }

        val function = Function(returnType, identifierName, firstParam)

        var next = firstParam
        while (next != null) {
            function.table[next.name] = function.newRegNum
            next = next.nextParam
        }

        function.createBlock0()

        val basicBlock = BasicBlock(function)
        function.appendBlock(basicBlock)
        function.currBlock = basicBlock

        compoundStatement.genLLCode(function)

        function.appendBlock(function.genReturnBlock())

        if (function.firstUnconnectedBlock != null) {
            function.appendBlock(function.firstUnconnectedBlock)
        }

        return function
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