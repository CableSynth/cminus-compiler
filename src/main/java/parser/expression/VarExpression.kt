package parser.expression

import compiler.misc.write
import lowlevel.CodeItem
import lowlevel.Function
import lowlevel.Operand
import lowlevel.Operation
import java.io.FileOutputStream

class VarExpression(var identifier: String = ""): Expression() {
    var isGlobal = false

    override fun genLLCode(function: Function): Int {
        if (function.table.containsKey(identifier)) {
            val location = function.table[identifier]
            return location as Int
        } else {
            isGlobal = true
            val regNum = function.newRegNum

            val dstOperand = Operand(Operand.OperandType.REGISTER, regNum)
            val srcOperand1 = Operand(Operand.OperandType.STRING, identifier)
            val srcOperand2 = Operand(Operand.OperandType.INTEGER, 0)

            val oper = Operation(Operation.OperationType.LOAD_I, function.currBlock)
            oper.setDestOperand(0, dstOperand)
            oper.setSrcOperand(0, srcOperand1)
            oper.setSrcOperand(1, srcOperand2)

            function.currBlock.appendOper(oper)

            return regNum
        }
    }

    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing VarExpr $identifier \n")
    }

    companion object {
        fun parse(): VarExpression {
            return VarExpression()
        }
    }
}