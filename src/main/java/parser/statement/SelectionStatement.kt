package parser.statement

import compiler.misc.write
import lowlevel.*
import lowlevel.Function
import parser.CMinusParser
import parser.expression.Expression
import scanner.Token
import scanner.advanceToken
import scanner.matchToken
import java.io.FileOutputStream

var isUnConnected = false

class SelectionStatement(private var expression : Expression, private var thenStatement : Statement? = null, private var elseStatement: Statement? = null) :
    Statement() {
    override fun genLLCode(function: Function) {
        val thenBlk = BasicBlock(function)
        val postBlk = BasicBlock(function)
        val elseBlk: BasicBlock? = if(elseStatement != null) BasicBlock(function) else null

        val expReg = expression.genLLCode(function)
        val regNum = function.newRegNum

        val dstOperand = Operand(Operand.OperandType.REGISTER, regNum)
        val srcOperand1 = Operand(Operand.OperandType.REGISTER, expReg)
        val srcOperand2 = Operand(Operand.OperandType.INTEGER, 0)
        val srcOperand3 = Operand(Operand.OperandType.BLOCK, elseBlk?.blockNum ?: postBlk.blockNum)

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

            val srcOperand = Operand(Operand.OperandType.BLOCK, thenBlk.blockNum)

            val thenOper = Operation(Operation.OperationType.JMP, function.currBlock)
            thenOper.setSrcOperand(0, srcOperand)

            function.currBlock.appendOper(thenOper)
        }
        function.currBlock = thenBlk

        thenStatement!!.genLLCode(function)


        if (!isUnConnected) {
            function.appendBlock(postBlk)
        } else {
            function.appendUnconnectedBlock(postBlk)
        }

        if (elseBlk != null) {
            if (isUnConnected == false) {
                isUnConnected = true
                function.currBlock = elseBlk
                elseStatement!!.genLLCode(function)

                val srcOperand = Operand(Operand.OperandType.BLOCK, postBlk.blockNum)

                val elseOper = Operation(Operation.OperationType.JMP, function.currBlock)
                elseOper.setSrcOperand(0, srcOperand)

                function.currBlock.appendOper(elseOper)

                function.appendUnconnectedBlock(elseBlk)
                isUnConnected = false
            } else {
                isUnConnected = true
                function.currBlock = elseBlk
                elseStatement!!.genLLCode(function)

                val srcOperand = Operand(Operand.OperandType.BLOCK, postBlk.blockNum)

                val elseOper = Operation(Operation.OperationType.JMP, function.currBlock)
                elseOper.setSrcOperand(0, srcOperand)

                function.currBlock.appendOper(elseOper)

                function.appendUnconnectedBlock(elseBlk)
            }
        }

        function.currBlock = postBlk
    }

    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing SelectionStmt \n")
        expression.print("$spacing  ", fos)
        thenStatement?.print("$spacing  ", fos)
        elseStatement?.print("$spacing  ", fos)
    }
    companion object {
        fun parse(): SelectionStatement {
            advanceToken()
            matchToken(Token.TokenType.LPAREN_TOKEN)
            val expression = Expression.parse()
            matchToken(Token.TokenType.RPAREN_TOKEN)
            val thenStatement = Statement.parse()
            var elseStatement: Statement? = null
            if (CMinusParser.scanner!!.viewNextToken().type == Token.TokenType.ELSE_TOKEN) {
                advanceToken()
                elseStatement = Statement.parse()
            }
            return SelectionStatement(expression, thenStatement, elseStatement)
        }
    }
}