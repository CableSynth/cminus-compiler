package parser.expression

import compiler.misc.write
import lowlevel.CodeItem
import lowlevel.Function
import lowlevel.Operand
import lowlevel.Operation
import scanner.advanceToken
import java.io.FileOutputStream

class AssignExpression(var varExpression: VarExpression? = null, private val expression: Expression): Expression() {
    override fun genLLCode(function: Function): Int {
        val destinationReg = varExpression!!.genLLCode(function)
        val sourceReg = expression.genLLCode(function)

        val destinationOperand = Operand(Operand.OperandType.REGISTER, destinationReg)
        val sourceOperand = Operand(Operand.OperandType.REGISTER, sourceReg)

        val oper = Operation(Operation.OperationType.ASSIGN, function.currBlock)
        oper.setDestOperand(0, destinationOperand)
        oper.setSrcOperand(0, sourceOperand)

        function.currBlock.appendOper(oper)

        if (varExpression!!.isGlobal) {
            val srcOperand1 = Operand(Operand.OperandType.REGISTER, destinationReg)
            val srcOperand2 = Operand(Operand.OperandType.STRING, varExpression!!.identifier)
            val srcOperand3 = Operand(Operand.OperandType.INTEGER, 0)

            val oper1 = Operation(Operation.OperationType.STORE_I, function.currBlock)
            oper1.setSrcOperand(0, srcOperand1)
            oper1.setSrcOperand(1, srcOperand2)
            oper1.setSrcOperand(2, srcOperand3)

            function.currBlock.appendOper(oper1)
        }

        return destinationReg
    }

    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing AssignExpr\n")
        varExpression!!.print("$spacing  ", fos)
        expression.print("$spacing  ", fos)
    }

    companion object {
        fun parse(): AssignExpression {
            advanceToken()
            val expression = Expression.parse()
            return AssignExpression(expression = expression)
        }
    }
}