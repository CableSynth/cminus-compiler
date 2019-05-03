package parser.statement

import compiler.misc.write
import lowlevel.CodeItem
import lowlevel.Function
import lowlevel.Operand
import lowlevel.Operation
import parser.CMinusParser
import parser.expression.Expression
import scanner.Token
import scanner.matchToken
import java.io.FileOutputStream

class ReturnStatement(var expression: Expression? = null) : Statement() {
    override fun genLLCode(function: Function){
        if (expression != null) {
            val expReg = expression!!.genLLCode(function)

            val dstOperand = Operand(Operand.OperandType.MACRO, "RetReg")
            val srcOperand = Operand(Operand.OperandType.REGISTER, expReg)

            val oper = Operation(Operation.OperationType.ASSIGN, function.currBlock)
            oper.setDestOperand(0, dstOperand)
            oper.setSrcOperand(0, srcOperand)

            function.currBlock.appendOper(oper)
        }
    }

    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing ReturnStmt \n")
        expression?.print("$spacing  ", fos)
    }

    companion object {
        fun parse(): ReturnStatement {
            matchToken(Token.TokenType.RETURN_TOKEN)
            val expression: Expression? = if(CMinusParser.scanner!!.viewNextToken().type != Token.TokenType.SEMI_TOKEN) {
                Expression.parse()
            } else {
                null
            }
            matchToken(Token.TokenType.SEMI_TOKEN)
            return ReturnStatement(expression)
        }
    }
}