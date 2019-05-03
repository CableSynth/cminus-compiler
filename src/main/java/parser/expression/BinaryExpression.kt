package parser.expression

import compiler.misc.write
import lowlevel.*
import lowlevel.Function
import scanner.Token
import scanner.Token.TokenType.*
import scanner.advanceToken
import scanner.matchToken
import scanner.viewNextToken
import java.io.FileOutputStream

class BinaryExpression(val binopToken: Token.TokenType? = null, var leftExpression: Expression? = null,
                       var rightExpression: Expression? = null) : Expression() {
    override fun genLLCode(function: Function): Int {
        val regNum = function.newRegNum

        val leftReg = leftExpression!!.genLLCode(function)
        val rightReg = rightExpression!!.genLLCode(function)

        val dstOperand = Operand(Operand.OperandType.REGISTER, regNum)
        val srcOperand1 = Operand(Operand.OperandType.REGISTER, leftReg)
        val srcOperand2 = Operand(Operand.OperandType.REGISTER, rightReg)

        val operType = when(binopToken) {
            EQUAL_TOKEN -> Operation.OperationType.EQUAL
            NOT_EQUAL_TOKEN -> Operation.OperationType.NOT_EQUAL
            LESS_THAN_TOKEN -> Operation.OperationType.LT
            GREATER_THAN_TOKEN -> Operation.OperationType.GT
            GREATER_EQUAL_TOKEN -> Operation.OperationType.GTE
            LESS_EQUAL_TOKEN -> Operation.OperationType.LTE
            PLUS_TOKEN -> Operation.OperationType.ADD_I
            MINUS_TOKEN -> Operation.OperationType.SUB_I
            TIMES_TOKEN -> Operation.OperationType.MUL_I
            DIV_TOKEN -> Operation.OperationType.DIV_I
            else -> throw(LowLevelException("Invalid Operation: $binopToken"))
        }

        val oper = Operation(operType, function.currBlock)
        oper.setDestOperand(0, dstOperand)
        oper.setSrcOperand(0, srcOperand1)
        oper.setSrcOperand(1, srcOperand2)

        function.currBlock.appendOper(oper)

        return regNum
    }

    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing BinaryExp $binopToken\n")
        leftExpression?.print("$spacing  ", fos)
        rightExpression?.print("$spacing  ", fos)
    }

    companion object {
        fun parse(): BinaryExpression {
            val binopToken = advanceToken()
            val expression: Expression
            return if (viewNextToken().type == LPAREN_TOKEN) {
                matchToken(LPAREN_TOKEN)
                expression = Expression.parse()
                matchToken(RPAREN_TOKEN)

                var binaryExpression = BinaryExpression(binopToken.type, leftExpression = expression)
                if (viewNextToken().type != SEMI_TOKEN) {
                    val extraExpression = parse()
                    binaryExpression = BinaryExpression(binopToken.type, leftExpression = expression, rightExpression = extraExpression)
                }

                binaryExpression
            } else {
                expression = Expression.parse()
                if (expression is BinaryExpression) {
                    if (expression.binopToken == TIMES_TOKEN ||
                        expression.binopToken == DIV_TOKEN
                    ) {
                        BinaryExpression(binopToken.type, leftExpression = expression)
                    } else {
                        BinaryExpression(binopToken.type, rightExpression = expression)
                    }
                } else {
                    BinaryExpression(binopToken.type, rightExpression = expression)
                }
            }
        }
    }
}