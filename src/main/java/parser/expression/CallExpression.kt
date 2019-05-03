package parser.expression

import compiler.misc.write
import lowlevel.*
import lowlevel.Function
import parser.CMinusParser
import scanner.Token
import scanner.advanceToken
import scanner.matchToken
import java.io.FileOutputStream

class CallExpression(var identifier: String = "", var args: ArrayList<Expression> = ArrayList()): Expression() {
    override fun genLLCode(function: Function): Int {

        args.forEachIndexed { i, arg ->
            val reg = arg.genLLCode(function)
            val operand = Operand(Operand.OperandType.REGISTER, reg)

            val oper = Operation(Operation.OperationType.PASS, function.currBlock)
            oper.addAttribute(Attribute("PARAM_NUM", i.toString()))
            oper.setSrcOperand(0, operand)
            function.currBlock.appendOper(oper)
        }

        val operand = Operand(Operand.OperandType.STRING, identifier)

        val oper = Operation(Operation.OperationType.CALL, function.currBlock)
        oper.addAttribute(Attribute("numParams", args.size.toString()))
        oper.setSrcOperand(0, operand)

        function.currBlock.appendOper(oper)

        val retReg = function.newRegNum

        val dstOperand = Operand(Operand.OperandType.REGISTER, retReg)
        val srcOperand = Operand(Operand.OperandType.MACRO, "RetReg")

        val oper1 = Operation(Operation.OperationType.ASSIGN, function.currBlock)
        oper1.setDestOperand(0, dstOperand)
        oper1.setSrcOperand(0, srcOperand)

        function.currBlock.appendOper(oper1)

        return retReg
    }

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
