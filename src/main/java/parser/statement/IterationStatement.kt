package parser.statement

import compiler.misc.write
import lowlevel.*
import lowlevel.Function
import parser.expression.Expression
import scanner.Token
import scanner.advanceToken
import scanner.matchToken
import java.io.FileOutputStream

class IterationStatement(private val expression: Expression, private val statement: Statement) : Statement() {
    override fun genLLCode(function: Function) {
        val thenBlk = BasicBlock(function)
        val postBlk = BasicBlock(function)

        val expReg = expression.genLLCode(function)
        val regNum = function.newRegNum

        val dstOperand = Operand(Operand.OperandType.REGISTER, regNum)
        val srcOperand1 = Operand(Operand.OperandType.REGISTER, expReg)
        val srcOperand2 = Operand(Operand.OperandType.INTEGER, 0)
        val srcOperand3 = Operand(Operand.OperandType.BLOCK, postBlk.blockNum)

        val oper = Operation(Operation.OperationType.BEQ, function.currBlock)
        oper.setDestOperand(0, dstOperand)
        oper.setSrcOperand(0, srcOperand1)
        oper.setSrcOperand(1, srcOperand2)
        oper.setSrcOperand(2, srcOperand3)

        function.currBlock.appendOper(oper)
        if (!isUnConnected) {
            function.appendBlock(thenBlk)
        } else {
            function.appendUnconnectedBlock(thenBlk)
        }
        function.currBlock = thenBlk

        statement.genLLCode(function)

        val expReg2 = expression.genLLCode(function)

        val srcOperand4 = Operand(Operand.OperandType.REGISTER, expReg2)
        val srcOperand5 = Operand(Operand.OperandType.BLOCK, thenBlk.blockNum)

        val oper2 = Operation(Operation.OperationType.BNE, function.currBlock)
        oper2.setDestOperand(0, dstOperand)
        oper2.setSrcOperand(0, srcOperand4)
        oper2.setSrcOperand(1, srcOperand2)
        oper2.setSrcOperand(2, srcOperand5)

        function.currBlock.appendOper(oper2)

        if (!isUnConnected) {
            function.appendBlock(postBlk)
        } else {
            function.appendUnconnectedBlock(postBlk)
        }
        function.currBlock = postBlk
    }

    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing IterationStmt \n")
        expression.print("$spacing  ", fos)
        statement.print("$spacing  ", fos)
    }

    companion object {
        fun parse(): IterationStatement {
            advanceToken()
            matchToken(Token.TokenType.LPAREN_TOKEN)
            val expression = Expression.parse()
            matchToken(Token.TokenType.RPAREN_TOKEN)
            val statement = Statement.parse()
            return IterationStatement(expression, statement)
        }
    }
}