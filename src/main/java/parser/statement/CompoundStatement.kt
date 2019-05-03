package parser.statement

import compiler.misc.write
import lowlevel.CodeItem
import lowlevel.Function
import parser.CMinusParser
import parser.declaration.Declaration
import scanner.Token
import scanner.advanceToken
import scanner.matchToken
import java.io.FileOutputStream

class CompoundStatement(private var localDeclarations: ArrayList<Declaration> = ArrayList(),
                        var statementList: ArrayList<Statement> = ArrayList()) : Statement() {

    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing CmpStmt \n")

        if (!localDeclarations.isEmpty()) {
            fos.write("$spacing   LocalDecls \n")
            for (declaration in localDeclarations) {
                declaration.print("$spacing    ", fos)
            }
        }

        if (!statementList.isEmpty()) {
            fos.write("$spacing   StmtList \n")
            for (statement in statementList) {
                statement.print("$spacing    ", fos)
            }
        }
    }

    override fun genLLCode(function: Function): CodeItem? {

        var declItem: CodeItem? = null
        val statmentItem: CodeItem

        if (localDeclarations.isNotEmpty()) {
//            val symbolTable
            declItem = localDeclarations[0].genLLCode()
            var currentItem = declItem

            var nextItem: CodeItem
            for (item in 1 until localDeclarations.size) {
                nextItem = localDeclarations[item].genLLCode()
                currentItem!!.nextItem = nextItem
                currentItem = nextItem
            }
        }

//        if(statementList.isNotEmpty()){
//            statmentItem = statementList[0].genLLCode()!!
//            var currentItem = statmentItem
//
//            var nextItem: CodeItem
//            for (item in 1 until statementList.size) {
//                nextItem = statementList[item].genLLCode()!!
//                currentItem.nextItem = nextItem
//                currentItem = nextItem
//            }
//        }

        return declItem
    }

    companion object {
        fun parse(): CompoundStatement {
            advanceToken()

            val localDeclarations = ArrayList<Declaration>()
            while (CMinusParser.scanner!!.viewNextToken().type == Token.TokenType.INT_TOKEN) {
                val declaration = Declaration.parse()
                localDeclarations.add(declaration)
            }

            val statements = ArrayList<Statement>()
            while (CMinusParser.scanner!!.viewNextToken().type != Token.TokenType.RCURLY_TOKEN) {
                val statement = Statement.parse()
                statements.add(statement)
            }

            matchToken(Token.TokenType.RCURLY_TOKEN)

            return CompoundStatement(localDeclarations, statements)
        }
    }
}