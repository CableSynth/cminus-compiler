package parser.expression

import compiler.misc.write
import lowlevel.Function
import lowlevel.Operand
import lowlevel.Operation
import java.io.FileOutputStream

class NumberExpression(var number: Int = 0): Expression() {

    override fun genLLCode(function: Function): Int {
        val regNum = function.newRegNum

        val dstOperand = Operand(Operand.OperandType.REGISTER, regNum)
        val srcOperand = Operand(Operand.OperandType.INTEGER, number)

        val oper = Operation(Operation.OperationType.ASSIGN, function.currBlock)
        oper.setDestOperand(0, dstOperand)
        oper.setSrcOperand(0, srcOperand)

        function.currBlock.appendOper(oper)

        return regNum
    }

    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing NumExpr $number \n")
    }

    companion object {
        fun parse(): NumberExpression {
            return NumberExpression()
        }
    }
}